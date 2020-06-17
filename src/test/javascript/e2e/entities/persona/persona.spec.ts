import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { PersonaComponentsPage, PersonaDeleteDialog, PersonaUpdatePage } from './persona.page-object';

const expect = chai.expect;

describe('Persona e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let personaComponentsPage: PersonaComponentsPage;
  let personaUpdatePage: PersonaUpdatePage;
  let personaDeleteDialog: PersonaDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Personas', async () => {
    await navBarPage.goToEntity('persona');
    personaComponentsPage = new PersonaComponentsPage();
    await browser.wait(ec.visibilityOf(personaComponentsPage.title), 5000);
    expect(await personaComponentsPage.getTitle()).to.eq('eoloprjApp.persona.home.title');
    await browser.wait(ec.or(ec.visibilityOf(personaComponentsPage.entities), ec.visibilityOf(personaComponentsPage.noResult)), 1000);
  });

  it('should load create Persona page', async () => {
    await personaComponentsPage.clickOnCreateButton();
    personaUpdatePage = new PersonaUpdatePage();
    expect(await personaUpdatePage.getPageTitle()).to.eq('eoloprjApp.persona.home.createOrEditLabel');
    await personaUpdatePage.cancel();
  });

  it('should create and save Personas', async () => {
    const nbButtonsBeforeCreate = await personaComponentsPage.countDeleteButtons();

    await personaComponentsPage.clickOnCreateButton();

    await promise.all([
      personaUpdatePage.setIdStudioInput('5'),
      personaUpdatePage.setCodiceFiscaleInput('codiceFiscale'),
      personaUpdatePage.setAreaDiInteresseInput('areaDiInteresse'),
      personaUpdatePage.setTitoloInput('titolo'),
      personaUpdatePage.setCognomeInput('cognome'),
      personaUpdatePage.setNomeInput('nome'),
      personaUpdatePage.setDataDiNascitaInput('dataDiNascita'),
      personaUpdatePage.setLuogoDiNascitaInput('luogoDiNascita'),
      personaUpdatePage.setProfessioneInput('professione'),
      personaUpdatePage.setTipoInput('5'),
      personaUpdatePage.setDiscriminatorInput('discriminator'),
      personaUpdatePage.setIdRuoloPersonaInput('5'),
      personaUpdatePage.setTipoRuoloUtenteInput('5'),
      personaUpdatePage.idSelectLastOption(),
    ]);

    expect(await personaUpdatePage.getIdStudioInput()).to.eq('5', 'Expected idStudio value to be equals to 5');
    expect(await personaUpdatePage.getCodiceFiscaleInput()).to.eq(
      'codiceFiscale',
      'Expected CodiceFiscale value to be equals to codiceFiscale'
    );
    expect(await personaUpdatePage.getAreaDiInteresseInput()).to.eq(
      'areaDiInteresse',
      'Expected AreaDiInteresse value to be equals to areaDiInteresse'
    );
    expect(await personaUpdatePage.getTitoloInput()).to.eq('titolo', 'Expected Titolo value to be equals to titolo');
    expect(await personaUpdatePage.getCognomeInput()).to.eq('cognome', 'Expected Cognome value to be equals to cognome');
    expect(await personaUpdatePage.getNomeInput()).to.eq('nome', 'Expected Nome value to be equals to nome');
    expect(await personaUpdatePage.getDataDiNascitaInput()).to.eq(
      'dataDiNascita',
      'Expected DataDiNascita value to be equals to dataDiNascita'
    );
    expect(await personaUpdatePage.getLuogoDiNascitaInput()).to.eq(
      'luogoDiNascita',
      'Expected LuogoDiNascita value to be equals to luogoDiNascita'
    );
    expect(await personaUpdatePage.getProfessioneInput()).to.eq('professione', 'Expected Professione value to be equals to professione');
    expect(await personaUpdatePage.getTipoInput()).to.eq('5', 'Expected tipo value to be equals to 5');
    expect(await personaUpdatePage.getDiscriminatorInput()).to.eq(
      'discriminator',
      'Expected Discriminator value to be equals to discriminator'
    );
    expect(await personaUpdatePage.getIdRuoloPersonaInput()).to.eq('5', 'Expected idRuoloPersona value to be equals to 5');
    expect(await personaUpdatePage.getTipoRuoloUtenteInput()).to.eq('5', 'Expected tipoRuoloUtente value to be equals to 5');

    await personaUpdatePage.save();
    expect(await personaUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await personaComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Persona', async () => {
    const nbButtonsBeforeDelete = await personaComponentsPage.countDeleteButtons();
    await personaComponentsPage.clickOnLastDeleteButton();

    personaDeleteDialog = new PersonaDeleteDialog();
    expect(await personaDeleteDialog.getDialogTitle()).to.eq('eoloprjApp.persona.delete.question');
    await personaDeleteDialog.clickOnConfirmButton();

    expect(await personaComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
