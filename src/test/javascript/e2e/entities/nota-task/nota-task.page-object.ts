import { element, by, ElementFinder } from 'protractor';

export class NotaTaskComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-nota-task div table .btn-danger'));
  title = element.all(by.css('jhi-nota-task div h2#page-heading span')).first();
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

export class NotaTaskUpdatePage {
  pageTitle = element(by.id('jhi-nota-task-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  idNotaTaskInput = element(by.id('field_idNotaTask'));
  idTaskRefInput = element(by.id('field_idTaskRef'));
  dataInput = element(by.id('field_data'));
  notaInput = element(by.id('field_nota'));
  versionInput = element(by.id('field_version'));

  idTaskSelect = element(by.id('field_idTask'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setIdNotaTaskInput(idNotaTask: string): Promise<void> {
    await this.idNotaTaskInput.sendKeys(idNotaTask);
  }

  async getIdNotaTaskInput(): Promise<string> {
    return await this.idNotaTaskInput.getAttribute('value');
  }

  async setIdTaskRefInput(idTaskRef: string): Promise<void> {
    await this.idTaskRefInput.sendKeys(idTaskRef);
  }

  async getIdTaskRefInput(): Promise<string> {
    return await this.idTaskRefInput.getAttribute('value');
  }

  async setDataInput(data: string): Promise<void> {
    await this.dataInput.sendKeys(data);
  }

  async getDataInput(): Promise<string> {
    return await this.dataInput.getAttribute('value');
  }

  async setNotaInput(nota: string): Promise<void> {
    await this.notaInput.sendKeys(nota);
  }

  async getNotaInput(): Promise<string> {
    return await this.notaInput.getAttribute('value');
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

export class NotaTaskDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-notaTask-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-notaTask'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
