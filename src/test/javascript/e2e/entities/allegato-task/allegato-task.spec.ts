import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { AllegatoTaskComponentsPage, AllegatoTaskDeleteDialog, AllegatoTaskUpdatePage } from './allegato-task.page-object';

const expect = chai.expect;

describe('AllegatoTask e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let allegatoTaskComponentsPage: AllegatoTaskComponentsPage;
  let allegatoTaskUpdatePage: AllegatoTaskUpdatePage;
  let allegatoTaskDeleteDialog: AllegatoTaskDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load AllegatoTasks', async () => {
    await navBarPage.goToEntity('allegato-task');
    allegatoTaskComponentsPage = new AllegatoTaskComponentsPage();
    await browser.wait(ec.visibilityOf(allegatoTaskComponentsPage.title), 5000);
    expect(await allegatoTaskComponentsPage.getTitle()).to.eq('eoloprjApp.allegatoTask.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(allegatoTaskComponentsPage.entities), ec.visibilityOf(allegatoTaskComponentsPage.noResult)),
      1000
    );
  });

  it('should load create AllegatoTask page', async () => {
    await allegatoTaskComponentsPage.clickOnCreateButton();
    allegatoTaskUpdatePage = new AllegatoTaskUpdatePage();
    expect(await allegatoTaskUpdatePage.getPageTitle()).to.eq('eoloprjApp.allegatoTask.home.createOrEditLabel');
    await allegatoTaskUpdatePage.cancel();
  });

  it('should create and save AllegatoTasks', async () => {
    const nbButtonsBeforeCreate = await allegatoTaskComponentsPage.countDeleteButtons();

    await allegatoTaskComponentsPage.clickOnCreateButton();

    await promise.all([
      allegatoTaskUpdatePage.setIdAllegatoTaskInput('5'),
      allegatoTaskUpdatePage.setIdTipoAllegatoRefInput('5'),
      allegatoTaskUpdatePage.setIdTaskRefInput('5'),
      allegatoTaskUpdatePage.setFormatoInput('5'),
      allegatoTaskUpdatePage.setNoteInput('note'),
      allegatoTaskUpdatePage.setStatoInput('5'),
      allegatoTaskUpdatePage.setPubblicoInput('5'),
      allegatoTaskUpdatePage.setVersionInput('version'),
      allegatoTaskUpdatePage.setIdAllegatoMasterInput('5'),
      allegatoTaskUpdatePage.tipoAllegatoSelectLastOption(),
      allegatoTaskUpdatePage.allegatoTaskSelectLastOption(),
      allegatoTaskUpdatePage.taskSelectLastOption(),
    ]);

    expect(await allegatoTaskUpdatePage.getIdAllegatoTaskInput()).to.eq('5', 'Expected idAllegatoTask value to be equals to 5');
    expect(await allegatoTaskUpdatePage.getIdTipoAllegatoRefInput()).to.eq('5', 'Expected idTipoAllegatoRef value to be equals to 5');
    expect(await allegatoTaskUpdatePage.getIdTaskRefInput()).to.eq('5', 'Expected idTaskRef value to be equals to 5');
    expect(await allegatoTaskUpdatePage.getFormatoInput()).to.eq('5', 'Expected formato value to be equals to 5');
    expect(await allegatoTaskUpdatePage.getNoteInput()).to.eq('note', 'Expected Note value to be equals to note');
    expect(await allegatoTaskUpdatePage.getStatoInput()).to.eq('5', 'Expected stato value to be equals to 5');
    expect(await allegatoTaskUpdatePage.getPubblicoInput()).to.eq('5', 'Expected pubblico value to be equals to 5');
    expect(await allegatoTaskUpdatePage.getVersionInput()).to.eq('version', 'Expected Version value to be equals to version');
    expect(await allegatoTaskUpdatePage.getIdAllegatoMasterInput()).to.eq('5', 'Expected idAllegatoMaster value to be equals to 5');

    await allegatoTaskUpdatePage.save();
    expect(await allegatoTaskUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await allegatoTaskComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last AllegatoTask', async () => {
    const nbButtonsBeforeDelete = await allegatoTaskComponentsPage.countDeleteButtons();
    await allegatoTaskComponentsPage.clickOnLastDeleteButton();

    allegatoTaskDeleteDialog = new AllegatoTaskDeleteDialog();
    expect(await allegatoTaskDeleteDialog.getDialogTitle()).to.eq('eoloprjApp.allegatoTask.delete.question');
    await allegatoTaskDeleteDialog.clickOnConfirmButton();

    expect(await allegatoTaskComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
