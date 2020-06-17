import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { InvitoComponentsPage, InvitoDeleteDialog, InvitoUpdatePage } from './invito.page-object';

const expect = chai.expect;

describe('Invito e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let invitoComponentsPage: InvitoComponentsPage;
  let invitoUpdatePage: InvitoUpdatePage;
  let invitoDeleteDialog: InvitoDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Invitos', async () => {
    await navBarPage.goToEntity('invito');
    invitoComponentsPage = new InvitoComponentsPage();
    await browser.wait(ec.visibilityOf(invitoComponentsPage.title), 5000);
    expect(await invitoComponentsPage.getTitle()).to.eq('eoloprjApp.invito.home.title');
    await browser.wait(ec.or(ec.visibilityOf(invitoComponentsPage.entities), ec.visibilityOf(invitoComponentsPage.noResult)), 1000);
  });

  it('should load create Invito page', async () => {
    await invitoComponentsPage.clickOnCreateButton();
    invitoUpdatePage = new InvitoUpdatePage();
    expect(await invitoUpdatePage.getPageTitle()).to.eq('eoloprjApp.invito.home.createOrEditLabel');
    await invitoUpdatePage.cancel();
  });

  it('should create and save Invitos', async () => {
    const nbButtonsBeforeCreate = await invitoComponentsPage.countDeleteButtons();

    await invitoComponentsPage.clickOnCreateButton();

    await promise.all([
      invitoUpdatePage.setIdStudioProfessionaleRefInput('5'),
      invitoUpdatePage.setDataInvitoInput('dataInvito'),
      invitoUpdatePage.setIdUserInvitanteInput('5'),
      invitoUpdatePage.setNomeUserInvitanteInput('nomeUserInvitante'),
      invitoUpdatePage.setDataScadenzaInvitoInput('dataScadenzaInvito'),
      invitoUpdatePage.setTestoInvitoInput('testoInvito'),
      invitoUpdatePage.setIdPraticaRefInput('5'),
      invitoUpdatePage.setIdTaskRefInput('5'),
      invitoUpdatePage.setLuogoFisicoInput('luogoFisico'),
      invitoUpdatePage.setIndicazioniLuogoInput('indicazioniLuogo'),
      invitoUpdatePage.setDataInizioInput('dataInizio'),
      invitoUpdatePage.setOraInizioInput('oraInizio'),
      invitoUpdatePage.setDataFineInput('dataFine'),
      invitoUpdatePage.setOraFineInput('oraFine'),
      invitoUpdatePage.setUrlStanzaVirtualeInput('urlStanzaVirtuale'),
      invitoUpdatePage.setDiscriminatorInput('discriminator'),
      invitoUpdatePage.idStudioProfessionaleRefSelectLastOption(),
      invitoUpdatePage.assegnazioneTaskSelectLastOption(),
    ]);

    expect(await invitoUpdatePage.getIdStudioProfessionaleRefInput()).to.eq(
      '5',
      'Expected idStudioProfessionaleRef value to be equals to 5'
    );
    expect(await invitoUpdatePage.getDataInvitoInput()).to.eq('dataInvito', 'Expected DataInvito value to be equals to dataInvito');
    expect(await invitoUpdatePage.getIdUserInvitanteInput()).to.eq('5', 'Expected idUserInvitante value to be equals to 5');
    expect(await invitoUpdatePage.getNomeUserInvitanteInput()).to.eq(
      'nomeUserInvitante',
      'Expected NomeUserInvitante value to be equals to nomeUserInvitante'
    );
    expect(await invitoUpdatePage.getDataScadenzaInvitoInput()).to.eq(
      'dataScadenzaInvito',
      'Expected DataScadenzaInvito value to be equals to dataScadenzaInvito'
    );
    expect(await invitoUpdatePage.getTestoInvitoInput()).to.eq('testoInvito', 'Expected TestoInvito value to be equals to testoInvito');
    expect(await invitoUpdatePage.getIdPraticaRefInput()).to.eq('5', 'Expected idPraticaRef value to be equals to 5');
    expect(await invitoUpdatePage.getIdTaskRefInput()).to.eq('5', 'Expected idTaskRef value to be equals to 5');
    expect(await invitoUpdatePage.getLuogoFisicoInput()).to.eq('luogoFisico', 'Expected LuogoFisico value to be equals to luogoFisico');
    expect(await invitoUpdatePage.getIndicazioniLuogoInput()).to.eq(
      'indicazioniLuogo',
      'Expected IndicazioniLuogo value to be equals to indicazioniLuogo'
    );
    expect(await invitoUpdatePage.getDataInizioInput()).to.eq('dataInizio', 'Expected DataInizio value to be equals to dataInizio');
    expect(await invitoUpdatePage.getOraInizioInput()).to.eq('oraInizio', 'Expected OraInizio value to be equals to oraInizio');
    expect(await invitoUpdatePage.getDataFineInput()).to.eq('dataFine', 'Expected DataFine value to be equals to dataFine');
    expect(await invitoUpdatePage.getOraFineInput()).to.eq('oraFine', 'Expected OraFine value to be equals to oraFine');
    expect(await invitoUpdatePage.getUrlStanzaVirtualeInput()).to.eq(
      'urlStanzaVirtuale',
      'Expected UrlStanzaVirtuale value to be equals to urlStanzaVirtuale'
    );
    expect(await invitoUpdatePage.getDiscriminatorInput()).to.eq(
      'discriminator',
      'Expected Discriminator value to be equals to discriminator'
    );

    await invitoUpdatePage.save();
    expect(await invitoUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await invitoComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Invito', async () => {
    const nbButtonsBeforeDelete = await invitoComponentsPage.countDeleteButtons();
    await invitoComponentsPage.clickOnLastDeleteButton();

    invitoDeleteDialog = new InvitoDeleteDialog();
    expect(await invitoDeleteDialog.getDialogTitle()).to.eq('eoloprjApp.invito.delete.question');
    await invitoDeleteDialog.clickOnConfirmButton();

    expect(await invitoComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
