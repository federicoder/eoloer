import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { DatiContabiliComponentsPage, DatiContabiliDeleteDialog, DatiContabiliUpdatePage } from './dati-contabili.page-object';

const expect = chai.expect;

describe('DatiContabili e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let datiContabiliComponentsPage: DatiContabiliComponentsPage;
  let datiContabiliUpdatePage: DatiContabiliUpdatePage;
  let datiContabiliDeleteDialog: DatiContabiliDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load DatiContabilis', async () => {
    await navBarPage.goToEntity('dati-contabili');
    datiContabiliComponentsPage = new DatiContabiliComponentsPage();
    await browser.wait(ec.visibilityOf(datiContabiliComponentsPage.title), 5000);
    expect(await datiContabiliComponentsPage.getTitle()).to.eq('eoloprjApp.datiContabili.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(datiContabiliComponentsPage.entities), ec.visibilityOf(datiContabiliComponentsPage.noResult)),
      1000
    );
  });

  it('should load create DatiContabili page', async () => {
    await datiContabiliComponentsPage.clickOnCreateButton();
    datiContabiliUpdatePage = new DatiContabiliUpdatePage();
    expect(await datiContabiliUpdatePage.getPageTitle()).to.eq('eoloprjApp.datiContabili.home.createOrEditLabel');
    await datiContabiliUpdatePage.cancel();
  });

  it('should create and save DatiContabilis', async () => {
    const nbButtonsBeforeCreate = await datiContabiliComponentsPage.countDeleteButtons();

    await datiContabiliComponentsPage.clickOnCreateButton();

    await promise.all([
      datiContabiliUpdatePage.setIdDatiContabiliInput('5'),
      datiContabiliUpdatePage.setIdPersonaRefInput('5'),
      datiContabiliUpdatePage.personaSelectLastOption(),
    ]);

    expect(await datiContabiliUpdatePage.getIdDatiContabiliInput()).to.eq('5', 'Expected idDatiContabili value to be equals to 5');
    expect(await datiContabiliUpdatePage.getIdPersonaRefInput()).to.eq('5', 'Expected idPersonaRef value to be equals to 5');

    await datiContabiliUpdatePage.save();
    expect(await datiContabiliUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await datiContabiliComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last DatiContabili', async () => {
    const nbButtonsBeforeDelete = await datiContabiliComponentsPage.countDeleteButtons();
    await datiContabiliComponentsPage.clickOnLastDeleteButton();

    datiContabiliDeleteDialog = new DatiContabiliDeleteDialog();
    expect(await datiContabiliDeleteDialog.getDialogTitle()).to.eq('eoloprjApp.datiContabili.delete.question');
    await datiContabiliDeleteDialog.clickOnConfirmButton();

    expect(await datiContabiliComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
