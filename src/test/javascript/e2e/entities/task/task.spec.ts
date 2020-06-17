import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { TaskComponentsPage, TaskDeleteDialog, TaskUpdatePage } from './task.page-object';

const expect = chai.expect;

describe('Task e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let taskComponentsPage: TaskComponentsPage;
  let taskUpdatePage: TaskUpdatePage;
  let taskDeleteDialog: TaskDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Tasks', async () => {
    await navBarPage.goToEntity('task');
    taskComponentsPage = new TaskComponentsPage();
    await browser.wait(ec.visibilityOf(taskComponentsPage.title), 5000);
    expect(await taskComponentsPage.getTitle()).to.eq('eoloprjApp.task.home.title');
    await browser.wait(ec.or(ec.visibilityOf(taskComponentsPage.entities), ec.visibilityOf(taskComponentsPage.noResult)), 1000);
  });

  it('should load create Task page', async () => {
    await taskComponentsPage.clickOnCreateButton();
    taskUpdatePage = new TaskUpdatePage();
    expect(await taskUpdatePage.getPageTitle()).to.eq('eoloprjApp.task.home.createOrEditLabel');
    await taskUpdatePage.cancel();
  });

  it('should create and save Tasks', async () => {
    const nbButtonsBeforeCreate = await taskComponentsPage.countDeleteButtons();

    await taskComponentsPage.clickOnCreateButton();

    await promise.all([
      taskUpdatePage.setIdPraticaInput('5'),
      taskUpdatePage.setNomeInput('nome'),
      taskUpdatePage.setStatoInput('5'),
      taskUpdatePage.setPrioritarioInput('5'),
      taskUpdatePage.setPubblicoInput('5'),
      taskUpdatePage.setVersionInput('version'),
      taskUpdatePage.setCondivisionePraticaIdInput('5'),
      taskUpdatePage.setAssegnazioneTaskIdInput('5'),
      taskUpdatePage.setInvitoIdInput('5'),
      taskUpdatePage.idSelectLastOption(),
      taskUpdatePage.idSelectLastOption(),
      taskUpdatePage.idSelectLastOption(),
      taskUpdatePage.idSelectLastOption(),
      taskUpdatePage.praticaSelectLastOption(),
    ]);

    expect(await taskUpdatePage.getIdPraticaInput()).to.eq('5', 'Expected idPratica value to be equals to 5');
    expect(await taskUpdatePage.getNomeInput()).to.eq('nome', 'Expected Nome value to be equals to nome');
    expect(await taskUpdatePage.getStatoInput()).to.eq('5', 'Expected stato value to be equals to 5');
    expect(await taskUpdatePage.getPrioritarioInput()).to.eq('5', 'Expected prioritario value to be equals to 5');
    expect(await taskUpdatePage.getPubblicoInput()).to.eq('5', 'Expected pubblico value to be equals to 5');
    expect(await taskUpdatePage.getVersionInput()).to.eq('version', 'Expected Version value to be equals to version');
    expect(await taskUpdatePage.getCondivisionePraticaIdInput()).to.eq('5', 'Expected condivisionePraticaId value to be equals to 5');
    expect(await taskUpdatePage.getAssegnazioneTaskIdInput()).to.eq('5', 'Expected assegnazioneTaskId value to be equals to 5');
    expect(await taskUpdatePage.getInvitoIdInput()).to.eq('5', 'Expected invitoId value to be equals to 5');

    await taskUpdatePage.save();
    expect(await taskUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await taskComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Task', async () => {
    const nbButtonsBeforeDelete = await taskComponentsPage.countDeleteButtons();
    await taskComponentsPage.clickOnLastDeleteButton();

    taskDeleteDialog = new TaskDeleteDialog();
    expect(await taskDeleteDialog.getDialogTitle()).to.eq('eoloprjApp.task.delete.question');
    await taskDeleteDialog.clickOnConfirmButton();

    expect(await taskComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
