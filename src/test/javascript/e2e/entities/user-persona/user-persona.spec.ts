import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { UserPersonaComponentsPage, UserPersonaDeleteDialog, UserPersonaUpdatePage } from './user-persona.page-object';

const expect = chai.expect;

describe('UserPersona e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let userPersonaComponentsPage: UserPersonaComponentsPage;
  let userPersonaUpdatePage: UserPersonaUpdatePage;
  let userPersonaDeleteDialog: UserPersonaDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load UserPersonas', async () => {
    await navBarPage.goToEntity('user-persona');
    userPersonaComponentsPage = new UserPersonaComponentsPage();
    await browser.wait(ec.visibilityOf(userPersonaComponentsPage.title), 5000);
    expect(await userPersonaComponentsPage.getTitle()).to.eq('eoloprjApp.userPersona.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(userPersonaComponentsPage.entities), ec.visibilityOf(userPersonaComponentsPage.noResult)),
      1000
    );
  });

  it('should load create UserPersona page', async () => {
    await userPersonaComponentsPage.clickOnCreateButton();
    userPersonaUpdatePage = new UserPersonaUpdatePage();
    expect(await userPersonaUpdatePage.getPageTitle()).to.eq('eoloprjApp.userPersona.home.createOrEditLabel');
    await userPersonaUpdatePage.cancel();
  });

  it('should create and save UserPersonas', async () => {
    const nbButtonsBeforeCreate = await userPersonaComponentsPage.countDeleteButtons();

    await userPersonaComponentsPage.clickOnCreateButton();

    await promise.all([
      userPersonaUpdatePage.setIdPersonaRefInput('5'),
      userPersonaUpdatePage.setNomeUserInput('5'),
      userPersonaUpdatePage.personaFisicaSelectLastOption(),
    ]);

    expect(await userPersonaUpdatePage.getIdPersonaRefInput()).to.eq('5', 'Expected idPersonaRef value to be equals to 5');
    expect(await userPersonaUpdatePage.getNomeUserInput()).to.eq('5', 'Expected nomeUser value to be equals to 5');

    await userPersonaUpdatePage.save();
    expect(await userPersonaUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await userPersonaComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last UserPersona', async () => {
    const nbButtonsBeforeDelete = await userPersonaComponentsPage.countDeleteButtons();
    await userPersonaComponentsPage.clickOnLastDeleteButton();

    userPersonaDeleteDialog = new UserPersonaDeleteDialog();
    expect(await userPersonaDeleteDialog.getDialogTitle()).to.eq('eoloprjApp.userPersona.delete.question');
    await userPersonaDeleteDialog.clickOnConfirmButton();

    expect(await userPersonaComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
