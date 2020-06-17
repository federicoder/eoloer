import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ConsuntivoTaskComponentsPage, ConsuntivoTaskDeleteDialog, ConsuntivoTaskUpdatePage } from './consuntivo-task.page-object';

const expect = chai.expect;

describe('ConsuntivoTask e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let consuntivoTaskComponentsPage: ConsuntivoTaskComponentsPage;
  let consuntivoTaskUpdatePage: ConsuntivoTaskUpdatePage;
  let consuntivoTaskDeleteDialog: ConsuntivoTaskDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ConsuntivoTasks', async () => {
    await navBarPage.goToEntity('consuntivo-task');
    consuntivoTaskComponentsPage = new ConsuntivoTaskComponentsPage();
    await browser.wait(ec.visibilityOf(consuntivoTaskComponentsPage.title), 5000);
    expect(await consuntivoTaskComponentsPage.getTitle()).to.eq('eoloprjApp.consuntivoTask.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(consuntivoTaskComponentsPage.entities), ec.visibilityOf(consuntivoTaskComponentsPage.noResult)),
      1000
    );
  });

  it('should load create ConsuntivoTask page', async () => {
    await consuntivoTaskComponentsPage.clickOnCreateButton();
    consuntivoTaskUpdatePage = new ConsuntivoTaskUpdatePage();
    expect(await consuntivoTaskUpdatePage.getPageTitle()).to.eq('eoloprjApp.consuntivoTask.home.createOrEditLabel');
    await consuntivoTaskUpdatePage.cancel();
  });

  it('should create and save ConsuntivoTasks', async () => {
    const nbButtonsBeforeCreate = await consuntivoTaskComponentsPage.countDeleteButtons();

    await consuntivoTaskComponentsPage.clickOnCreateButton();

    await promise.all([
      consuntivoTaskUpdatePage.setIdTaskRefInput('5'),
      consuntivoTaskUpdatePage.setDataInizioInput('dataInizio'),
      consuntivoTaskUpdatePage.setDataFineInput('dataFine'),
      consuntivoTaskUpdatePage.setTimeLineInput('5'),
      consuntivoTaskUpdatePage.setVersionInput('version'),
    ]);

    expect(await consuntivoTaskUpdatePage.getIdTaskRefInput()).to.eq('5', 'Expected idTaskRef value to be equals to 5');
    expect(await consuntivoTaskUpdatePage.getDataInizioInput()).to.eq('dataInizio', 'Expected DataInizio value to be equals to dataInizio');
    expect(await consuntivoTaskUpdatePage.getDataFineInput()).to.eq('dataFine', 'Expected DataFine value to be equals to dataFine');
    expect(await consuntivoTaskUpdatePage.getTimeLineInput()).to.eq('5', 'Expected timeLine value to be equals to 5');
    expect(await consuntivoTaskUpdatePage.getVersionInput()).to.eq('version', 'Expected Version value to be equals to version');

    await consuntivoTaskUpdatePage.save();
    expect(await consuntivoTaskUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await consuntivoTaskComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last ConsuntivoTask', async () => {
    const nbButtonsBeforeDelete = await consuntivoTaskComponentsPage.countDeleteButtons();
    await consuntivoTaskComponentsPage.clickOnLastDeleteButton();

    consuntivoTaskDeleteDialog = new ConsuntivoTaskDeleteDialog();
    expect(await consuntivoTaskDeleteDialog.getDialogTitle()).to.eq('eoloprjApp.consuntivoTask.delete.question');
    await consuntivoTaskDeleteDialog.clickOnConfirmButton();

    expect(await consuntivoTaskComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
