import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { PraticaComponentsPage, PraticaDeleteDialog, PraticaUpdatePage } from './pratica.page-object';

const expect = chai.expect;

describe('Pratica e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let praticaComponentsPage: PraticaComponentsPage;
  let praticaUpdatePage: PraticaUpdatePage;
  let praticaDeleteDialog: PraticaDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Praticas', async () => {
    await navBarPage.goToEntity('pratica');
    praticaComponentsPage = new PraticaComponentsPage();
    await browser.wait(ec.visibilityOf(praticaComponentsPage.title), 5000);
    expect(await praticaComponentsPage.getTitle()).to.eq('eoloprjApp.pratica.home.title');
    await browser.wait(ec.or(ec.visibilityOf(praticaComponentsPage.entities), ec.visibilityOf(praticaComponentsPage.noResult)), 1000);
  });

  it('should load create Pratica page', async () => {
    await praticaComponentsPage.clickOnCreateButton();
    praticaUpdatePage = new PraticaUpdatePage();
    expect(await praticaUpdatePage.getPageTitle()).to.eq('eoloprjApp.pratica.home.createOrEditLabel');
    await praticaUpdatePage.cancel();
  });

  it('should create and save Praticas', async () => {
    const nbButtonsBeforeCreate = await praticaComponentsPage.countDeleteButtons();

    await praticaComponentsPage.clickOnCreateButton();

    await promise.all([
      praticaUpdatePage.setIdStudioInput('5'),
      praticaUpdatePage.setNumeroInput('numero'),
      praticaUpdatePage.setNomeInput('nome'),
      praticaUpdatePage.setDataAperturaInput('dataApertura'),
      praticaUpdatePage.setDataChiusuraInput('dataChiusura'),
      praticaUpdatePage.setDataScadenzaInput('dataScadenza'),
      praticaUpdatePage.setStatoInput('5'),
      praticaUpdatePage.setMotivoChiusuraInput('motivoChiusura'),
      praticaUpdatePage.setIdTitolareInput('5'),
      praticaUpdatePage.setPrcAvanzatoInput('5'),
      praticaUpdatePage.setVersionInput('version'),
      praticaUpdatePage.setValutaInput('valuta'),
      praticaUpdatePage.setIdTemplatePraticaInput('5'),
      praticaUpdatePage.idTemplateSelectLastOption(),
    ]);

    expect(await praticaUpdatePage.getIdStudioInput()).to.eq('5', 'Expected idStudio value to be equals to 5');
    expect(await praticaUpdatePage.getNumeroInput()).to.eq('numero', 'Expected Numero value to be equals to numero');
    expect(await praticaUpdatePage.getNomeInput()).to.eq('nome', 'Expected Nome value to be equals to nome');
    expect(await praticaUpdatePage.getDataAperturaInput()).to.eq(
      'dataApertura',
      'Expected DataApertura value to be equals to dataApertura'
    );
    expect(await praticaUpdatePage.getDataChiusuraInput()).to.eq(
      'dataChiusura',
      'Expected DataChiusura value to be equals to dataChiusura'
    );
    expect(await praticaUpdatePage.getDataScadenzaInput()).to.eq(
      'dataScadenza',
      'Expected DataScadenza value to be equals to dataScadenza'
    );
    expect(await praticaUpdatePage.getStatoInput()).to.eq('5', 'Expected stato value to be equals to 5');
    expect(await praticaUpdatePage.getMotivoChiusuraInput()).to.eq(
      'motivoChiusura',
      'Expected MotivoChiusura value to be equals to motivoChiusura'
    );
    expect(await praticaUpdatePage.getIdTitolareInput()).to.eq('5', 'Expected idTitolare value to be equals to 5');
    expect(await praticaUpdatePage.getPrcAvanzatoInput()).to.eq('5', 'Expected prcAvanzato value to be equals to 5');
    expect(await praticaUpdatePage.getVersionInput()).to.eq('version', 'Expected Version value to be equals to version');
    expect(await praticaUpdatePage.getValutaInput()).to.eq('valuta', 'Expected Valuta value to be equals to valuta');
    expect(await praticaUpdatePage.getIdTemplatePraticaInput()).to.eq('5', 'Expected idTemplatePratica value to be equals to 5');

    await praticaUpdatePage.save();
    expect(await praticaUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await praticaComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Pratica', async () => {
    const nbButtonsBeforeDelete = await praticaComponentsPage.countDeleteButtons();
    await praticaComponentsPage.clickOnLastDeleteButton();

    praticaDeleteDialog = new PraticaDeleteDialog();
    expect(await praticaDeleteDialog.getDialogTitle()).to.eq('eoloprjApp.pratica.delete.question');
    await praticaDeleteDialog.clickOnConfirmButton();

    expect(await praticaComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
