import { element, by, ElementFinder } from 'protractor';

export class TaskComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-task div table .btn-danger'));
  title = element.all(by.css('jhi-task div h2#page-heading span')).first();
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

export class TaskUpdatePage {
  pageTitle = element(by.id('jhi-task-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  idPraticaRefInput = element(by.id('field_idPraticaRef'));
  nomeInput = element(by.id('field_nome'));
  statoInput = element(by.id('field_stato'));
  prioritarioInput = element(by.id('field_prioritario'));
  pubblicoInput = element(by.id('field_pubblico'));
  versionInput = element(by.id('field_version'));
  idCondivisionePraticaRefInput = element(by.id('field_idCondivisionePraticaRef'));
  idAssegnazioneTaskRefInput = element(by.id('field_idAssegnazioneTaskRef'));
  idInvitoRefInput = element(by.id('field_idInvitoRef'));

  idSelect = element(by.id('field_id'));
  idSelect = element(by.id('field_id'));
  idSelect = element(by.id('field_id'));
  idSelect = element(by.id('field_id'));
  idPraticaRefSelect = element(by.id('field_idPraticaRef'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setIdPraticaRefInput(idPraticaRef: string): Promise<void> {
    await this.idPraticaRefInput.sendKeys(idPraticaRef);
  }

  async getIdPraticaRefInput(): Promise<string> {
    return await this.idPraticaRefInput.getAttribute('value');
  }

  async setNomeInput(nome: string): Promise<void> {
    await this.nomeInput.sendKeys(nome);
  }

  async getNomeInput(): Promise<string> {
    return await this.nomeInput.getAttribute('value');
  }

  async setStatoInput(stato: string): Promise<void> {
    await this.statoInput.sendKeys(stato);
  }

  async getStatoInput(): Promise<string> {
    return await this.statoInput.getAttribute('value');
  }

  async setPrioritarioInput(prioritario: string): Promise<void> {
    await this.prioritarioInput.sendKeys(prioritario);
  }

  async getPrioritarioInput(): Promise<string> {
    return await this.prioritarioInput.getAttribute('value');
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

  async setIdCondivisionePraticaRefInput(idCondivisionePraticaRef: string): Promise<void> {
    await this.idCondivisionePraticaRefInput.sendKeys(idCondivisionePraticaRef);
  }

  async getIdCondivisionePraticaRefInput(): Promise<string> {
    return await this.idCondivisionePraticaRefInput.getAttribute('value');
  }

  async setIdAssegnazioneTaskRefInput(idAssegnazioneTaskRef: string): Promise<void> {
    await this.idAssegnazioneTaskRefInput.sendKeys(idAssegnazioneTaskRef);
  }

  async getIdAssegnazioneTaskRefInput(): Promise<string> {
    return await this.idAssegnazioneTaskRefInput.getAttribute('value');
  }

  async setIdInvitoRefInput(idInvitoRef: string): Promise<void> {
    await this.idInvitoRefInput.sendKeys(idInvitoRef);
  }

  async getIdInvitoRefInput(): Promise<string> {
    return await this.idInvitoRefInput.getAttribute('value');
  }

  async idSelectLastOption(): Promise<void> {
    await this.idSelect.all(by.tagName('option')).last().click();
  }

  async idSelectOption(option: string): Promise<void> {
    await this.idSelect.sendKeys(option);
  }

  getIdSelect(): ElementFinder {
    return this.idSelect;
  }

  async getIdSelectedOption(): Promise<string> {
    return await this.idSelect.element(by.css('option:checked')).getText();
  }

  async idSelectLastOption(): Promise<void> {
    await this.idSelect.all(by.tagName('option')).last().click();
  }

  async idSelectOption(option: string): Promise<void> {
    await this.idSelect.sendKeys(option);
  }

  getIdSelect(): ElementFinder {
    return this.idSelect;
  }

  async getIdSelectedOption(): Promise<string> {
    return await this.idSelect.element(by.css('option:checked')).getText();
  }

  async idSelectLastOption(): Promise<void> {
    await this.idSelect.all(by.tagName('option')).last().click();
  }

  async idSelectOption(option: string): Promise<void> {
    await this.idSelect.sendKeys(option);
  }

  getIdSelect(): ElementFinder {
    return this.idSelect;
  }

  async getIdSelectedOption(): Promise<string> {
    return await this.idSelect.element(by.css('option:checked')).getText();
  }

  async idSelectLastOption(): Promise<void> {
    await this.idSelect.all(by.tagName('option')).last().click();
  }

  async idSelectOption(option: string): Promise<void> {
    await this.idSelect.sendKeys(option);
  }

  getIdSelect(): ElementFinder {
    return this.idSelect;
  }

  async getIdSelectedOption(): Promise<string> {
    return await this.idSelect.element(by.css('option:checked')).getText();
  }

  async idPraticaRefSelectLastOption(): Promise<void> {
    await this.idPraticaRefSelect.all(by.tagName('option')).last().click();
  }

  async idPraticaRefSelectOption(option: string): Promise<void> {
    await this.idPraticaRefSelect.sendKeys(option);
  }

  getIdPraticaRefSelect(): ElementFinder {
    return this.idPraticaRefSelect;
  }

  async getIdPraticaRefSelectedOption(): Promise<string> {
    return await this.idPraticaRefSelect.element(by.css('option:checked')).getText();
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

export class TaskDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-task-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-task'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
