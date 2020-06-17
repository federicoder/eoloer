import { element, by, ElementFinder } from 'protractor';

export class ProdottoComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-prodotto div table .btn-danger'));
  title = element.all(by.css('jhi-prodotto div h2#page-heading span')).first();
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

export class ProdottoUpdatePage {
  pageTitle = element(by.id('jhi-prodotto-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  nuovaLicenzaInput = element(by.id('field_nuovaLicenza'));
  rinnovoLicenzaInput = element(by.id('field_rinnovoLicenza'));
  storageInput = element(by.id('field_storage'));

  idSelect = element(by.id('field_id'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setNuovaLicenzaInput(nuovaLicenza: string): Promise<void> {
    await this.nuovaLicenzaInput.sendKeys(nuovaLicenza);
  }

  async getNuovaLicenzaInput(): Promise<string> {
    return await this.nuovaLicenzaInput.getAttribute('value');
  }

  async setRinnovoLicenzaInput(rinnovoLicenza: string): Promise<void> {
    await this.rinnovoLicenzaInput.sendKeys(rinnovoLicenza);
  }

  async getRinnovoLicenzaInput(): Promise<string> {
    return await this.rinnovoLicenzaInput.getAttribute('value');
  }

  async setStorageInput(storage: string): Promise<void> {
    await this.storageInput.sendKeys(storage);
  }

  async getStorageInput(): Promise<string> {
    return await this.storageInput.getAttribute('value');
  }

  async idSelectLastOption(): Promise<void> {
    await this.idSelect.all(by.tagName('option')).last().click();
  }

  async idSelectOption(option: string): Promise<void> {
    await this.idSelect.sendKeys(option);
  }

  getIdSelect(): ElementFinder {
    return this.idSelect;
  }

  async getIdSelectedOption(): Promise<string> {
    return await this.idSelect.element(by.css('option:checked')).getText();
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

export class ProdottoDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-prodotto-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-prodotto'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
