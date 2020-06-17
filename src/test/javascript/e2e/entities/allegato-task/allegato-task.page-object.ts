import { element, by, ElementFinder } from 'protractor';

export class AllegatoTaskComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-allegato-task div table .btn-danger'));
  title = element.all(by.css('jhi-allegato-task div h2#page-heading span')).first();
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

export class AllegatoTaskUpdatePage {
  pageTitle = element(by.id('jhi-allegato-task-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  idAllegatoTaskInput = element(by.id('field_idAllegatoTask'));
  idTipoInput = element(by.id('field_idTipo'));
  idTaskInput = element(by.id('field_idTask'));
  formatoInput = element(by.id('field_formato'));
  noteInput = element(by.id('field_note'));
  statoInput = element(by.id('field_stato'));
  pubblicoInput = element(by.id('field_pubblico'));
  versionInput = element(by.id('field_version'));
  idAllegatoMasterInput = element(by.id('field_idAllegatoMaster'));

  tipoAllegatoSelect = element(by.id('field_tipoAllegato'));
  allegatoTaskSelect = element(by.id('field_allegatoTask'));
  taskSelect = element(by.id('field_task'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setIdAllegatoTaskInput(idAllegatoTask: string): Promise<void> {
    await this.idAllegatoTaskInput.sendKeys(idAllegatoTask);
  }

  async getIdAllegatoTaskInput(): Promise<string> {
    return await this.idAllegatoTaskInput.getAttribute('value');
  }

  async setIdTipoInput(idTipo: string): Promise<void> {
    await this.idTipoInput.sendKeys(idTipo);
  }

  async getIdTipoInput(): Promise<string> {
    return await this.idTipoInput.getAttribute('value');
  }

  async setIdTaskInput(idTask: string): Promise<void> {
    await this.idTaskInput.sendKeys(idTask);
  }

  async getIdTaskInput(): Promise<string> {
    return await this.idTaskInput.getAttribute('value');
  }

  async setFormatoInput(formato: string): Promise<void> {
    await this.formatoInput.sendKeys(formato);
  }

  async getFormatoInput(): Promise<string> {
    return await this.formatoInput.getAttribute('value');
  }

  async setNoteInput(note: string): Promise<void> {
    await this.noteInput.sendKeys(note);
  }

  async getNoteInput(): Promise<string> {
    return await this.noteInput.getAttribute('value');
  }

  async setStatoInput(stato: string): Promise<void> {
    await this.statoInput.sendKeys(stato);
  }

  async getStatoInput(): Promise<string> {
    return await this.statoInput.getAttribute('value');
  }

  async setPubblicoInput(pubblico: string): Promise<void> {
    await this.pubblicoInput.sendKeys(pubblico);
  }

  async getPubblicoInput(): Promise<string> {
    return await this.pubblicoInput.getAttribute('value');
  }

  async setVersionInput(version: string): Promise<void> {
    await this.versionInput.sendKeys(version);
  }

  async getVersionInput(): Promise<string> {
    return await this.versionInput.getAttribute('value');
  }

  async setIdAllegatoMasterInput(idAllegatoMaster: string): Promise<void> {
    await this.idAllegatoMasterInput.sendKeys(idAllegatoMaster);
  }

  async getIdAllegatoMasterInput(): Promise<string> {
    return await this.idAllegatoMasterInput.getAttribute('value');
  }

  async tipoAllegatoSelectLastOption(): Promise<void> {
    await this.tipoAllegatoSelect.all(by.tagName('option')).last().click();
  }

  async tipoAllegatoSelectOption(option: string): Promise<void> {
    await this.tipoAllegatoSelect.sendKeys(option);
  }

  getTipoAllegatoSelect(): ElementFinder {
    return this.tipoAllegatoSelect;
  }

  async getTipoAllegatoSelectedOption(): Promise<string> {
    return await this.tipoAllegatoSelect.element(by.css('option:checked')).getText();
  }

  async allegatoTaskSelectLastOption(): Promise<void> {
    await this.allegatoTaskSelect.all(by.tagName('option')).last().click();
  }

  async allegatoTaskSelectOption(option: string): Promise<void> {
    await this.allegatoTaskSelect.sendKeys(option);
  }

  getAllegatoTaskSelect(): ElementFinder {
    return this.allegatoTaskSelect;
  }

  async getAllegatoTaskSelectedOption(): Promise<string> {
    return await this.allegatoTaskSelect.element(by.css('option:checked')).getText();
  }

  async taskSelectLastOption(): Promise<void> {
    await this.taskSelect.all(by.tagName('option')).last().click();
  }

  async taskSelectOption(option: string): Promise<void> {
    await this.taskSelect.sendKeys(option);
  }

  getTaskSelect(): ElementFinder {
    return this.taskSelect;
  }

  async getTaskSelectedOption(): Promise<string> {
    return await this.taskSelect.element(by.css('option:checked')).getText();
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

export class AllegatoTaskDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-allegatoTask-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-allegatoTask'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
