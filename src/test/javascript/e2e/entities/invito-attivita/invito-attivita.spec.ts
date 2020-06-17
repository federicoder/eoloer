import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { InvitoAttivitaComponentsPage, InvitoAttivitaDeleteDialog, InvitoAttivitaUpdatePage } from './invito-attivita.page-object';

const expect = chai.expect;

describe('InvitoAttivita e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let invitoAttivitaComponentsPage: InvitoAttivitaComponentsPage;
  let invitoAttivitaUpdatePage: InvitoAttivitaUpdatePage;
  let invitoAttivitaDeleteDialog: InvitoAttivitaDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load InvitoAttivitas', async () => {
    await navBarPage.goToEntity('invito-attivita');
    invitoAttivitaComponentsPage = new InvitoAttivitaComponentsPage();
    await browser.wait(ec.visibilityOf(invitoAttivitaComponentsPage.title), 5000);
    expect(await invitoAttivitaComponentsPage.getTitle()).to.eq('eoloprjApp.invitoAttivita.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(invitoAttivitaComponentsPage.entities), ec.visibilityOf(invitoAttivitaComponentsPage.noResult)),
      1000
    );
  });

  it('should load create InvitoAttivita page', async () => {
    await invitoAttivitaComponentsPage.clickOnCreateButton();
    invitoAttivitaUpdatePage = new InvitoAttivitaUpdatePage();
    expect(await invitoAttivitaUpdatePage.getPageTitle()).to.eq('eoloprjApp.invitoAttivita.home.createOrEditLabel');
    await invitoAttivitaUpdatePage.cancel();
  });

  it('should create and save InvitoAttivitas', async () => {
    const nbButtonsBeforeCreate = await invitoAttivitaComponentsPage.countDeleteButtons();

    await invitoAttivitaComponentsPage.clickOnCreateButton();

    await promise.all([invitoAttivitaUpdatePage.setIdTaskRefInput('5'), invitoAttivitaUpdatePage.idTaskRefSelectLastOption()]);

    expect(await invitoAttivitaUpdatePage.getIdTaskRefInput()).to.eq('5', 'Expected idTaskRef value to be equals to 5');

    await invitoAttivitaUpdatePage.save();
    expect(await invitoAttivitaUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await invitoAttivitaComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last InvitoAttivita', async () => {
    const nbButtonsBeforeDelete = await invitoAttivitaComponentsPage.countDeleteButtons();
    await invitoAttivitaComponentsPage.clickOnLastDeleteButton();

    invitoAttivitaDeleteDialog = new InvitoAttivitaDeleteDialog();
    expect(await invitoAttivitaDeleteDialog.getDialogTitle()).to.eq('eoloprjApp.invitoAttivita.delete.question');
    await invitoAttivitaDeleteDialog.clickOnConfirmButton();

    expect(await invitoAttivitaComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
