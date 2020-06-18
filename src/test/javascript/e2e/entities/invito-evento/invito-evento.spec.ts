import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { InvitoEventoComponentsPage, InvitoEventoDeleteDialog, InvitoEventoUpdatePage } from './invito-evento.page-object';

const expect = chai.expect;

describe('InvitoEvento e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let invitoEventoComponentsPage: InvitoEventoComponentsPage;
  let invitoEventoUpdatePage: InvitoEventoUpdatePage;
  let invitoEventoDeleteDialog: InvitoEventoDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load InvitoEventos', async () => {
    await navBarPage.goToEntity('invito-evento');
    invitoEventoComponentsPage = new InvitoEventoComponentsPage();
    await browser.wait(ec.visibilityOf(invitoEventoComponentsPage.title), 5000);
    expect(await invitoEventoComponentsPage.getTitle()).to.eq('eoloprjApp.invitoEvento.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(invitoEventoComponentsPage.entities), ec.visibilityOf(invitoEventoComponentsPage.noResult)),
      1000
    );
  });

  it('should load create InvitoEvento page', async () => {
    await invitoEventoComponentsPage.clickOnCreateButton();
    invitoEventoUpdatePage = new InvitoEventoUpdatePage();
    expect(await invitoEventoUpdatePage.getPageTitle()).to.eq('eoloprjApp.invitoEvento.home.createOrEditLabel');
    await invitoEventoUpdatePage.cancel();
  });

  it('should create and save InvitoEventos', async () => {
    const nbButtonsBeforeCreate = await invitoEventoComponentsPage.countDeleteButtons();

    await invitoEventoComponentsPage.clickOnCreateButton();

    await promise.all([
      invitoEventoUpdatePage.setIdTaskRefInput('5'),
      invitoEventoUpdatePage.setLuogoFisicoInput('luogoFisico'),
      invitoEventoUpdatePage.setIndicazioniLuogoInput('indicazioniLuogo'),
      invitoEventoUpdatePage.setDataInizioInput('dataInizio'),
      invitoEventoUpdatePage.setOraInizioInput('oraInizio'),
      invitoEventoUpdatePage.setDataFineInput('dataFine'),
      invitoEventoUpdatePage.setOraFineInput('oraFine'),
      invitoEventoUpdatePage.setUrlStanzaVirtualeInput('urlStanzaVirtuale'),
      invitoEventoUpdatePage.idTaskSelectLastOption(),
      invitoEventoUpdatePage.previsioneEventoSelectLastOption(),
    ]);

    expect(await invitoEventoUpdatePage.getIdTaskRefInput()).to.eq('5', 'Expected idTaskRef value to be equals to 5');
    expect(await invitoEventoUpdatePage.getLuogoFisicoInput()).to.eq(
      'luogoFisico',
      'Expected LuogoFisico value to be equals to luogoFisico'
    );
    expect(await invitoEventoUpdatePage.getIndicazioniLuogoInput()).to.eq(
      'indicazioniLuogo',
      'Expected IndicazioniLuogo value to be equals to indicazioniLuogo'
    );
    expect(await invitoEventoUpdatePage.getDataInizioInput()).to.eq('dataInizio', 'Expected DataInizio value to be equals to dataInizio');
    expect(await invitoEventoUpdatePage.getOraInizioInput()).to.eq('oraInizio', 'Expected OraInizio value to be equals to oraInizio');
    expect(await invitoEventoUpdatePage.getDataFineInput()).to.eq('dataFine', 'Expected DataFine value to be equals to dataFine');
    expect(await invitoEventoUpdatePage.getOraFineInput()).to.eq('oraFine', 'Expected OraFine value to be equals to oraFine');
    expect(await invitoEventoUpdatePage.getUrlStanzaVirtualeInput()).to.eq(
      'urlStanzaVirtuale',
      'Expected UrlStanzaVirtuale value to be equals to urlStanzaVirtuale'
    );

    await invitoEventoUpdatePage.save();
    expect(await invitoEventoUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await invitoEventoComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last InvitoEvento', async () => {
    const nbButtonsBeforeDelete = await invitoEventoComponentsPage.countDeleteButtons();
    await invitoEventoComponentsPage.clickOnLastDeleteButton();

    invitoEventoDeleteDialog = new InvitoEventoDeleteDialog();
    expect(await invitoEventoDeleteDialog.getDialogTitle()).to.eq('eoloprjApp.invitoEvento.delete.question');
    await invitoEventoDeleteDialog.clickOnConfirmButton();

    expect(await invitoEventoComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
