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

  idTaskRefInput = element(by.id('field_idTaskRef'));
  dataPianificataInput = element(by.id('field_dataPianificata'));
  oraPianificataInput = element(by.id('field_oraPianificata'));
  dataScadenzaInput = element(by.id('field_dataScadenza'));
  versionInput = element(by.id('field_version'));

  idPrevisioneAttivitaSelect = element(by.id('field_idPrevisioneAttivita'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setIdTaskRefInput(idTaskRef: string): Promise<void> {
    await this.idTaskRefInput.sendKeys(idTaskRef);
  }

  async getIdTaskRefInput(): Promise<string> {
    return await this.idTaskRefInput.getAttribute('value');
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

  async idPrevisioneAttivitaSelectLastOption(): Promise<void> {
    await this.idPrevisioneAttivitaSelect.all(by.tagName('option')).last().click();
  }

  async idPrevisioneAttivitaSelectOption(option: string): Promise<void> {
    await this.idPrevisioneAttivitaSelect.sendKeys(option);
  }

  getIdPrevisioneAttivitaSelect(): ElementFinder {
    return this.idPrevisioneAttivitaSelect;
  }

  async getIdPrevisioneAttivitaSelectedOption(): Promise<string> {
    return await this.idPrevisioneAttivitaSelect.element(by.css('option:checked')).getText();
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
