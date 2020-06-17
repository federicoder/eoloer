import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { TemplatePraticaComponentsPage, TemplatePraticaDeleteDialog, TemplatePraticaUpdatePage } from './template-pratica.page-object';

const expect = chai.expect;

describe('TemplatePratica e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let templatePraticaComponentsPage: TemplatePraticaComponentsPage;
  let templatePraticaUpdatePage: TemplatePraticaUpdatePage;
  let templatePraticaDeleteDialog: TemplatePraticaDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load TemplatePraticas', async () => {
    await navBarPage.goToEntity('template-pratica');
    templatePraticaComponentsPage = new TemplatePraticaComponentsPage();
    await browser.wait(ec.visibilityOf(templatePraticaComponentsPage.title), 5000);
    expect(await templatePraticaComponentsPage.getTitle()).to.eq('eoloprjApp.templatePratica.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(templatePraticaComponentsPage.entities), ec.visibilityOf(templatePraticaComponentsPage.noResult)),
      1000
    );
  });

  it('should load create TemplatePratica page', async () => {
    await templatePraticaComponentsPage.clickOnCreateButton();
    templatePraticaUpdatePage = new TemplatePraticaUpdatePage();
    expect(await templatePraticaUpdatePage.getPageTitle()).to.eq('eoloprjApp.templatePratica.home.createOrEditLabel');
    await templatePraticaUpdatePage.cancel();
  });

  it('should create and save TemplatePraticas', async () => {
    const nbButtonsBeforeCreate = await templatePraticaComponentsPage.countDeleteButtons();

    await templatePraticaComponentsPage.clickOnCreateButton();

    await promise.all([
      templatePraticaUpdatePage.setIdTemplateInput('5'),
      templatePraticaUpdatePage.setNomeTemplateInput('5'),
      templatePraticaUpdatePage.setElencoTagAmbitoInput('5'),
    ]);

    expect(await templatePraticaUpdatePage.getIdTemplateInput()).to.eq('5', 'Expected idTemplate value to be equals to 5');
    expect(await templatePraticaUpdatePage.getNomeTemplateInput()).to.eq('5', 'Expected nomeTemplate value to be equals to 5');
    expect(await templatePraticaUpdatePage.getElencoTagAmbitoInput()).to.eq('5', 'Expected elencoTagAmbito value to be equals to 5');

    await templatePraticaUpdatePage.save();
    expect(await templatePraticaUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await templatePraticaComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last TemplatePratica', async () => {
    const nbButtonsBeforeDelete = await templatePraticaComponentsPage.countDeleteButtons();
    await templatePraticaComponentsPage.clickOnLastDeleteButton();

    templatePraticaDeleteDialog = new TemplatePraticaDeleteDialog();
    expect(await templatePraticaDeleteDialog.getDialogTitle()).to.eq('eoloprjApp.templatePratica.delete.question');
    await templatePraticaDeleteDialog.clickOnConfirmButton();

    expect(await templatePraticaComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
