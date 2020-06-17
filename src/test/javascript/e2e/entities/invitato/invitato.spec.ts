import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { InvitatoComponentsPage, InvitatoDeleteDialog, InvitatoUpdatePage } from './invitato.page-object';

const expect = chai.expect;

describe('Invitato e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let invitatoComponentsPage: InvitatoComponentsPage;
  let invitatoUpdatePage: InvitatoUpdatePage;
  let invitatoDeleteDialog: InvitatoDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Invitatoes', async () => {
    await navBarPage.goToEntity('invitato');
    invitatoComponentsPage = new InvitatoComponentsPage();
    await browser.wait(ec.visibilityOf(invitatoComponentsPage.title), 5000);
    expect(await invitatoComponentsPage.getTitle()).to.eq('eoloprjApp.invitato.home.title');
    await browser.wait(ec.or(ec.visibilityOf(invitatoComponentsPage.entities), ec.visibilityOf(invitatoComponentsPage.noResult)), 1000);
  });

  it('should load create Invitato page', async () => {
    await invitatoComponentsPage.clickOnCreateButton();
    invitatoUpdatePage = new InvitatoUpdatePage();
    expect(await invitatoUpdatePage.getPageTitle()).to.eq('eoloprjApp.invitato.home.createOrEditLabel');
    await invitatoUpdatePage.cancel();
  });

  it('should create and save Invitatoes', async () => {
    const nbButtonsBeforeCreate = await invitatoComponentsPage.countDeleteButtons();

    await invitatoComponentsPage.clickOnCreateButton();

    await promise.all([
      invitatoUpdatePage.setIdInvitatoInput('5'),
      invitatoUpdatePage.setIdInvitoRefInput('5'),
      invitatoUpdatePage.setTokenInvitoInput('tokenInvito'),
      invitatoUpdatePage.setCanalePrimarioInvitoInput('5'),
      invitatoUpdatePage.setCanaleBackupInvitoInput('5'),
      invitatoUpdatePage.setStatoInvitoInput('5'),
      invitatoUpdatePage.setIdUserInvitatoInput('5'),
      invitatoUpdatePage.setIdPersonaInvitataInput('5'),
      invitatoUpdatePage.setNomeUserInvitatoInput('nomeUserInvitato'),
      invitatoUpdatePage.setDataRispostaInvitoInput('dataRispostaInvito'),
      invitatoUpdatePage.setRuoloInvitatoInput('5'),
      invitatoUpdatePage.setIndInvitatiInput('5'),
      invitatoUpdatePage.userPersonaSelectLastOption(),
      invitatoUpdatePage.invitoSelectLastOption(),
    ]);

    expect(await invitatoUpdatePage.getIdInvitatoInput()).to.eq('5', 'Expected idInvitato value to be equals to 5');
    expect(await invitatoUpdatePage.getIdInvitoRefInput()).to.eq('5', 'Expected idInvitoRef value to be equals to 5');
    expect(await invitatoUpdatePage.getTokenInvitoInput()).to.eq('tokenInvito', 'Expected TokenInvito value to be equals to tokenInvito');
    expect(await invitatoUpdatePage.getCanalePrimarioInvitoInput()).to.eq('5', 'Expected canalePrimarioInvito value to be equals to 5');
    expect(await invitatoUpdatePage.getCanaleBackupInvitoInput()).to.eq('5', 'Expected canaleBackupInvito value to be equals to 5');
    expect(await invitatoUpdatePage.getStatoInvitoInput()).to.eq('5', 'Expected statoInvito value to be equals to 5');
    expect(await invitatoUpdatePage.getIdUserInvitatoInput()).to.eq('5', 'Expected idUserInvitato value to be equals to 5');
    expect(await invitatoUpdatePage.getIdPersonaInvitataInput()).to.eq('5', 'Expected idPersonaInvitata value to be equals to 5');
    expect(await invitatoUpdatePage.getNomeUserInvitatoInput()).to.eq(
      'nomeUserInvitato',
      'Expected NomeUserInvitato value to be equals to nomeUserInvitato'
    );
    expect(await invitatoUpdatePage.getDataRispostaInvitoInput()).to.eq(
      'dataRispostaInvito',
      'Expected DataRispostaInvito value to be equals to dataRispostaInvito'
    );
    expect(await invitatoUpdatePage.getRuoloInvitatoInput()).to.eq('5', 'Expected ruoloInvitato value to be equals to 5');
    expect(await invitatoUpdatePage.getIndInvitatiInput()).to.eq('5', 'Expected indInvitati value to be equals to 5');

    await invitatoUpdatePage.save();
    expect(await invitatoUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await invitatoComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Invitato', async () => {
    const nbButtonsBeforeDelete = await invitatoComponentsPage.countDeleteButtons();
    await invitatoComponentsPage.clickOnLastDeleteButton();

    invitatoDeleteDialog = new InvitatoDeleteDialog();
    expect(await invitatoDeleteDialog.getDialogTitle()).to.eq('eoloprjApp.invitato.delete.question');
    await invitatoDeleteDialog.clickOnConfirmButton();

    expect(await invitatoComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
