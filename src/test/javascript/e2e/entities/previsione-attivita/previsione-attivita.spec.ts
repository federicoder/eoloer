import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  PrevisioneAttivitaComponentsPage,
  PrevisioneAttivitaDeleteDialog,
  PrevisioneAttivitaUpdatePage,
} from './previsione-attivita.page-object';

const expect = chai.expect;

describe('PrevisioneAttivita e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let previsioneAttivitaComponentsPage: PrevisioneAttivitaComponentsPage;
  let previsioneAttivitaUpdatePage: PrevisioneAttivitaUpdatePage;
  let previsioneAttivitaDeleteDialog: PrevisioneAttivitaDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load PrevisioneAttivitas', async () => {
    await navBarPage.goToEntity('previsione-attivita');
    previsioneAttivitaComponentsPage = new PrevisioneAttivitaComponentsPage();
    await browser.wait(ec.visibilityOf(previsioneAttivitaComponentsPage.title), 5000);
    expect(await previsioneAttivitaComponentsPage.getTitle()).to.eq('eoloprjApp.previsioneAttivita.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(previsioneAttivitaComponentsPage.entities), ec.visibilityOf(previsioneAttivitaComponentsPage.noResult)),
      1000
    );
  });

  it('should load create PrevisioneAttivita page', async () => {
    await previsioneAttivitaComponentsPage.clickOnCreateButton();
    previsioneAttivitaUpdatePage = new PrevisioneAttivitaUpdatePage();
    expect(await previsioneAttivitaUpdatePage.getPageTitle()).to.eq('eoloprjApp.previsioneAttivita.home.createOrEditLabel');
    await previsioneAttivitaUpdatePage.cancel();
  });

  it('should create and save PrevisioneAttivitas', async () => {
    const nbButtonsBeforeCreate = await previsioneAttivitaComponentsPage.countDeleteButtons();

    await previsioneAttivitaComponentsPage.clickOnCreateButton();

    await promise.all([
      previsioneAttivitaUpdatePage.setIdTaskRefInput('5'),
      previsioneAttivitaUpdatePage.setDataPianificataInput('dataPianificata'),
      previsioneAttivitaUpdatePage.setOraPianificataInput('oraPianificata'),
      previsioneAttivitaUpdatePage.setDataScadenzaInput('dataScadenza'),
      previsioneAttivitaUpdatePage.setVersionInput('version'),
      previsioneAttivitaUpdatePage.idPrevisioneAttivitaSelectLastOption(),
    ]);

    expect(await previsioneAttivitaUpdatePage.getIdTaskRefInput()).to.eq('5', 'Expected idTaskRef value to be equals to 5');
    expect(await previsioneAttivitaUpdatePage.getDataPianificataInput()).to.eq(
      'dataPianificata',
      'Expected DataPianificata value to be equals to dataPianificata'
    );
    expect(await previsioneAttivitaUpdatePage.getOraPianificataInput()).to.eq(
      'oraPianificata',
      'Expected OraPianificata value to be equals to oraPianificata'
    );
    expect(await previsioneAttivitaUpdatePage.getDataScadenzaInput()).to.eq(
      'dataScadenza',
      'Expected DataScadenza value to be equals to dataScadenza'
    );
    expect(await previsioneAttivitaUpdatePage.getVersionInput()).to.eq('version', 'Expected Version value to be equals to version');

    await previsioneAttivitaUpdatePage.save();
    expect(await previsioneAttivitaUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await previsioneAttivitaComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last PrevisioneAttivita', async () => {
    const nbButtonsBeforeDelete = await previsioneAttivitaComponentsPage.countDeleteButtons();
    await previsioneAttivitaComponentsPage.clickOnLastDeleteButton();

    previsioneAttivitaDeleteDialog = new PrevisioneAttivitaDeleteDialog();
    expect(await previsioneAttivitaDeleteDialog.getDialogTitle()).to.eq('eoloprjApp.previsioneAttivita.delete.question');
    await previsioneAttivitaDeleteDialog.clickOnConfirmButton();

    expect(await previsioneAttivitaComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
