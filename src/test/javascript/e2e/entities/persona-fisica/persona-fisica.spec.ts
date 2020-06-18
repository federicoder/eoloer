import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { PersonaFisicaComponentsPage, PersonaFisicaDeleteDialog, PersonaFisicaUpdatePage } from './persona-fisica.page-object';

const expect = chai.expect;

describe('PersonaFisica e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let personaFisicaComponentsPage: PersonaFisicaComponentsPage;
  let personaFisicaUpdatePage: PersonaFisicaUpdatePage;
  let personaFisicaDeleteDialog: PersonaFisicaDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load PersonaFisicas', async () => {
    await navBarPage.goToEntity('persona-fisica');
    personaFisicaComponentsPage = new PersonaFisicaComponentsPage();
    await browser.wait(ec.visibilityOf(personaFisicaComponentsPage.title), 5000);
    expect(await personaFisicaComponentsPage.getTitle()).to.eq('eoloprjApp.personaFisica.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(personaFisicaComponentsPage.entities), ec.visibilityOf(personaFisicaComponentsPage.noResult)),
      1000
    );
  });

  it('should load create PersonaFisica page', async () => {
    await personaFisicaComponentsPage.clickOnCreateButton();
    personaFisicaUpdatePage = new PersonaFisicaUpdatePage();
    expect(await personaFisicaUpdatePage.getPageTitle()).to.eq('eoloprjApp.personaFisica.home.createOrEditLabel');
    await personaFisicaUpdatePage.cancel();
  });

  it('should create and save PersonaFisicas', async () => {
    const nbButtonsBeforeCreate = await personaFisicaComponentsPage.countDeleteButtons();

    await personaFisicaComponentsPage.clickOnCreateButton();

    await promise.all([
      personaFisicaUpdatePage.setIdPersonaRefInput('5'),
      personaFisicaUpdatePage.setIdRuoloPersonaRefInput('5'),
      personaFisicaUpdatePage.setTitoloInput('titolo'),
      personaFisicaUpdatePage.setCognomeInput('cognome'),
      personaFisicaUpdatePage.setNomeInput('nome'),
      personaFisicaUpdatePage.setDataDiNascitaInput('dataDiNascita'),
      personaFisicaUpdatePage.setLuogoDiNascitaInput('luogoDiNascita'),
      personaFisicaUpdatePage.setProfessioneInput('professione'),
      personaFisicaUpdatePage.idPersonaSelectLastOption(),
    ]);

    expect(await personaFisicaUpdatePage.getIdPersonaRefInput()).to.eq('5', 'Expected idPersonaRef value to be equals to 5');
    expect(await personaFisicaUpdatePage.getIdRuoloPersonaRefInput()).to.eq('5', 'Expected idRuoloPersonaRef value to be equals to 5');
    expect(await personaFisicaUpdatePage.getTitoloInput()).to.eq('titolo', 'Expected Titolo value to be equals to titolo');
    expect(await personaFisicaUpdatePage.getCognomeInput()).to.eq('cognome', 'Expected Cognome value to be equals to cognome');
    expect(await personaFisicaUpdatePage.getNomeInput()).to.eq('nome', 'Expected Nome value to be equals to nome');
    expect(await personaFisicaUpdatePage.getDataDiNascitaInput()).to.eq(
      'dataDiNascita',
      'Expected DataDiNascita value to be equals to dataDiNascita'
    );
    expect(await personaFisicaUpdatePage.getLuogoDiNascitaInput()).to.eq(
      'luogoDiNascita',
      'Expected LuogoDiNascita value to be equals to luogoDiNascita'
    );
    expect(await personaFisicaUpdatePage.getProfessioneInput()).to.eq(
      'professione',
      'Expected Professione value to be equals to professione'
    );

    await personaFisicaUpdatePage.save();
    expect(await personaFisicaUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await personaFisicaComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last PersonaFisica', async () => {
    const nbButtonsBeforeDelete = await personaFisicaComponentsPage.countDeleteButtons();
    await personaFisicaComponentsPage.clickOnLastDeleteButton();

    personaFisicaDeleteDialog = new PersonaFisicaDeleteDialog();
    expect(await personaFisicaDeleteDialog.getDialogTitle()).to.eq('eoloprjApp.personaFisica.delete.question');
    await personaFisicaDeleteDialog.clickOnConfirmButton();

    expect(await personaFisicaComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
