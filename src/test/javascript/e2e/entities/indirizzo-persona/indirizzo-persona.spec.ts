import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { IndirizzoPersonaComponentsPage, IndirizzoPersonaDeleteDialog, IndirizzoPersonaUpdatePage } from './indirizzo-persona.page-object';

const expect = chai.expect;

describe('IndirizzoPersona e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let indirizzoPersonaComponentsPage: IndirizzoPersonaComponentsPage;
  let indirizzoPersonaUpdatePage: IndirizzoPersonaUpdatePage;
  let indirizzoPersonaDeleteDialog: IndirizzoPersonaDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load IndirizzoPersonas', async () => {
    await navBarPage.goToEntity('indirizzo-persona');
    indirizzoPersonaComponentsPage = new IndirizzoPersonaComponentsPage();
    await browser.wait(ec.visibilityOf(indirizzoPersonaComponentsPage.title), 5000);
    expect(await indirizzoPersonaComponentsPage.getTitle()).to.eq('eoloprjApp.indirizzoPersona.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(indirizzoPersonaComponentsPage.entities), ec.visibilityOf(indirizzoPersonaComponentsPage.noResult)),
      1000
    );
  });

  it('should load create IndirizzoPersona page', async () => {
    await indirizzoPersonaComponentsPage.clickOnCreateButton();
    indirizzoPersonaUpdatePage = new IndirizzoPersonaUpdatePage();
    expect(await indirizzoPersonaUpdatePage.getPageTitle()).to.eq('eoloprjApp.indirizzoPersona.home.createOrEditLabel');
    await indirizzoPersonaUpdatePage.cancel();
  });

  it('should create and save IndirizzoPersonas', async () => {
    const nbButtonsBeforeCreate = await indirizzoPersonaComponentsPage.countDeleteButtons();

    await indirizzoPersonaComponentsPage.clickOnCreateButton();

    await promise.all([
      indirizzoPersonaUpdatePage.setIdPersonaRefInput('5'),
      indirizzoPersonaUpdatePage.setIndirizzoInput('indirizzo'),
      indirizzoPersonaUpdatePage.setComuneInput('comune'),
      indirizzoPersonaUpdatePage.setCapInput('5'),
      indirizzoPersonaUpdatePage.setProvinciaInput('provincia'),
      indirizzoPersonaUpdatePage.setRegioneInput('regione'),
      indirizzoPersonaUpdatePage.setNazioneInput('nazione'),
      indirizzoPersonaUpdatePage.idPersonaSelectLastOption(),
    ]);

    expect(await indirizzoPersonaUpdatePage.getIdPersonaRefInput()).to.eq('5', 'Expected idPersonaRef value to be equals to 5');
    expect(await indirizzoPersonaUpdatePage.getIndirizzoInput()).to.eq('indirizzo', 'Expected Indirizzo value to be equals to indirizzo');
    expect(await indirizzoPersonaUpdatePage.getComuneInput()).to.eq('comune', 'Expected Comune value to be equals to comune');
    expect(await indirizzoPersonaUpdatePage.getCapInput()).to.eq('5', 'Expected cap value to be equals to 5');
    expect(await indirizzoPersonaUpdatePage.getProvinciaInput()).to.eq('provincia', 'Expected Provincia value to be equals to provincia');
    expect(await indirizzoPersonaUpdatePage.getRegioneInput()).to.eq('regione', 'Expected Regione value to be equals to regione');
    expect(await indirizzoPersonaUpdatePage.getNazioneInput()).to.eq('nazione', 'Expected Nazione value to be equals to nazione');

    await indirizzoPersonaUpdatePage.save();
    expect(await indirizzoPersonaUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await indirizzoPersonaComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last IndirizzoPersona', async () => {
    const nbButtonsBeforeDelete = await indirizzoPersonaComponentsPage.countDeleteButtons();
    await indirizzoPersonaComponentsPage.clickOnLastDeleteButton();

    indirizzoPersonaDeleteDialog = new IndirizzoPersonaDeleteDialog();
    expect(await indirizzoPersonaDeleteDialog.getDialogTitle()).to.eq('eoloprjApp.indirizzoPersona.delete.question');
    await indirizzoPersonaDeleteDialog.clickOnConfirmButton();

    expect(await indirizzoPersonaComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
