import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { TipoAllegatoComponentsPage, TipoAllegatoDeleteDialog, TipoAllegatoUpdatePage } from './tipo-allegato.page-object';

const expect = chai.expect;

describe('TipoAllegato e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let tipoAllegatoComponentsPage: TipoAllegatoComponentsPage;
  let tipoAllegatoUpdatePage: TipoAllegatoUpdatePage;
  let tipoAllegatoDeleteDialog: TipoAllegatoDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load TipoAllegatoes', async () => {
    await navBarPage.goToEntity('tipo-allegato');
    tipoAllegatoComponentsPage = new TipoAllegatoComponentsPage();
    await browser.wait(ec.visibilityOf(tipoAllegatoComponentsPage.title), 5000);
    expect(await tipoAllegatoComponentsPage.getTitle()).to.eq('eoloprjApp.tipoAllegato.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(tipoAllegatoComponentsPage.entities), ec.visibilityOf(tipoAllegatoComponentsPage.noResult)),
      1000
    );
  });

  it('should load create TipoAllegato page', async () => {
    await tipoAllegatoComponentsPage.clickOnCreateButton();
    tipoAllegatoUpdatePage = new TipoAllegatoUpdatePage();
    expect(await tipoAllegatoUpdatePage.getPageTitle()).to.eq('eoloprjApp.tipoAllegato.home.createOrEditLabel');
    await tipoAllegatoUpdatePage.cancel();
  });

  it('should create and save TipoAllegatoes', async () => {
    const nbButtonsBeforeCreate = await tipoAllegatoComponentsPage.countDeleteButtons();

    await tipoAllegatoComponentsPage.clickOnCreateButton();

    await promise.all([
      tipoAllegatoUpdatePage.setIdTipoAllegatoInput('5'),
      tipoAllegatoUpdatePage.setNomeInput('nome'),
      tipoAllegatoUpdatePage.setFormatiAmmessiInput('formatiAmmessi'),
      tipoAllegatoUpdatePage.setMaxDimensioneAmmessaInput('maxDimensioneAmmessa'),
      tipoAllegatoUpdatePage.setVersionInput('version'),
    ]);

    expect(await tipoAllegatoUpdatePage.getIdTipoAllegatoInput()).to.eq('5', 'Expected idTipoAllegato value to be equals to 5');
    expect(await tipoAllegatoUpdatePage.getNomeInput()).to.eq('nome', 'Expected Nome value to be equals to nome');
    expect(await tipoAllegatoUpdatePage.getFormatiAmmessiInput()).to.eq(
      'formatiAmmessi',
      'Expected FormatiAmmessi value to be equals to formatiAmmessi'
    );
    expect(await tipoAllegatoUpdatePage.getMaxDimensioneAmmessaInput()).to.eq(
      'maxDimensioneAmmessa',
      'Expected MaxDimensioneAmmessa value to be equals to maxDimensioneAmmessa'
    );
    expect(await tipoAllegatoUpdatePage.getVersionInput()).to.eq('version', 'Expected Version value to be equals to version');

    await tipoAllegatoUpdatePage.save();
    expect(await tipoAllegatoUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await tipoAllegatoComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last TipoAllegato', async () => {
    const nbButtonsBeforeDelete = await tipoAllegatoComponentsPage.countDeleteButtons();
    await tipoAllegatoComponentsPage.clickOnLastDeleteButton();

    tipoAllegatoDeleteDialog = new TipoAllegatoDeleteDialog();
    expect(await tipoAllegatoDeleteDialog.getDialogTitle()).to.eq('eoloprjApp.tipoAllegato.delete.question');
    await tipoAllegatoDeleteDialog.clickOnConfirmButton();

    expect(await tipoAllegatoComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
