import { element, by, ElementFinder } from 'protractor';

export class ConsuntivoTaskComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-consuntivo-task div table .btn-danger'));
  title = element.all(by.css('jhi-consuntivo-task div h2#page-heading span')).first();
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

export class ConsuntivoTaskUpdatePage {
  pageTitle = element(by.id('jhi-consuntivo-task-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  idTaskInput = element(by.id('field_idTask'));
  dataInizioInput = element(by.id('field_dataInizio'));
  dataFineInput = element(by.id('field_dataFine'));
  timeLineInput = element(by.id('field_timeLine'));
  versionInput = element(by.id('field_version'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setIdTaskInput(idTask: string): Promise<void> {
    await this.idTaskInput.sendKeys(idTask);
  }

  async getIdTaskInput(): Promise<string> {
    return await this.idTaskInput.getAttribute('value');
  }

  async setDataInizioInput(dataInizio: string): Promise<void> {
    await this.dataInizioInput.sendKeys(dataInizio);
  }

  async getDataInizioInput(): Promise<string> {
    return await this.dataInizioInput.getAttribute('value');
  }

  async setDataFineInput(dataFine: string): Promise<void> {
    await this.dataFineInput.sendKeys(dataFine);
  }

  async getDataFineInput(): Promise<string> {
    return await this.dataFineInput.getAttribute('value');
  }

  async setTimeLineInput(timeLine: string): Promise<void> {
    await this.timeLineInput.sendKeys(timeLine);
  }

  async getTimeLineInput(): Promise<string> {
    return await this.timeLineInput.getAttribute('value');
  }

  async setVersionInput(version: string): Promise<void> {
    await this.versionInput.sendKeys(version);
  }

  async getVersionInput(): Promise<string> {
    return await this.versionInput.getAttribute('value');
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

export class ConsuntivoTaskDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-consuntivoTask-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-consuntivoTask'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
