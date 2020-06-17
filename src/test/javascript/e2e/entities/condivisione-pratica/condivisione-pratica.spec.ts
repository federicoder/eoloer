import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  CondivisionePraticaComponentsPage,
  CondivisionePraticaDeleteDialog,
  CondivisionePraticaUpdatePage,
} from './condivisione-pratica.page-object';

const expect = chai.expect;

describe('CondivisionePratica e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let condivisionePraticaComponentsPage: CondivisionePraticaComponentsPage;
  let condivisionePraticaUpdatePage: CondivisionePraticaUpdatePage;
  let condivisionePraticaDeleteDialog: CondivisionePraticaDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load CondivisionePraticas', async () => {
    await navBarPage.goToEntity('condivisione-pratica');
    condivisionePraticaComponentsPage = new CondivisionePraticaComponentsPage();
    await browser.wait(ec.visibilityOf(condivisionePraticaComponentsPage.title), 5000);
    expect(await condivisionePraticaComponentsPage.getTitle()).to.eq('eoloprjApp.condivisionePratica.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(condivisionePraticaComponentsPage.entities), ec.visibilityOf(condivisionePraticaComponentsPage.noResult)),
      1000
    );
  });

  it('should load create CondivisionePratica page', async () => {
    await condivisionePraticaComponentsPage.clickOnCreateButton();
    condivisionePraticaUpdatePage = new CondivisionePraticaUpdatePage();
    expect(await condivisionePraticaUpdatePage.getPageTitle()).to.eq('eoloprjApp.condivisionePratica.home.createOrEditLabel');
    await condivisionePraticaUpdatePage.cancel();
  });

  it('should create and save CondivisionePraticas', async () => {
    const nbButtonsBeforeCreate = await condivisionePraticaComponentsPage.countDeleteButtons();

    await condivisionePraticaComponentsPage.clickOnCreateButton();

    await promise.all([
      condivisionePraticaUpdatePage.setIdUserAmmessoInput('5'),
      condivisionePraticaUpdatePage.setRuoloInput('5'),
      condivisionePraticaUpdatePage.setIdUserConcedenteInput('5'),
      condivisionePraticaUpdatePage.setStatoInvitoInput('5'),
      condivisionePraticaUpdatePage.setIdPraticaInput('5'),
      condivisionePraticaUpdatePage.ruoloSelectLastOption(),
      condivisionePraticaUpdatePage.idUserConcedenteSelectLastOption(),
      condivisionePraticaUpdatePage.praticaSelectLastOption(),
      condivisionePraticaUpdatePage.userPersonaSelectLastOption(),
    ]);

    expect(await condivisionePraticaUpdatePage.getIdUserAmmessoInput()).to.eq('5', 'Expected idUserAmmesso value to be equals to 5');
    expect(await condivisionePraticaUpdatePage.getRuoloInput()).to.eq('5', 'Expected ruolo value to be equals to 5');
    expect(await condivisionePraticaUpdatePage.getIdUserConcedenteInput()).to.eq('5', 'Expected idUserConcedente value to be equals to 5');
    expect(await condivisionePraticaUpdatePage.getStatoInvitoInput()).to.eq('5', 'Expected statoInvito value to be equals to 5');
    expect(await condivisionePraticaUpdatePage.getIdPraticaInput()).to.eq('5', 'Expected idPratica value to be equals to 5');

    await condivisionePraticaUpdatePage.save();
    expect(await condivisionePraticaUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await condivisionePraticaComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last CondivisionePratica', async () => {
    const nbButtonsBeforeDelete = await condivisionePraticaComponentsPage.countDeleteButtons();
    await condivisionePraticaComponentsPage.clickOnLastDeleteButton();

    condivisionePraticaDeleteDialog = new CondivisionePraticaDeleteDialog();
    expect(await condivisionePraticaDeleteDialog.getDialogTitle()).to.eq('eoloprjApp.condivisionePratica.delete.question');
    await condivisionePraticaDeleteDialog.clickOnConfirmButton();

    expect(await condivisionePraticaComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
