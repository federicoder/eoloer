import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { NotaPraticaComponentsPage, NotaPraticaDeleteDialog, NotaPraticaUpdatePage } from './nota-pratica.page-object';

const expect = chai.expect;

describe('NotaPratica e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let notaPraticaComponentsPage: NotaPraticaComponentsPage;
  let notaPraticaUpdatePage: NotaPraticaUpdatePage;
  let notaPraticaDeleteDialog: NotaPraticaDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load NotaPraticas', async () => {
    await navBarPage.goToEntity('nota-pratica');
    notaPraticaComponentsPage = new NotaPraticaComponentsPage();
    await browser.wait(ec.visibilityOf(notaPraticaComponentsPage.title), 5000);
    expect(await notaPraticaComponentsPage.getTitle()).to.eq('eoloprjApp.notaPratica.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(notaPraticaComponentsPage.entities), ec.visibilityOf(notaPraticaComponentsPage.noResult)),
      1000
    );
  });

  it('should load create NotaPratica page', async () => {
    await notaPraticaComponentsPage.clickOnCreateButton();
    notaPraticaUpdatePage = new NotaPraticaUpdatePage();
    expect(await notaPraticaUpdatePage.getPageTitle()).to.eq('eoloprjApp.notaPratica.home.createOrEditLabel');
    await notaPraticaUpdatePage.cancel();
  });

  it('should create and save NotaPraticas', async () => {
    const nbButtonsBeforeCreate = await notaPraticaComponentsPage.countDeleteButtons();

    await notaPraticaComponentsPage.clickOnCreateButton();

    await promise.all([
      notaPraticaUpdatePage.setIdPraticaRefInput('5'),
      notaPraticaUpdatePage.setDataInput('data'),
      notaPraticaUpdatePage.setNotaInput('nota'),
      notaPraticaUpdatePage.setVersionInput('version'),
      notaPraticaUpdatePage.idPraticaRefSelectLastOption(),
    ]);

    expect(await notaPraticaUpdatePage.getIdPraticaRefInput()).to.eq('5', 'Expected idPraticaRef value to be equals to 5');
    expect(await notaPraticaUpdatePage.getDataInput()).to.eq('data', 'Expected Data value to be equals to data');
    expect(await notaPraticaUpdatePage.getNotaInput()).to.eq('nota', 'Expected Nota value to be equals to nota');
    expect(await notaPraticaUpdatePage.getVersionInput()).to.eq('version', 'Expected Version value to be equals to version');

    await notaPraticaUpdatePage.save();
    expect(await notaPraticaUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await notaPraticaComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last NotaPratica', async () => {
    const nbButtonsBeforeDelete = await notaPraticaComponentsPage.countDeleteButtons();
    await notaPraticaComponentsPage.clickOnLastDeleteButton();

    notaPraticaDeleteDialog = new NotaPraticaDeleteDialog();
    expect(await notaPraticaDeleteDialog.getDialogTitle()).to.eq('eoloprjApp.notaPratica.delete.question');
    await notaPraticaDeleteDialog.clickOnConfirmButton();

    expect(await notaPraticaComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
