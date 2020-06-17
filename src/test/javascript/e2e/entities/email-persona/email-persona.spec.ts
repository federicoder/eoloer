import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { EmailPersonaComponentsPage, EmailPersonaDeleteDialog, EmailPersonaUpdatePage } from './email-persona.page-object';

const expect = chai.expect;

describe('EmailPersona e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let emailPersonaComponentsPage: EmailPersonaComponentsPage;
  let emailPersonaUpdatePage: EmailPersonaUpdatePage;
  let emailPersonaDeleteDialog: EmailPersonaDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load EmailPersonas', async () => {
    await navBarPage.goToEntity('email-persona');
    emailPersonaComponentsPage = new EmailPersonaComponentsPage();
    await browser.wait(ec.visibilityOf(emailPersonaComponentsPage.title), 5000);
    expect(await emailPersonaComponentsPage.getTitle()).to.eq('eoloprjApp.emailPersona.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(emailPersonaComponentsPage.entities), ec.visibilityOf(emailPersonaComponentsPage.noResult)),
      1000
    );
  });

  it('should load create EmailPersona page', async () => {
    await emailPersonaComponentsPage.clickOnCreateButton();
    emailPersonaUpdatePage = new EmailPersonaUpdatePage();
    expect(await emailPersonaUpdatePage.getPageTitle()).to.eq('eoloprjApp.emailPersona.home.createOrEditLabel');
    await emailPersonaUpdatePage.cancel();
  });

  it('should create and save EmailPersonas', async () => {
    const nbButtonsBeforeCreate = await emailPersonaComponentsPage.countDeleteButtons();

    await emailPersonaComponentsPage.clickOnCreateButton();

    await promise.all([
      emailPersonaUpdatePage.setIdPersonaRefInput('5'),
      emailPersonaUpdatePage.setEtichettaInput('5'),
      emailPersonaUpdatePage.setNumeroInput('5'),
      emailPersonaUpdatePage.idPersonaRefSelectLastOption(),
    ]);

    expect(await emailPersonaUpdatePage.getIdPersonaRefInput()).to.eq('5', 'Expected idPersonaRef value to be equals to 5');
    expect(await emailPersonaUpdatePage.getEtichettaInput()).to.eq('5', 'Expected etichetta value to be equals to 5');
    expect(await emailPersonaUpdatePage.getNumeroInput()).to.eq('5', 'Expected numero value to be equals to 5');

    await emailPersonaUpdatePage.save();
    expect(await emailPersonaUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await emailPersonaComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last EmailPersona', async () => {
    const nbButtonsBeforeDelete = await emailPersonaComponentsPage.countDeleteButtons();
    await emailPersonaComponentsPage.clickOnLastDeleteButton();

    emailPersonaDeleteDialog = new EmailPersonaDeleteDialog();
    expect(await emailPersonaDeleteDialog.getDialogTitle()).to.eq('eoloprjApp.emailPersona.delete.question');
    await emailPersonaDeleteDialog.clickOnConfirmButton();

    expect(await emailPersonaComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
