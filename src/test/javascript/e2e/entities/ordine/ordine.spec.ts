import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { OrdineComponentsPage, OrdineDeleteDialog, OrdineUpdatePage } from './ordine.page-object';

const expect = chai.expect;

describe('Ordine e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let ordineComponentsPage: OrdineComponentsPage;
  let ordineUpdatePage: OrdineUpdatePage;
  let ordineDeleteDialog: OrdineDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Ordines', async () => {
    await navBarPage.goToEntity('ordine');
    ordineComponentsPage = new OrdineComponentsPage();
    await browser.wait(ec.visibilityOf(ordineComponentsPage.title), 5000);
    expect(await ordineComponentsPage.getTitle()).to.eq('eoloprjApp.ordine.home.title');
    await browser.wait(ec.or(ec.visibilityOf(ordineComponentsPage.entities), ec.visibilityOf(ordineComponentsPage.noResult)), 1000);
  });

  it('should load create Ordine page', async () => {
    await ordineComponentsPage.clickOnCreateButton();
    ordineUpdatePage = new OrdineUpdatePage();
    expect(await ordineUpdatePage.getPageTitle()).to.eq('eoloprjApp.ordine.home.createOrEditLabel');
    await ordineUpdatePage.cancel();
  });

  it('should create and save Ordines', async () => {
    const nbButtonsBeforeCreate = await ordineComponentsPage.countDeleteButtons();

    await ordineComponentsPage.clickOnCreateButton();

    await promise.all([
      ordineUpdatePage.setIdStudioProfessionaleInput('5'),
      ordineUpdatePage.setStatoOrdineInput('5'),
      ordineUpdatePage.setTotImponibileInput('5'),
      ordineUpdatePage.setTotIvaInput('5'),
      ordineUpdatePage.setTotOrdineInput('5'),
      ordineUpdatePage.studioProfessionaleSelectLastOption(),
    ]);

    expect(await ordineUpdatePage.getIdStudioProfessionaleInput()).to.eq('5', 'Expected idStudioProfessionale value to be equals to 5');
    expect(await ordineUpdatePage.getStatoOrdineInput()).to.eq('5', 'Expected statoOrdine value to be equals to 5');
    expect(await ordineUpdatePage.getTotImponibileInput()).to.eq('5', 'Expected totImponibile value to be equals to 5');
    expect(await ordineUpdatePage.getTotIvaInput()).to.eq('5', 'Expected totIva value to be equals to 5');
    expect(await ordineUpdatePage.getTotOrdineInput()).to.eq('5', 'Expected totOrdine value to be equals to 5');

    await ordineUpdatePage.save();
    expect(await ordineUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await ordineComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Ordine', async () => {
    const nbButtonsBeforeDelete = await ordineComponentsPage.countDeleteButtons();
    await ordineComponentsPage.clickOnLastDeleteButton();

    ordineDeleteDialog = new OrdineDeleteDialog();
    expect(await ordineDeleteDialog.getDialogTitle()).to.eq('eoloprjApp.ordine.delete.question');
    await ordineDeleteDialog.clickOnConfirmButton();

    expect(await ordineComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
