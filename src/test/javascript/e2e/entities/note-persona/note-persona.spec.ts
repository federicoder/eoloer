import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { NotePersonaComponentsPage, NotePersonaDeleteDialog, NotePersonaUpdatePage } from './note-persona.page-object';

const expect = chai.expect;

describe('NotePersona e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let notePersonaComponentsPage: NotePersonaComponentsPage;
  let notePersonaUpdatePage: NotePersonaUpdatePage;
  let notePersonaDeleteDialog: NotePersonaDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load NotePersonas', async () => {
    await navBarPage.goToEntity('note-persona');
    notePersonaComponentsPage = new NotePersonaComponentsPage();
    await browser.wait(ec.visibilityOf(notePersonaComponentsPage.title), 5000);
    expect(await notePersonaComponentsPage.getTitle()).to.eq('eoloprjApp.notePersona.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(notePersonaComponentsPage.entities), ec.visibilityOf(notePersonaComponentsPage.noResult)),
      1000
    );
  });

  it('should load create NotePersona page', async () => {
    await notePersonaComponentsPage.clickOnCreateButton();
    notePersonaUpdatePage = new NotePersonaUpdatePage();
    expect(await notePersonaUpdatePage.getPageTitle()).to.eq('eoloprjApp.notePersona.home.createOrEditLabel');
    await notePersonaUpdatePage.cancel();
  });

  it('should create and save NotePersonas', async () => {
    const nbButtonsBeforeCreate = await notePersonaComponentsPage.countDeleteButtons();

    await notePersonaComponentsPage.clickOnCreateButton();

    await promise.all([
      notePersonaUpdatePage.setIdPersonaRefInput('5'),
      notePersonaUpdatePage.setIdNotePersonaInput('5'),
      notePersonaUpdatePage.setTestoInput('testo'),
      notePersonaUpdatePage.personaSelectLastOption(),
    ]);

    expect(await notePersonaUpdatePage.getIdPersonaRefInput()).to.eq('5', 'Expected idPersonaRef value to be equals to 5');
    expect(await notePersonaUpdatePage.getIdNotePersonaInput()).to.eq('5', 'Expected idNotePersona value to be equals to 5');
    expect(await notePersonaUpdatePage.getTestoInput()).to.eq('testo', 'Expected Testo value to be equals to testo');

    await notePersonaUpdatePage.save();
    expect(await notePersonaUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await notePersonaComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last NotePersona', async () => {
    const nbButtonsBeforeDelete = await notePersonaComponentsPage.countDeleteButtons();
    await notePersonaComponentsPage.clickOnLastDeleteButton();

    notePersonaDeleteDialog = new NotePersonaDeleteDialog();
    expect(await notePersonaDeleteDialog.getDialogTitle()).to.eq('eoloprjApp.notePersona.delete.question');
    await notePersonaDeleteDialog.clickOnConfirmButton();

    expect(await notePersonaComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
