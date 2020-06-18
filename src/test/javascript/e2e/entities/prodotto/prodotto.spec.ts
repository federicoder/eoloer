import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ProdottoComponentsPage, ProdottoDeleteDialog, ProdottoUpdatePage } from './prodotto.page-object';

const expect = chai.expect;

describe('Prodotto e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let prodottoComponentsPage: ProdottoComponentsPage;
  let prodottoUpdatePage: ProdottoUpdatePage;
  let prodottoDeleteDialog: ProdottoDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Prodottos', async () => {
    await navBarPage.goToEntity('prodotto');
    prodottoComponentsPage = new ProdottoComponentsPage();
    await browser.wait(ec.visibilityOf(prodottoComponentsPage.title), 5000);
    expect(await prodottoComponentsPage.getTitle()).to.eq('eoloprjApp.prodotto.home.title');
    await browser.wait(ec.or(ec.visibilityOf(prodottoComponentsPage.entities), ec.visibilityOf(prodottoComponentsPage.noResult)), 1000);
  });

  it('should load create Prodotto page', async () => {
    await prodottoComponentsPage.clickOnCreateButton();
    prodottoUpdatePage = new ProdottoUpdatePage();
    expect(await prodottoUpdatePage.getPageTitle()).to.eq('eoloprjApp.prodotto.home.createOrEditLabel');
    await prodottoUpdatePage.cancel();
  });

  it('should create and save Prodottos', async () => {
    const nbButtonsBeforeCreate = await prodottoComponentsPage.countDeleteButtons();

    await prodottoComponentsPage.clickOnCreateButton();

    await promise.all([
      prodottoUpdatePage.setNuovaLicenzaInput('5'),
      prodottoUpdatePage.setRinnovoLicenzaInput('5'),
      prodottoUpdatePage.setStorageInput('5'),
    ]);

    expect(await prodottoUpdatePage.getNuovaLicenzaInput()).to.eq('5', 'Expected nuovaLicenza value to be equals to 5');
    expect(await prodottoUpdatePage.getRinnovoLicenzaInput()).to.eq('5', 'Expected rinnovoLicenza value to be equals to 5');
    expect(await prodottoUpdatePage.getStorageInput()).to.eq('5', 'Expected storage value to be equals to 5');

    await prodottoUpdatePage.save();
    expect(await prodottoUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await prodottoComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Prodotto', async () => {
    const nbButtonsBeforeDelete = await prodottoComponentsPage.countDeleteButtons();
    await prodottoComponentsPage.clickOnLastDeleteButton();

    prodottoDeleteDialog = new ProdottoDeleteDialog();
    expect(await prodottoDeleteDialog.getDialogTitle()).to.eq('eoloprjApp.prodotto.delete.question');
    await prodottoDeleteDialog.clickOnConfirmButton();

    expect(await prodottoComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
