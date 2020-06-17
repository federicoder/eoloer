import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { InvitoPraticaComponentsPage, InvitoPraticaDeleteDialog, InvitoPraticaUpdatePage } from './invito-pratica.page-object';

const expect = chai.expect;

describe('InvitoPratica e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let invitoPraticaComponentsPage: InvitoPraticaComponentsPage;
  let invitoPraticaUpdatePage: InvitoPraticaUpdatePage;
  let invitoPraticaDeleteDialog: InvitoPraticaDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load InvitoPraticas', async () => {
    await navBarPage.goToEntity('invito-pratica');
    invitoPraticaComponentsPage = new InvitoPraticaComponentsPage();
    await browser.wait(ec.visibilityOf(invitoPraticaComponentsPage.title), 5000);
    expect(await invitoPraticaComponentsPage.getTitle()).to.eq('eoloprjApp.invitoPratica.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(invitoPraticaComponentsPage.entities), ec.visibilityOf(invitoPraticaComponentsPage.noResult)),
      1000
    );
  });

  it('should load create InvitoPratica page', async () => {
    await invitoPraticaComponentsPage.clickOnCreateButton();
    invitoPraticaUpdatePage = new InvitoPraticaUpdatePage();
    expect(await invitoPraticaUpdatePage.getPageTitle()).to.eq('eoloprjApp.invitoPratica.home.createOrEditLabel');
    await invitoPraticaUpdatePage.cancel();
  });

  it('should create and save InvitoPraticas', async () => {
    const nbButtonsBeforeCreate = await invitoPraticaComponentsPage.countDeleteButtons();

    await invitoPraticaComponentsPage.clickOnCreateButton();

    await promise.all([
      invitoPraticaUpdatePage.setIdPraticaRefInput('5'),
      invitoPraticaUpdatePage.idPraticaRefSelectLastOption(),
      invitoPraticaUpdatePage.praticaSelectLastOption(),
    ]);

    expect(await invitoPraticaUpdatePage.getIdPraticaRefInput()).to.eq('5', 'Expected idPraticaRef value to be equals to 5');

    await invitoPraticaUpdatePage.save();
    expect(await invitoPraticaUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await invitoPraticaComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last InvitoPratica', async () => {
    const nbButtonsBeforeDelete = await invitoPraticaComponentsPage.countDeleteButtons();
    await invitoPraticaComponentsPage.clickOnLastDeleteButton();

    invitoPraticaDeleteDialog = new InvitoPraticaDeleteDialog();
    expect(await invitoPraticaDeleteDialog.getDialogTitle()).to.eq('eoloprjApp.invitoPratica.delete.question');
    await invitoPraticaDeleteDialog.clickOnConfirmButton();

    expect(await invitoPraticaComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
