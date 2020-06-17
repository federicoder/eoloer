import { element, by, ElementFinder } from 'protractor';

export class DatiContabiliComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-dati-contabili div table .btn-danger'));
  title = element.all(by.css('jhi-dati-contabili div h2#page-heading span')).first();
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

export class DatiContabiliUpdatePage {
  pageTitle = element(by.id('jhi-dati-contabili-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  idPersonaRefInput = element(by.id('field_idPersonaRef'));

  idPersonaRefSelect = element(by.id('field_idPersonaRef'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setIdPersonaRefInput(idPersonaRef: string): Promise<void> {
    await this.idPersonaRefInput.sendKeys(idPersonaRef);
  }

  async getIdPersonaRefInput(): Promise<string> {
    return await this.idPersonaRefInput.getAttribute('value');
  }

  async idPersonaRefSelectLastOption(): Promise<void> {
    await this.idPersonaRefSelect.all(by.tagName('option')).last().click();
  }

  async idPersonaRefSelectOption(option: string): Promise<void> {
    await this.idPersonaRefSelect.sendKeys(option);
  }

  getIdPersonaRefSelect(): ElementFinder {
    return this.idPersonaRefSelect;
  }

  async getIdPersonaRefSelectedOption(): Promise<string> {
    return await this.idPersonaRefSelect.element(by.css('option:checked')).getText();
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

export class DatiContabiliDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-datiContabili-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-datiContabili'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
