import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { NotaTaskComponentsPage, NotaTaskDeleteDialog, NotaTaskUpdatePage } from './nota-task.page-object';

const expect = chai.expect;

describe('NotaTask e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let notaTaskComponentsPage: NotaTaskComponentsPage;
  let notaTaskUpdatePage: NotaTaskUpdatePage;
  let notaTaskDeleteDialog: NotaTaskDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load NotaTasks', async () => {
    await navBarPage.goToEntity('nota-task');
    notaTaskComponentsPage = new NotaTaskComponentsPage();
    await browser.wait(ec.visibilityOf(notaTaskComponentsPage.title), 5000);
    expect(await notaTaskComponentsPage.getTitle()).to.eq('eoloprjApp.notaTask.home.title');
    await browser.wait(ec.or(ec.visibilityOf(notaTaskComponentsPage.entities), ec.visibilityOf(notaTaskComponentsPage.noResult)), 1000);
  });

  it('should load create NotaTask page', async () => {
    await notaTaskComponentsPage.clickOnCreateButton();
    notaTaskUpdatePage = new NotaTaskUpdatePage();
    expect(await notaTaskUpdatePage.getPageTitle()).to.eq('eoloprjApp.notaTask.home.createOrEditLabel');
    await notaTaskUpdatePage.cancel();
  });

  it('should create and save NotaTasks', async () => {
    const nbButtonsBeforeCreate = await notaTaskComponentsPage.countDeleteButtons();

    await notaTaskComponentsPage.clickOnCreateButton();

    await promise.all([
      notaTaskUpdatePage.setIdTaskInput('5'),
      notaTaskUpdatePage.setDataInput('data'),
      notaTaskUpdatePage.setNotaInput('nota'),
      notaTaskUpdatePage.setVersionInput('version'),
      notaTaskUpdatePage.taskSelectLastOption(),
    ]);

    expect(await notaTaskUpdatePage.getIdTaskInput()).to.eq('5', 'Expected idTask value to be equals to 5');
    expect(await notaTaskUpdatePage.getDataInput()).to.eq('data', 'Expected Data value to be equals to data');
    expect(await notaTaskUpdatePage.getNotaInput()).to.eq('nota', 'Expected Nota value to be equals to nota');
    expect(await notaTaskUpdatePage.getVersionInput()).to.eq('version', 'Expected Version value to be equals to version');

    await notaTaskUpdatePage.save();
    expect(await notaTaskUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await notaTaskComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last NotaTask', async () => {
    const nbButtonsBeforeDelete = await notaTaskComponentsPage.countDeleteButtons();
    await notaTaskComponentsPage.clickOnLastDeleteButton();

    notaTaskDeleteDialog = new NotaTaskDeleteDialog();
    expect(await notaTaskDeleteDialog.getDialogTitle()).to.eq('eoloprjApp.notaTask.delete.question');
    await notaTaskDeleteDialog.clickOnConfirmButton();

    expect(await notaTaskComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
