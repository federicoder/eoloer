import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { AssegnazioneTaskComponentsPage, AssegnazioneTaskDeleteDialog, AssegnazioneTaskUpdatePage } from './assegnazione-task.page-object';

const expect = chai.expect;

describe('AssegnazioneTask e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let assegnazioneTaskComponentsPage: AssegnazioneTaskComponentsPage;
  let assegnazioneTaskUpdatePage: AssegnazioneTaskUpdatePage;
  let assegnazioneTaskDeleteDialog: AssegnazioneTaskDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load AssegnazioneTasks', async () => {
    await navBarPage.goToEntity('assegnazione-task');
    assegnazioneTaskComponentsPage = new AssegnazioneTaskComponentsPage();
    await browser.wait(ec.visibilityOf(assegnazioneTaskComponentsPage.title), 5000);
    expect(await assegnazioneTaskComponentsPage.getTitle()).to.eq('eoloprjApp.assegnazioneTask.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(assegnazioneTaskComponentsPage.entities), ec.visibilityOf(assegnazioneTaskComponentsPage.noResult)),
      1000
    );
  });

  it('should load create AssegnazioneTask page', async () => {
    await assegnazioneTaskComponentsPage.clickOnCreateButton();
    assegnazioneTaskUpdatePage = new AssegnazioneTaskUpdatePage();
    expect(await assegnazioneTaskUpdatePage.getPageTitle()).to.eq('eoloprjApp.assegnazioneTask.home.createOrEditLabel');
    await assegnazioneTaskUpdatePage.cancel();
  });

  it('should create and save AssegnazioneTasks', async () => {
    const nbButtonsBeforeCreate = await assegnazioneTaskComponentsPage.countDeleteButtons();

    await assegnazioneTaskComponentsPage.clickOnCreateButton();

    await promise.all([
      assegnazioneTaskUpdatePage.setIdAssegnazioneTaskInput('5'),
      assegnazioneTaskUpdatePage.setIdTaskRefInput('5'),
      assegnazioneTaskUpdatePage.setIdUserAmmessoInput('5'),
      assegnazioneTaskUpdatePage.setRuoloInput('5'),
      assegnazioneTaskUpdatePage.setIdUserConcedenteInput('5'),
      assegnazioneTaskUpdatePage.setStatoAssegnazioneInput('5'),
      assegnazioneTaskUpdatePage.ruoloSelectLastOption(),
      assegnazioneTaskUpdatePage.userPersonaSelectLastOption(),
    ]);

    expect(await assegnazioneTaskUpdatePage.getIdAssegnazioneTaskInput()).to.eq('5', 'Expected idAssegnazioneTask value to be equals to 5');
    expect(await assegnazioneTaskUpdatePage.getIdTaskRefInput()).to.eq('5', 'Expected idTaskRef value to be equals to 5');
    expect(await assegnazioneTaskUpdatePage.getIdUserAmmessoInput()).to.eq('5', 'Expected idUserAmmesso value to be equals to 5');
    expect(await assegnazioneTaskUpdatePage.getRuoloInput()).to.eq('5', 'Expected ruolo value to be equals to 5');
    expect(await assegnazioneTaskUpdatePage.getIdUserConcedenteInput()).to.eq('5', 'Expected idUserConcedente value to be equals to 5');
    expect(await assegnazioneTaskUpdatePage.getStatoAssegnazioneInput()).to.eq('5', 'Expected statoAssegnazione value to be equals to 5');

    await assegnazioneTaskUpdatePage.save();
    expect(await assegnazioneTaskUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await assegnazioneTaskComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last AssegnazioneTask', async () => {
    const nbButtonsBeforeDelete = await assegnazioneTaskComponentsPage.countDeleteButtons();
    await assegnazioneTaskComponentsPage.clickOnLastDeleteButton();

    assegnazioneTaskDeleteDialog = new AssegnazioneTaskDeleteDialog();
    expect(await assegnazioneTaskDeleteDialog.getDialogTitle()).to.eq('eoloprjApp.assegnazioneTask.delete.question');
    await assegnazioneTaskDeleteDialog.clickOnConfirmButton();

    expect(await assegnazioneTaskComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
