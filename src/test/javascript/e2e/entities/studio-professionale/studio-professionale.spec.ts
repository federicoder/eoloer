import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  StudioProfessionaleComponentsPage,
  StudioProfessionaleDeleteDialog,
  StudioProfessionaleUpdatePage,
} from './studio-professionale.page-object';

const expect = chai.expect;

describe('StudioProfessionale e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let studioProfessionaleComponentsPage: StudioProfessionaleComponentsPage;
  let studioProfessionaleUpdatePage: StudioProfessionaleUpdatePage;
  let studioProfessionaleDeleteDialog: StudioProfessionaleDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load StudioProfessionales', async () => {
    await navBarPage.goToEntity('studio-professionale');
    studioProfessionaleComponentsPage = new StudioProfessionaleComponentsPage();
    await browser.wait(ec.visibilityOf(studioProfessionaleComponentsPage.title), 5000);
    expect(await studioProfessionaleComponentsPage.getTitle()).to.eq('eoloprjApp.studioProfessionale.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(studioProfessionaleComponentsPage.entities), ec.visibilityOf(studioProfessionaleComponentsPage.noResult)),
      1000
    );
  });

  it('should load create StudioProfessionale page', async () => {
    await studioProfessionaleComponentsPage.clickOnCreateButton();
    studioProfessionaleUpdatePage = new StudioProfessionaleUpdatePage();
    expect(await studioProfessionaleUpdatePage.getPageTitle()).to.eq('eoloprjApp.studioProfessionale.home.createOrEditLabel');
    await studioProfessionaleUpdatePage.cancel();
  });

  it('should create and save StudioProfessionales', async () => {
    const nbButtonsBeforeCreate = await studioProfessionaleComponentsPage.countDeleteButtons();

    await studioProfessionaleComponentsPage.clickOnCreateButton();

    await promise.all([
      studioProfessionaleUpdatePage.setIdUserAmministratoreInput('5'),
      studioProfessionaleUpdatePage.idUserAmministratoreSelectLastOption(),
    ]);

    expect(await studioProfessionaleUpdatePage.getIdUserAmministratoreInput()).to.eq(
      '5',
      'Expected idUserAmministratore value to be equals to 5'
    );

    await studioProfessionaleUpdatePage.save();
    expect(await studioProfessionaleUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await studioProfessionaleComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last StudioProfessionale', async () => {
    const nbButtonsBeforeDelete = await studioProfessionaleComponentsPage.countDeleteButtons();
    await studioProfessionaleComponentsPage.clickOnLastDeleteButton();

    studioProfessionaleDeleteDialog = new StudioProfessionaleDeleteDialog();
    expect(await studioProfessionaleDeleteDialog.getDialogTitle()).to.eq('eoloprjApp.studioProfessionale.delete.question');
    await studioProfessionaleDeleteDialog.clickOnConfirmButton();

    expect(await studioProfessionaleComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
