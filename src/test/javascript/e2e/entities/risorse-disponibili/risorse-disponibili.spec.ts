import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  RisorseDisponibiliComponentsPage,
  RisorseDisponibiliDeleteDialog,
  RisorseDisponibiliUpdatePage,
} from './risorse-disponibili.page-object';

const expect = chai.expect;

describe('RisorseDisponibili e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let risorseDisponibiliComponentsPage: RisorseDisponibiliComponentsPage;
  let risorseDisponibiliUpdatePage: RisorseDisponibiliUpdatePage;
  let risorseDisponibiliDeleteDialog: RisorseDisponibiliDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load RisorseDisponibilis', async () => {
    await navBarPage.goToEntity('risorse-disponibili');
    risorseDisponibiliComponentsPage = new RisorseDisponibiliComponentsPage();
    await browser.wait(ec.visibilityOf(risorseDisponibiliComponentsPage.title), 5000);
    expect(await risorseDisponibiliComponentsPage.getTitle()).to.eq('eoloprjApp.risorseDisponibili.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(risorseDisponibiliComponentsPage.entities), ec.visibilityOf(risorseDisponibiliComponentsPage.noResult)),
      1000
    );
  });

  it('should load create RisorseDisponibili page', async () => {
    await risorseDisponibiliComponentsPage.clickOnCreateButton();
    risorseDisponibiliUpdatePage = new RisorseDisponibiliUpdatePage();
    expect(await risorseDisponibiliUpdatePage.getPageTitle()).to.eq('eoloprjApp.risorseDisponibili.home.createOrEditLabel');
    await risorseDisponibiliUpdatePage.cancel();
  });

  it('should create and save RisorseDisponibilis', async () => {
    const nbButtonsBeforeCreate = await risorseDisponibiliComponentsPage.countDeleteButtons();

    await risorseDisponibiliComponentsPage.clickOnCreateButton();

    await promise.all([
      risorseDisponibiliUpdatePage.setIdStudioProfessionaleRefInput('5'),
      risorseDisponibiliUpdatePage.setDataAttivazioneLicenzaInput('dataAttivazioneLicenza'),
      risorseDisponibiliUpdatePage.setNrLicenzaInput('5'),
      risorseDisponibiliUpdatePage.setStorageTotaleInput('5'),
      risorseDisponibiliUpdatePage.idStudioProfessionaleSelectLastOption(),
    ]);

    expect(await risorseDisponibiliUpdatePage.getIdStudioProfessionaleRefInput()).to.eq(
      '5',
      'Expected idStudioProfessionaleRef value to be equals to 5'
    );
    expect(await risorseDisponibiliUpdatePage.getDataAttivazioneLicenzaInput()).to.eq(
      'dataAttivazioneLicenza',
      'Expected DataAttivazioneLicenza value to be equals to dataAttivazioneLicenza'
    );
    expect(await risorseDisponibiliUpdatePage.getNrLicenzaInput()).to.eq('5', 'Expected nrLicenza value to be equals to 5');
    expect(await risorseDisponibiliUpdatePage.getStorageTotaleInput()).to.eq('5', 'Expected storageTotale value to be equals to 5');

    await risorseDisponibiliUpdatePage.save();
    expect(await risorseDisponibiliUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await risorseDisponibiliComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last RisorseDisponibili', async () => {
    const nbButtonsBeforeDelete = await risorseDisponibiliComponentsPage.countDeleteButtons();
    await risorseDisponibiliComponentsPage.clickOnLastDeleteButton();

    risorseDisponibiliDeleteDialog = new RisorseDisponibiliDeleteDialog();
    expect(await risorseDisponibiliDeleteDialog.getDialogTitle()).to.eq('eoloprjApp.risorseDisponibili.delete.question');
    await risorseDisponibiliDeleteDialog.clickOnConfirmButton();

    expect(await risorseDisponibiliComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
