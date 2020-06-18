import { element, by, ElementFinder } from 'protractor';

export class TelefonoPersonaComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-telefono-persona div table .btn-danger'));
  title = element.all(by.css('jhi-telefono-persona div h2#page-heading span')).first();
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

export class TelefonoPersonaUpdatePage {
  pageTitle = element(by.id('jhi-telefono-persona-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  idPersonaRefInput = element(by.id('field_idPersonaRef'));
  etichettaInput = element(by.id('field_etichetta'));
  valoreInput = element(by.id('field_valore'));

  idPersonaSelect = element(by.id('field_idPersona'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setIdPersonaRefInput(idPersonaRef: string): Promise<void> {
    await this.idPersonaRefInput.sendKeys(idPersonaRef);
  }

  async getIdPersonaRefInput(): Promise<string> {
    return await this.idPersonaRefInput.getAttribute('value');
  }

  async setEtichettaInput(etichetta: string): Promise<void> {
    await this.etichettaInput.sendKeys(etichetta);
  }

  async getEtichettaInput(): Promise<string> {
    return await this.etichettaInput.getAttribute('value');
  }

  async setValoreInput(valore: string): Promise<void> {
    await this.valoreInput.sendKeys(valore);
  }

  async getValoreInput(): Promise<string> {
    return await this.valoreInput.getAttribute('value');
  }

  async idPersonaSelectLastOption(): Promise<void> {
    await this.idPersonaSelect.all(by.tagName('option')).last().click();
  }

  async idPersonaSelectOption(option: string): Promise<void> {
    await this.idPersonaSelect.sendKeys(option);
  }

  getIdPersonaSelect(): ElementFinder {
    return this.idPersonaSelect;
  }

  async getIdPersonaSelectedOption(): Promise<string> {
    return await this.idPersonaSelect.element(by.css('option:checked')).getText();
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

export class TelefonoPersonaDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-telefonoPersona-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-telefonoPersona'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
