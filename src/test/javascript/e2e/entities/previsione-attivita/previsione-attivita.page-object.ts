import { element, by, ElementFinder } from 'protractor';

export class PrevisioneAttivitaComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-previsione-attivita div table .btn-danger'));
  title = element.all(by.css('jhi-previsione-attivita div h2#page-heading span')).first();
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

export class PrevisioneAttivitaUpdatePage {
  pageTitle = element(by.id('jhi-previsione-attivita-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  idTaskInput = element(by.id('field_idTask'));
  dataPianificataInput = element(by.id('field_dataPianificata'));
  oraPianificataInput = element(by.id('field_oraPianificata'));
  dataScadenzaInput = element(by.id('field_dataScadenza'));
  versionInput = element(by.id('field_version'));

  idTaskSelect = element(by.id('field_idTask'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setIdTaskInput(idTask: string): Promise<void> {
    await this.idTaskInput.sendKeys(idTask);
  }

  async getIdTaskInput(): Promise<string> {
    return await this.idTaskInput.getAttribute('value');
  }

  async setDataPianificataInput(dataPianificata: string): Promise<void> {
    await this.dataPianificataInput.sendKeys(dataPianificata);
  }

  async getDataPianificataInput(): Promise<string> {
    return await this.dataPianificataInput.getAttribute('value');
  }

  async setOraPianificataInput(oraPianificata: string): Promise<void> {
    await this.oraPianificataInput.sendKeys(oraPianificata);
  }

  async getOraPianificataInput(): Promise<string> {
    return await this.oraPianificataInput.getAttribute('value');
  }

  async setDataScadenzaInput(dataScadenza: string): Promise<void> {
    await this.dataScadenzaInput.sendKeys(dataScadenza);
  }

  async getDataScadenzaInput(): Promise<string> {
    return await this.dataScadenzaInput.getAttribute('value');
  }

  async setVersionInput(version: string): Promise<void> {
    await this.versionInput.sendKeys(version);
  }

  async getVersionInput(): Promise<string> {
    return await this.versionInput.getAttribute('value');
  }

  async idTaskSelectLastOption(): Promise<void> {
    await this.idTaskSelect.all(by.tagName('option')).last().click();
  }

  async idTaskSelectOption(option: string): Promise<void> {
    await this.idTaskSelect.sendKeys(option);
  }

  getIdTaskSelect(): ElementFinder {
    return this.idTaskSelect;
  }

  async getIdTaskSelectedOption(): Promise<string> {
    return await this.idTaskSelect.element(by.css('option:checked')).getText();
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

export class PrevisioneAttivitaDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-previsioneAttivita-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-previsioneAttivita'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
