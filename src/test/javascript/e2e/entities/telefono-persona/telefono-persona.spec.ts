import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { TelefonoPersonaComponentsPage, TelefonoPersonaDeleteDialog, TelefonoPersonaUpdatePage } from './telefono-persona.page-object';

const expect = chai.expect;

describe('TelefonoPersona e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let telefonoPersonaComponentsPage: TelefonoPersonaComponentsPage;
  let telefonoPersonaUpdatePage: TelefonoPersonaUpdatePage;
  let telefonoPersonaDeleteDialog: TelefonoPersonaDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load TelefonoPersonas', async () => {
    await navBarPage.goToEntity('telefono-persona');
    telefonoPersonaComponentsPage = new TelefonoPersonaComponentsPage();
    await browser.wait(ec.visibilityOf(telefonoPersonaComponentsPage.title), 5000);
    expect(await telefonoPersonaComponentsPage.getTitle()).to.eq('eoloprjApp.telefonoPersona.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(telefonoPersonaComponentsPage.entities), ec.visibilityOf(telefonoPersonaComponentsPage.noResult)),
      1000
    );
  });

  it('should load create TelefonoPersona page', async () => {
    await telefonoPersonaComponentsPage.clickOnCreateButton();
    telefonoPersonaUpdatePage = new TelefonoPersonaUpdatePage();
    expect(await telefonoPersonaUpdatePage.getPageTitle()).to.eq('eoloprjApp.telefonoPersona.home.createOrEditLabel');
    await telefonoPersonaUpdatePage.cancel();
  });

  it('should create and save TelefonoPersonas', async () => {
    const nbButtonsBeforeCreate = await telefonoPersonaComponentsPage.countDeleteButtons();

    await telefonoPersonaComponentsPage.clickOnCreateButton();

    await promise.all([
      telefonoPersonaUpdatePage.setIdPersonaRefInput('5'),
      telefonoPersonaUpdatePage.setEtichettaInput('5'),
      telefonoPersonaUpdatePage.setValoreInput('5'),
      telefonoPersonaUpdatePage.idPersonaSelectLastOption(),
    ]);

    expect(await telefonoPersonaUpdatePage.getIdPersonaRefInput()).to.eq('5', 'Expected idPersonaRef value to be equals to 5');
    expect(await telefonoPersonaUpdatePage.getEtichettaInput()).to.eq('5', 'Expected etichetta value to be equals to 5');
    expect(await telefonoPersonaUpdatePage.getValoreInput()).to.eq('5', 'Expected valore value to be equals to 5');

    await telefonoPersonaUpdatePage.save();
    expect(await telefonoPersonaUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await telefonoPersonaComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last TelefonoPersona', async () => {
    const nbButtonsBeforeDelete = await telefonoPersonaComponentsPage.countDeleteButtons();
    await telefonoPersonaComponentsPage.clickOnLastDeleteButton();

    telefonoPersonaDeleteDialog = new TelefonoPersonaDeleteDialog();
    expect(await telefonoPersonaDeleteDialog.getDialogTitle()).to.eq('eoloprjApp.telefonoPersona.delete.question');
    await telefonoPersonaDeleteDialog.clickOnConfirmButton();

    expect(await telefonoPersonaComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
