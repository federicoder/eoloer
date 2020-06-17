import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { OrganizzazioneComponentsPage, OrganizzazioneDeleteDialog, OrganizzazioneUpdatePage } from './organizzazione.page-object';

const expect = chai.expect;

describe('Organizzazione e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let organizzazioneComponentsPage: OrganizzazioneComponentsPage;
  let organizzazioneUpdatePage: OrganizzazioneUpdatePage;
  let organizzazioneDeleteDialog: OrganizzazioneDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Organizzaziones', async () => {
    await navBarPage.goToEntity('organizzazione');
    organizzazioneComponentsPage = new OrganizzazioneComponentsPage();
    await browser.wait(ec.visibilityOf(organizzazioneComponentsPage.title), 5000);
    expect(await organizzazioneComponentsPage.getTitle()).to.eq('eoloprjApp.organizzazione.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(organizzazioneComponentsPage.entities), ec.visibilityOf(organizzazioneComponentsPage.noResult)),
      1000
    );
  });

  it('should load create Organizzazione page', async () => {
    await organizzazioneComponentsPage.clickOnCreateButton();
    organizzazioneUpdatePage = new OrganizzazioneUpdatePage();
    expect(await organizzazioneUpdatePage.getPageTitle()).to.eq('eoloprjApp.organizzazione.home.createOrEditLabel');
    await organizzazioneUpdatePage.cancel();
  });

  it('should create and save Organizzaziones', async () => {
    const nbButtonsBeforeCreate = await organizzazioneComponentsPage.countDeleteButtons();

    await organizzazioneComponentsPage.clickOnCreateButton();

    await promise.all([
      organizzazioneUpdatePage.setIdOrganizzazioneInput('5'),
      organizzazioneUpdatePage.setIdPersonaRefInput('5'),
      organizzazioneUpdatePage.setNomeInput('nome'),
      organizzazioneUpdatePage.setTipoInput('tipo'),
      organizzazioneUpdatePage.idPersonaRefSelectLastOption(),
    ]);

    expect(await organizzazioneUpdatePage.getIdOrganizzazioneInput()).to.eq('5', 'Expected idOrganizzazione value to be equals to 5');
    expect(await organizzazioneUpdatePage.getIdPersonaRefInput()).to.eq('5', 'Expected idPersonaRef value to be equals to 5');
    expect(await organizzazioneUpdatePage.getNomeInput()).to.eq('nome', 'Expected Nome value to be equals to nome');
    expect(await organizzazioneUpdatePage.getTipoInput()).to.eq('tipo', 'Expected Tipo value to be equals to tipo');

    await organizzazioneUpdatePage.save();
    expect(await organizzazioneUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await organizzazioneComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last Organizzazione', async () => {
    const nbButtonsBeforeDelete = await organizzazioneComponentsPage.countDeleteButtons();
    await organizzazioneComponentsPage.clickOnLastDeleteButton();

    organizzazioneDeleteDialog = new OrganizzazioneDeleteDialog();
    expect(await organizzazioneDeleteDialog.getDialogTitle()).to.eq('eoloprjApp.organizzazione.delete.question');
    await organizzazioneDeleteDialog.clickOnConfirmButton();

    expect(await organizzazioneComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
