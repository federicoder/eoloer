import { element, by, ElementFinder } from 'protractor';

export class InvitoAttivitaComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-invito-attivita div table .btn-danger'));
  title = element.all(by.css('jhi-invito-attivita div h2#page-heading span')).first();
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

export class InvitoAttivitaUpdatePage {
  pageTitle = element(by.id('jhi-invito-attivita-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  idAttivitaInput = element(by.id('field_idAttivita'));

  idAttivitaSelect = element(by.id('field_idAttivita'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setIdAttivitaInput(idAttivita: string): Promise<void> {
    await this.idAttivitaInput.sendKeys(idAttivita);
  }

  async getIdAttivitaInput(): Promise<string> {
    return await this.idAttivitaInput.getAttribute('value');
  }

  async idAttivitaSelectLastOption(): Promise<void> {
    await this.idAttivitaSelect.all(by.tagName('option')).last().click();
  }

  async idAttivitaSelectOption(option: string): Promise<void> {
    await this.idAttivitaSelect.sendKeys(option);
  }

  getIdAttivitaSelect(): ElementFinder {
    return this.idAttivitaSelect;
  }

  async getIdAttivitaSelectedOption(): Promise<string> {
    return await this.idAttivitaSelect.element(by.css('option:checked')).getText();
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

export class InvitoAttivitaDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-invitoAttivita-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-invitoAttivita'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
