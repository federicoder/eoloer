import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { LineaOrdineComponentsPage, LineaOrdineDeleteDialog, LineaOrdineUpdatePage } from './linea-ordine.page-object';

const expect = chai.expect;

describe('LineaOrdine e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let lineaOrdineComponentsPage: LineaOrdineComponentsPage;
  let lineaOrdineUpdatePage: LineaOrdineUpdatePage;
  let lineaOrdineDeleteDialog: LineaOrdineDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load LineaOrdines', async () => {
    await navBarPage.goToEntity('linea-ordine');
    lineaOrdineComponentsPage = new LineaOrdineComponentsPage();
    await browser.wait(ec.visibilityOf(lineaOrdineComponentsPage.title), 5000);
    expect(await lineaOrdineComponentsPage.getTitle()).to.eq('eoloprjApp.lineaOrdine.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(lineaOrdineComponentsPage.entities), ec.visibilityOf(lineaOrdineComponentsPage.noResult)),
      1000
    );
  });

  it('should load create LineaOrdine page', async () => {
    await lineaOrdineComponentsPage.clickOnCreateButton();
    lineaOrdineUpdatePage = new LineaOrdineUpdatePage();
    expect(await lineaOrdineUpdatePage.getPageTitle()).to.eq('eoloprjApp.lineaOrdine.home.createOrEditLabel');
    await lineaOrdineUpdatePage.cancel();
  });

  it('should create and save LineaOrdines', async () => {
    const nbButtonsBeforeCreate = await lineaOrdineComponentsPage.countDeleteButtons();

    await lineaOrdineComponentsPage.clickOnCreateButton();

    await promise.all([
      lineaOrdineUpdatePage.setIdOrdineInput('5'),
      lineaOrdineUpdatePage.setIdProdottoInput('5'),
      lineaOrdineUpdatePage.setQuantitaInput('5'),
      lineaOrdineUpdatePage.setImportoInput('5'),
      lineaOrdineUpdatePage.setCodIvaInput('codIva'),
      lineaOrdineUpdatePage.ordineSelectLastOption(),
    ]);

    expect(await lineaOrdineUpdatePage.getIdOrdineInput()).to.eq('5', 'Expected idOrdine value to be equals to 5');
    expect(await lineaOrdineUpdatePage.getIdProdottoInput()).to.eq('5', 'Expected idProdotto value to be equals to 5');
    expect(await lineaOrdineUpdatePage.getQuantitaInput()).to.eq('5', 'Expected quantita value to be equals to 5');
    expect(await lineaOrdineUpdatePage.getImportoInput()).to.eq('5', 'Expected importo value to be equals to 5');
    expect(await lineaOrdineUpdatePage.getCodIvaInput()).to.eq('codIva', 'Expected CodIva value to be equals to codIva');

    await lineaOrdineUpdatePage.save();
    expect(await lineaOrdineUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await lineaOrdineComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last LineaOrdine', async () => {
    const nbButtonsBeforeDelete = await lineaOrdineComponentsPage.countDeleteButtons();
    await lineaOrdineComponentsPage.clickOnLastDeleteButton();

    lineaOrdineDeleteDialog = new LineaOrdineDeleteDialog();
    expect(await lineaOrdineDeleteDialog.getDialogTitle()).to.eq('eoloprjApp.lineaOrdine.delete.question');
    await lineaOrdineDeleteDialog.clickOnConfirmButton();

    expect(await lineaOrdineComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
