import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  RappresentanzaPraticaComponentsPage,
  RappresentanzaPraticaDeleteDialog,
  RappresentanzaPraticaUpdatePage,
} from './rappresentanza-pratica.page-object';

const expect = chai.expect;

describe('RappresentanzaPratica e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let rappresentanzaPraticaComponentsPage: RappresentanzaPraticaComponentsPage;
  let rappresentanzaPraticaUpdatePage: RappresentanzaPraticaUpdatePage;
  let rappresentanzaPraticaDeleteDialog: RappresentanzaPraticaDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load RappresentanzaPraticas', async () => {
    await navBarPage.goToEntity('rappresentanza-pratica');
    rappresentanzaPraticaComponentsPage = new RappresentanzaPraticaComponentsPage();
    await browser.wait(ec.visibilityOf(rappresentanzaPraticaComponentsPage.title), 5000);
    expect(await rappresentanzaPraticaComponentsPage.getTitle()).to.eq('eoloprjApp.rappresentanzaPratica.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(rappresentanzaPraticaComponentsPage.entities), ec.visibilityOf(rappresentanzaPraticaComponentsPage.noResult)),
      1000
    );
  });

  it('should load create RappresentanzaPratica page', async () => {
    await rappresentanzaPraticaComponentsPage.clickOnCreateButton();
    rappresentanzaPraticaUpdatePage = new RappresentanzaPraticaUpdatePage();
    expect(await rappresentanzaPraticaUpdatePage.getPageTitle()).to.eq('eoloprjApp.rappresentanzaPratica.home.createOrEditLabel');
    await rappresentanzaPraticaUpdatePage.cancel();
  });

  it('should create and save RappresentanzaPraticas', async () => {
    const nbButtonsBeforeCreate = await rappresentanzaPraticaComponentsPage.countDeleteButtons();

    await rappresentanzaPraticaComponentsPage.clickOnCreateButton();

    await promise.all([
      rappresentanzaPraticaUpdatePage.setIdRuoloPersonaInput('5'),
      rappresentanzaPraticaUpdatePage.setIdPersonaInput('5'),
      rappresentanzaPraticaUpdatePage.ruoliSelectLastOption(),
      rappresentanzaPraticaUpdatePage.personaSelectLastOption(),
    ]);

    expect(await rappresentanzaPraticaUpdatePage.getIdRuoloPersonaInput()).to.eq('5', 'Expected idRuoloPersona value to be equals to 5');
    expect(await rappresentanzaPraticaUpdatePage.getIdPersonaInput()).to.eq('5', 'Expected idPersona value to be equals to 5');

    await rappresentanzaPraticaUpdatePage.save();
    expect(await rappresentanzaPraticaUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await rappresentanzaPraticaComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last RappresentanzaPratica', async () => {
    const nbButtonsBeforeDelete = await rappresentanzaPraticaComponentsPage.countDeleteButtons();
    await rappresentanzaPraticaComponentsPage.clickOnLastDeleteButton();

    rappresentanzaPraticaDeleteDialog = new RappresentanzaPraticaDeleteDialog();
    expect(await rappresentanzaPraticaDeleteDialog.getDialogTitle()).to.eq('eoloprjApp.rappresentanzaPratica.delete.question');
    await rappresentanzaPraticaDeleteDialog.clickOnConfirmButton();

    expect(await rappresentanzaPraticaComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
