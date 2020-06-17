import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  RuoloOrganizzazioneComponentsPage,
  RuoloOrganizzazioneDeleteDialog,
  RuoloOrganizzazioneUpdatePage,
} from './ruolo-organizzazione.page-object';

const expect = chai.expect;

describe('RuoloOrganizzazione e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let ruoloOrganizzazioneComponentsPage: RuoloOrganizzazioneComponentsPage;
  let ruoloOrganizzazioneUpdatePage: RuoloOrganizzazioneUpdatePage;
  let ruoloOrganizzazioneDeleteDialog: RuoloOrganizzazioneDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load RuoloOrganizzaziones', async () => {
    await navBarPage.goToEntity('ruolo-organizzazione');
    ruoloOrganizzazioneComponentsPage = new RuoloOrganizzazioneComponentsPage();
    await browser.wait(ec.visibilityOf(ruoloOrganizzazioneComponentsPage.title), 5000);
    expect(await ruoloOrganizzazioneComponentsPage.getTitle()).to.eq('eoloprjApp.ruoloOrganizzazione.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(ruoloOrganizzazioneComponentsPage.entities), ec.visibilityOf(ruoloOrganizzazioneComponentsPage.noResult)),
      1000
    );
  });

  it('should load create RuoloOrganizzazione page', async () => {
    await ruoloOrganizzazioneComponentsPage.clickOnCreateButton();
    ruoloOrganizzazioneUpdatePage = new RuoloOrganizzazioneUpdatePage();
    expect(await ruoloOrganizzazioneUpdatePage.getPageTitle()).to.eq('eoloprjApp.ruoloOrganizzazione.home.createOrEditLabel');
    await ruoloOrganizzazioneUpdatePage.cancel();
  });

  it('should create and save RuoloOrganizzaziones', async () => {
    const nbButtonsBeforeCreate = await ruoloOrganizzazioneComponentsPage.countDeleteButtons();

    await ruoloOrganizzazioneComponentsPage.clickOnCreateButton();

    await promise.all([
      ruoloOrganizzazioneUpdatePage.setRuoloInOrgInput('5'),
      ruoloOrganizzazioneUpdatePage.idSelectLastOption(),
      ruoloOrganizzazioneUpdatePage.idSelectLastOption(),
    ]);

    expect(await ruoloOrganizzazioneUpdatePage.getRuoloInOrgInput()).to.eq('5', 'Expected ruoloInOrg value to be equals to 5');

    await ruoloOrganizzazioneUpdatePage.save();
    expect(await ruoloOrganizzazioneUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await ruoloOrganizzazioneComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last RuoloOrganizzazione', async () => {
    const nbButtonsBeforeDelete = await ruoloOrganizzazioneComponentsPage.countDeleteButtons();
    await ruoloOrganizzazioneComponentsPage.clickOnLastDeleteButton();

    ruoloOrganizzazioneDeleteDialog = new RuoloOrganizzazioneDeleteDialog();
    expect(await ruoloOrganizzazioneDeleteDialog.getDialogTitle()).to.eq('eoloprjApp.ruoloOrganizzazione.delete.question');
    await ruoloOrganizzazioneDeleteDialog.clickOnConfirmButton();

    expect(await ruoloOrganizzazioneComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
