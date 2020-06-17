import { element, by, ElementFinder } from 'protractor';

export class EmailPersonaComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-email-persona div table .btn-danger'));
  title = element.all(by.css('jhi-email-persona div h2#page-heading span')).first();
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

export class EmailPersonaUpdatePage {
  pageTitle = element(by.id('jhi-email-persona-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  idPersonaRefInput = element(by.id('field_idPersonaRef'));
  etichettaInput = element(by.id('field_etichetta'));
  numeroInput = element(by.id('field_numero'));

  personaSelect = element(by.id('field_persona'));

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

  async setNumeroInput(numero: string): Promise<void> {
    await this.numeroInput.sendKeys(numero);
  }

  async getNumeroInput(): Promise<string> {
    return await this.numeroInput.getAttribute('value');
  }

  async personaSelectLastOption(): Promise<void> {
    await this.personaSelect.all(by.tagName('option')).last().click();
  }

  async personaSelectOption(option: string): Promise<void> {
    await this.personaSelect.sendKeys(option);
  }

  getPersonaSelect(): ElementFinder {
    return this.personaSelect;
  }

  async getPersonaSelectedOption(): Promise<string> {
    return await this.personaSelect.element(by.css('option:checked')).getText();
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

export class EmailPersonaDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-emailPersona-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-emailPersona'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
