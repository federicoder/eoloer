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

  idTipoAllegatoRefInput = element(by.id('field_idTipoAllegatoRef'));
  idTaskRefInput = element(by.id('field_idTaskRef'));
  formatoInput = element(by.id('field_formato'));
  noteInput = element(by.id('field_note'));
  statoInput = element(by.id('field_stato'));
  pubblicoInput = element(by.id('field_pubblico'));
  versionInput = element(by.id('field_version'));
  idAllegatoMasterInput = element(by.id('field_idAllegatoMaster'));

  idTipoAllegatoSelect = element(by.id('field_idTipoAllegato'));
  idTaskSelect = element(by.id('field_idTask'));
  allegatoTaskSelect = element(by.id('field_allegatoTask'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setIdTipoAllegatoRefInput(idTipoAllegatoRef: string): Promise<void> {
    await this.idTipoAllegatoRefInput.sendKeys(idTipoAllegatoRef);
  }

  async getIdTipoAllegatoRefInput(): Promise<string> {
    return await this.idTipoAllegatoRefInput.getAttribute('value');
  }

  async setIdTaskRefInput(idTaskRef: string): Promise<void> {
    await this.idTaskRefInput.sendKeys(idTaskRef);
  }

  async getIdTaskRefInput(): Promise<string> {
    return await this.idTaskRefInput.getAttribute('value');
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

  async idTipoAllegatoSelectLastOption(): Promise<void> {
    await this.idTipoAllegatoSelect.all(by.tagName('option')).last().click();
  }

  async idTipoAllegatoSelectOption(option: string): Promise<void> {
    await this.idTipoAllegatoSelect.sendKeys(option);
  }

  getIdTipoAllegatoSelect(): ElementFinder {
    return this.idTipoAllegatoSelect;
  }

  async getIdTipoAllegatoSelectedOption(): Promise<string> {
    return await this.idTipoAllegatoSelect.element(by.css('option:checked')).getText();
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
