import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { PrevisioneEventoComponentsPage, PrevisioneEventoDeleteDialog, PrevisioneEventoUpdatePage } from './previsione-evento.page-object';

const expect = chai.expect;

describe('PrevisioneEvento e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let previsioneEventoComponentsPage: PrevisioneEventoComponentsPage;
  let previsioneEventoUpdatePage: PrevisioneEventoUpdatePage;
  let previsioneEventoDeleteDialog: PrevisioneEventoDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load PrevisioneEventos', async () => {
    await navBarPage.goToEntity('previsione-evento');
    previsioneEventoComponentsPage = new PrevisioneEventoComponentsPage();
    await browser.wait(ec.visibilityOf(previsioneEventoComponentsPage.title), 5000);
    expect(await previsioneEventoComponentsPage.getTitle()).to.eq('eoloprjApp.previsioneEvento.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(previsioneEventoComponentsPage.entities), ec.visibilityOf(previsioneEventoComponentsPage.noResult)),
      1000
    );
  });

  it('should load create PrevisioneEvento page', async () => {
    await previsioneEventoComponentsPage.clickOnCreateButton();
    previsioneEventoUpdatePage = new PrevisioneEventoUpdatePage();
    expect(await previsioneEventoUpdatePage.getPageTitle()).to.eq('eoloprjApp.previsioneEvento.home.createOrEditLabel');
    await previsioneEventoUpdatePage.cancel();
  });

  it('should create and save PrevisioneEventos', async () => {
    const nbButtonsBeforeCreate = await previsioneEventoComponentsPage.countDeleteButtons();

    await previsioneEventoComponentsPage.clickOnCreateButton();

    await promise.all([
      previsioneEventoUpdatePage.setIdTaskInput('5'),
      previsioneEventoUpdatePage.setDataInizioInput('dataInizio'),
      previsioneEventoUpdatePage.setDataFineInput('dataFine'),
      previsioneEventoUpdatePage.setLuogoInput('luogo'),
      previsioneEventoUpdatePage.setIndicazioniLuogoInput('indicazioniLuogo'),
      previsioneEventoUpdatePage.setVersionInput('version'),
      previsioneEventoUpdatePage.idTaskSelectLastOption(),
    ]);

    expect(await previsioneEventoUpdatePage.getIdTaskInput()).to.eq('5', 'Expected idTask value to be equals to 5');
    expect(await previsioneEventoUpdatePage.getDataInizioInput()).to.eq(
      'dataInizio',
      'Expected DataInizio value to be equals to dataInizio'
    );
    expect(await previsioneEventoUpdatePage.getDataFineInput()).to.eq('dataFine', 'Expected DataFine value to be equals to dataFine');
    expect(await previsioneEventoUpdatePage.getLuogoInput()).to.eq('luogo', 'Expected Luogo value to be equals to luogo');
    expect(await previsioneEventoUpdatePage.getIndicazioniLuogoInput()).to.eq(
      'indicazioniLuogo',
      'Expected IndicazioniLuogo value to be equals to indicazioniLuogo'
    );
    expect(await previsioneEventoUpdatePage.getVersionInput()).to.eq('version', 'Expected Version value to be equals to version');

    await previsioneEventoUpdatePage.save();
    expect(await previsioneEventoUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await previsioneEventoComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last PrevisioneEvento', async () => {
    const nbButtonsBeforeDelete = await previsioneEventoComponentsPage.countDeleteButtons();
    await previsioneEventoComponentsPage.clickOnLastDeleteButton();

    previsioneEventoDeleteDialog = new PrevisioneEventoDeleteDialog();
    expect(await previsioneEventoDeleteDialog.getDialogTitle()).to.eq('eoloprjApp.previsioneEvento.delete.question');
    await previsioneEventoDeleteDialog.clickOnConfirmButton();

    expect(await previsioneEventoComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
