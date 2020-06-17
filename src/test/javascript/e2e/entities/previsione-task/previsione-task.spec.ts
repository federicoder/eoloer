import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { PrevisioneTaskComponentsPage, PrevisioneTaskDeleteDialog, PrevisioneTaskUpdatePage } from './previsione-task.page-object';

const expect = chai.expect;

describe('PrevisioneTask e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let previsioneTaskComponentsPage: PrevisioneTaskComponentsPage;
  let previsioneTaskUpdatePage: PrevisioneTaskUpdatePage;
  let previsioneTaskDeleteDialog: PrevisioneTaskDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load PrevisioneTasks', async () => {
    await navBarPage.goToEntity('previsione-task');
    previsioneTaskComponentsPage = new PrevisioneTaskComponentsPage();
    await browser.wait(ec.visibilityOf(previsioneTaskComponentsPage.title), 5000);
    expect(await previsioneTaskComponentsPage.getTitle()).to.eq('eoloprjApp.previsioneTask.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(previsioneTaskComponentsPage.entities), ec.visibilityOf(previsioneTaskComponentsPage.noResult)),
      1000
    );
  });

  it('should load create PrevisioneTask page', async () => {
    await previsioneTaskComponentsPage.clickOnCreateButton();
    previsioneTaskUpdatePage = new PrevisioneTaskUpdatePage();
    expect(await previsioneTaskUpdatePage.getPageTitle()).to.eq('eoloprjApp.previsioneTask.home.createOrEditLabel');
    await previsioneTaskUpdatePage.cancel();
  });

  it('should create and save PrevisioneTasks', async () => {
    const nbButtonsBeforeCreate = await previsioneTaskComponentsPage.countDeleteButtons();

    await previsioneTaskComponentsPage.clickOnCreateButton();

    await promise.all([
      previsioneTaskUpdatePage.setIdTaskRefInput('5'),
      previsioneTaskUpdatePage.setQntOrdineInput('5'),
      previsioneTaskUpdatePage.setPrcPrevisioneInput('5'),
      previsioneTaskUpdatePage.setCheckListInput('5'),
      previsioneTaskUpdatePage.setIdTaskMilestoneInput('5'),
      previsioneTaskUpdatePage.setTipoTaskInput('tipoTask'),
      previsioneTaskUpdatePage.setVersionInput('version'),
      previsioneTaskUpdatePage.previsioneTaskSelectLastOption(),
    ]);

    expect(await previsioneTaskUpdatePage.getIdTaskRefInput()).to.eq('5', 'Expected idTaskRef value to be equals to 5');
    expect(await previsioneTaskUpdatePage.getQntOrdineInput()).to.eq('5', 'Expected qntOrdine value to be equals to 5');
    expect(await previsioneTaskUpdatePage.getPrcPrevisioneInput()).to.eq('5', 'Expected prcPrevisione value to be equals to 5');
    expect(await previsioneTaskUpdatePage.getCheckListInput()).to.eq('5', 'Expected checkList value to be equals to 5');
    expect(await previsioneTaskUpdatePage.getIdTaskMilestoneInput()).to.eq('5', 'Expected idTaskMilestone value to be equals to 5');
    expect(await previsioneTaskUpdatePage.getTipoTaskInput()).to.eq('tipoTask', 'Expected TipoTask value to be equals to tipoTask');
    expect(await previsioneTaskUpdatePage.getVersionInput()).to.eq('version', 'Expected Version value to be equals to version');

    await previsioneTaskUpdatePage.save();
    expect(await previsioneTaskUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await previsioneTaskComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last PrevisioneTask', async () => {
    const nbButtonsBeforeDelete = await previsioneTaskComponentsPage.countDeleteButtons();
    await previsioneTaskComponentsPage.clickOnLastDeleteButton();

    previsioneTaskDeleteDialog = new PrevisioneTaskDeleteDialog();
    expect(await previsioneTaskDeleteDialog.getDialogTitle()).to.eq('eoloprjApp.previsioneTask.delete.question');
    await previsioneTaskDeleteDialog.clickOnConfirmButton();

    expect(await previsioneTaskComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
