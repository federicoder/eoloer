import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  AllegatoTemplateTaskComponentsPage,
  AllegatoTemplateTaskDeleteDialog,
  AllegatoTemplateTaskUpdatePage,
} from './allegato-template-task.page-object';

const expect = chai.expect;

describe('AllegatoTemplateTask e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let allegatoTemplateTaskComponentsPage: AllegatoTemplateTaskComponentsPage;
  let allegatoTemplateTaskUpdatePage: AllegatoTemplateTaskUpdatePage;
  let allegatoTemplateTaskDeleteDialog: AllegatoTemplateTaskDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load AllegatoTemplateTasks', async () => {
    await navBarPage.goToEntity('allegato-template-task');
    allegatoTemplateTaskComponentsPage = new AllegatoTemplateTaskComponentsPage();
    await browser.wait(ec.visibilityOf(allegatoTemplateTaskComponentsPage.title), 5000);
    expect(await allegatoTemplateTaskComponentsPage.getTitle()).to.eq('eoloprjApp.allegatoTemplateTask.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(allegatoTemplateTaskComponentsPage.entities), ec.visibilityOf(allegatoTemplateTaskComponentsPage.noResult)),
      1000
    );
  });

  it('should load create AllegatoTemplateTask page', async () => {
    await allegatoTemplateTaskComponentsPage.clickOnCreateButton();
    allegatoTemplateTaskUpdatePage = new AllegatoTemplateTaskUpdatePage();
    expect(await allegatoTemplateTaskUpdatePage.getPageTitle()).to.eq('eoloprjApp.allegatoTemplateTask.home.createOrEditLabel');
    await allegatoTemplateTaskUpdatePage.cancel();
  });

  it('should create and save AllegatoTemplateTasks', async () => {
    const nbButtonsBeforeCreate = await allegatoTemplateTaskComponentsPage.countDeleteButtons();

    await allegatoTemplateTaskComponentsPage.clickOnCreateButton();

    await promise.all([
      allegatoTemplateTaskUpdatePage.setIdTemplateTaskRefInput('5'),
      allegatoTemplateTaskUpdatePage.setIdTipoAllegatoRefInput('5'),
      allegatoTemplateTaskUpdatePage.setFormatoInput('5'),
      allegatoTemplateTaskUpdatePage.setIdFileRefInput('5'),
      allegatoTemplateTaskUpdatePage.setPubPrivInput('5'),
      allegatoTemplateTaskUpdatePage.idTemplateTaskSelectLastOption(),
      allegatoTemplateTaskUpdatePage.tipoAllegatoSelectLastOption(),
    ]);

    expect(await allegatoTemplateTaskUpdatePage.getIdTemplateTaskRefInput()).to.eq(
      '5',
      'Expected idTemplateTaskRef value to be equals to 5'
    );
    expect(await allegatoTemplateTaskUpdatePage.getIdTipoAllegatoRefInput()).to.eq(
      '5',
      'Expected idTipoAllegatoRef value to be equals to 5'
    );
    expect(await allegatoTemplateTaskUpdatePage.getFormatoInput()).to.eq('5', 'Expected formato value to be equals to 5');
    expect(await allegatoTemplateTaskUpdatePage.getIdFileRefInput()).to.eq('5', 'Expected idFileRef value to be equals to 5');
    expect(await allegatoTemplateTaskUpdatePage.getPubPrivInput()).to.eq('5', 'Expected pubPriv value to be equals to 5');

    await allegatoTemplateTaskUpdatePage.save();
    expect(await allegatoTemplateTaskUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await allegatoTemplateTaskComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last AllegatoTemplateTask', async () => {
    const nbButtonsBeforeDelete = await allegatoTemplateTaskComponentsPage.countDeleteButtons();
    await allegatoTemplateTaskComponentsPage.clickOnLastDeleteButton();

    allegatoTemplateTaskDeleteDialog = new AllegatoTemplateTaskDeleteDialog();
    expect(await allegatoTemplateTaskDeleteDialog.getDialogTitle()).to.eq('eoloprjApp.allegatoTemplateTask.delete.question');
    await allegatoTemplateTaskDeleteDialog.clickOnConfirmButton();

    expect(await allegatoTemplateTaskComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
