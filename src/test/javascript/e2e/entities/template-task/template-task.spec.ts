import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { TemplateTaskComponentsPage, TemplateTaskDeleteDialog, TemplateTaskUpdatePage } from './template-task.page-object';

const expect = chai.expect;

describe('TemplateTask e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let templateTaskComponentsPage: TemplateTaskComponentsPage;
  let templateTaskUpdatePage: TemplateTaskUpdatePage;
  let templateTaskDeleteDialog: TemplateTaskDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load TemplateTasks', async () => {
    await navBarPage.goToEntity('template-task');
    templateTaskComponentsPage = new TemplateTaskComponentsPage();
    await browser.wait(ec.visibilityOf(templateTaskComponentsPage.title), 5000);
    expect(await templateTaskComponentsPage.getTitle()).to.eq('eoloprjApp.templateTask.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(templateTaskComponentsPage.entities), ec.visibilityOf(templateTaskComponentsPage.noResult)),
      1000
    );
  });

  it('should load create TemplateTask page', async () => {
    await templateTaskComponentsPage.clickOnCreateButton();
    templateTaskUpdatePage = new TemplateTaskUpdatePage();
    expect(await templateTaskUpdatePage.getPageTitle()).to.eq('eoloprjApp.templateTask.home.createOrEditLabel');
    await templateTaskUpdatePage.cancel();
  });

  it('should create and save TemplateTasks', async () => {
    const nbButtonsBeforeCreate = await templateTaskComponentsPage.countDeleteButtons();

    await templateTaskComponentsPage.clickOnCreateButton();

    await promise.all([
      templateTaskUpdatePage.setOrdineEsecuzioneInput('5'),
      templateTaskUpdatePage.setNomeInput('5'),
      templateTaskUpdatePage.setNoteInput('5'),
      templateTaskUpdatePage.setPubPrivInput('5'),
      templateTaskUpdatePage.setIdTemplatePraticaRefInput('5'),
      templateTaskUpdatePage.idTemplatePraticaRefSelectLastOption(),
      templateTaskUpdatePage.idSelectLastOption(),
      templateTaskUpdatePage.templateTaskSelectLastOption(),
    ]);

    expect(await templateTaskUpdatePage.getOrdineEsecuzioneInput()).to.eq('5', 'Expected ordineEsecuzione value to be equals to 5');
    expect(await templateTaskUpdatePage.getNomeInput()).to.eq('5', 'Expected nome value to be equals to 5');
    expect(await templateTaskUpdatePage.getNoteInput()).to.eq('5', 'Expected note value to be equals to 5');
    expect(await templateTaskUpdatePage.getPubPrivInput()).to.eq('5', 'Expected pubPriv value to be equals to 5');
    expect(await templateTaskUpdatePage.getIdTemplatePraticaRefInput()).to.eq('5', 'Expected idTemplatePraticaRef value to be equals to 5');

    await templateTaskUpdatePage.save();
    expect(await templateTaskUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await templateTaskComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last TemplateTask', async () => {
    const nbButtonsBeforeDelete = await templateTaskComponentsPage.countDeleteButtons();
    await templateTaskComponentsPage.clickOnLastDeleteButton();

    templateTaskDeleteDialog = new TemplateTaskDeleteDialog();
    expect(await templateTaskDeleteDialog.getDialogTitle()).to.eq('eoloprjApp.templateTask.delete.question');
    await templateTaskDeleteDialog.clickOnConfirmButton();

    expect(await templateTaskComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
