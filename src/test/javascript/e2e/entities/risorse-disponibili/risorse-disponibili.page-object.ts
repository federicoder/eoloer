import { element, by, ElementFinder } from 'protractor';

export class RisorseDisponibiliComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-risorse-disponibili div table .btn-danger'));
  title = element.all(by.css('jhi-risorse-disponibili div h2#page-heading span')).first();
  noResult = element(by.id('no-result'));
  entities = element(by.id('entities'));

  async clickOnCreateButton(): Promise<void> {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton(): Promise<void> {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons(): Promise<number> {
    return this.deleteButtons.count();
  }

  async getTitle(): Promise<string> {
    return this.title.getAttribute('jhiTranslate');
  }
}

export class RisorseDisponibiliUpdatePage {
  pageTitle = element(by.id('jhi-risorse-disponibili-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  idStudioProfessionaleInput = element(by.id('field_idStudioProfessionale'));
  dataAttivazioneLicenzaInput = element(by.id('field_dataAttivazioneLicenza'));
  nrLicenzaInput = element(by.id('field_nrLicenza'));
  storageTotaleInput = element(by.id('field_storageTotale'));

  studioProfessionaleSelect = element(by.id('field_studioProfessionale'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setIdStudioProfessionaleInput(idStudioProfessionale: string): Promise<void> {
    await this.idStudioProfessionaleInput.sendKeys(idStudioProfessionale);
  }

  async getIdStudioProfessionaleInput(): Promise<string> {
    return await this.idStudioProfessionaleInput.getAttribute('value');
  }

  async setDataAttivazioneLicenzaInput(dataAttivazioneLicenza: string): Promise<void> {
    await this.dataAttivazioneLicenzaInput.sendKeys(dataAttivazioneLicenza);
  }

  async getDataAttivazioneLicenzaInput(): Promise<string> {
    return await this.dataAttivazioneLicenzaInput.getAttribute('value');
  }

  async setNrLicenzaInput(nrLicenza: string): Promise<void> {
    await this.nrLicenzaInput.sendKeys(nrLicenza);
  }

  async getNrLicenzaInput(): Promise<string> {
    return await this.nrLicenzaInput.getAttribute('value');
  }

  async setStorageTotaleInput(storageTotale: string): Promise<void> {
    await this.storageTotaleInput.sendKeys(storageTotale);
  }

  async getStorageTotaleInput(): Promise<string> {
    return await this.storageTotaleInput.getAttribute('value');
  }

  async studioProfessionaleSelectLastOption(): Promise<void> {
    await this.studioProfessionaleSelect.all(by.tagName('option')).last().click();
  }

  async studioProfessionaleSelectOption(option: string): Promise<void> {
    await this.studioProfessionaleSelect.sendKeys(option);
  }

  getStudioProfessionaleSelect(): ElementFinder {
    return this.studioProfessionaleSelect;
  }

  async getStudioProfessionaleSelectedOption(): Promise<string> {
    return await this.studioProfessionaleSelect.element(by.css('option:checked')).getText();
  }

  async save(): Promise<void> {
    await this.saveButton.click();
  }

  async cancel(): Promise<void> {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class RisorseDisponibiliDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-risorseDisponibili-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-risorseDisponibili'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
