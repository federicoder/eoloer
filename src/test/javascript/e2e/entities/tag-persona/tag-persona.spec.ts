import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { TagPersonaComponentsPage, TagPersonaDeleteDialog, TagPersonaUpdatePage } from './tag-persona.page-object';

const expect = chai.expect;

describe('TagPersona e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let tagPersonaComponentsPage: TagPersonaComponentsPage;
  let tagPersonaUpdatePage: TagPersonaUpdatePage;
  let tagPersonaDeleteDialog: TagPersonaDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load TagPersonas', async () => {
    await navBarPage.goToEntity('tag-persona');
    tagPersonaComponentsPage = new TagPersonaComponentsPage();
    await browser.wait(ec.visibilityOf(tagPersonaComponentsPage.title), 5000);
    expect(await tagPersonaComponentsPage.getTitle()).to.eq('eoloprjApp.tagPersona.home.title');
    await browser.wait(ec.or(ec.visibilityOf(tagPersonaComponentsPage.entities), ec.visibilityOf(tagPersonaComponentsPage.noResult)), 1000);
  });

  it('should load create TagPersona page', async () => {
    await tagPersonaComponentsPage.clickOnCreateButton();
    tagPersonaUpdatePage = new TagPersonaUpdatePage();
    expect(await tagPersonaUpdatePage.getPageTitle()).to.eq('eoloprjApp.tagPersona.home.createOrEditLabel');
    await tagPersonaUpdatePage.cancel();
  });

  it('should create and save TagPersonas', async () => {
    const nbButtonsBeforeCreate = await tagPersonaComponentsPage.countDeleteButtons();

    await tagPersonaComponentsPage.clickOnCreateButton();

    await promise.all([
      tagPersonaUpdatePage.setIdPersonaRefInput('5'),
      tagPersonaUpdatePage.setTagInput('5'),
      tagPersonaUpdatePage.idPersonaSelectLastOption(),
    ]);

    expect(await tagPersonaUpdatePage.getIdPersonaRefInput()).to.eq('5', 'Expected idPersonaRef value to be equals to 5');
    expect(await tagPersonaUpdatePage.getTagInput()).to.eq('5', 'Expected tag value to be equals to 5');

    await tagPersonaUpdatePage.save();
    expect(await tagPersonaUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await tagPersonaComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last TagPersona', async () => {
    const nbButtonsBeforeDelete = await tagPersonaComponentsPage.countDeleteButtons();
    await tagPersonaComponentsPage.clickOnLastDeleteButton();

    tagPersonaDeleteDialog = new TagPersonaDeleteDialog();
    expect(await tagPersonaDeleteDialog.getDialogTitle()).to.eq('eoloprjApp.tagPersona.delete.question');
    await tagPersonaDeleteDialog.clickOnConfirmButton();

    expect(await tagPersonaComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
