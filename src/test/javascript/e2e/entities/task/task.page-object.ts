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

  idPraticaInput = element(by.id('field_idPratica'));
  nomeInput = element(by.id('field_nome'));
  statoInput = element(by.id('field_stato'));
  prioritarioInput = element(by.id('field_prioritario'));
  pubblicoInput = element(by.id('field_pubblico'));
  versionInput = element(by.id('field_version'));
  condivisionePraticaIdInput = element(by.id('field_condivisionePraticaId'));
  assegnazioneTaskIdInput = element(by.id('field_assegnazioneTaskId'));
  invitoIdInput = element(by.id('field_invitoId'));

  idSelect = element(by.id('field_id'));
  idSelect = element(by.id('field_id'));
  idSelect = element(by.id('field_id'));
  idSelect = element(by.id('field_id'));
  praticaSelect = element(by.id('field_pratica'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setIdPraticaInput(idPratica: string): Promise<void> {
    await this.idPraticaInput.sendKeys(idPratica);
  }

  async getIdPraticaInput(): Promise<string> {
    return await this.idPraticaInput.getAttribute('value');
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

  async setCondivisionePraticaIdInput(condivisionePraticaId: string): Promise<void> {
    await this.condivisionePraticaIdInput.sendKeys(condivisionePraticaId);
  }

  async getCondivisionePraticaIdInput(): Promise<string> {
    return await this.condivisionePraticaIdInput.getAttribute('value');
  }

  async setAssegnazioneTaskIdInput(assegnazioneTaskId: string): Promise<void> {
    await this.assegnazioneTaskIdInput.sendKeys(assegnazioneTaskId);
  }

  async getAssegnazioneTaskIdInput(): Promise<string> {
    return await this.assegnazioneTaskIdInput.getAttribute('value');
  }

  async setInvitoIdInput(invitoId: string): Promise<void> {
    await this.invitoIdInput.sendKeys(invitoId);
  }

  async getInvitoIdInput(): Promise<string> {
    return await this.invitoIdInput.getAttribute('value');
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

  async praticaSelectLastOption(): Promise<void> {
    await this.praticaSelect.all(by.tagName('option')).last().click();
  }

  async praticaSelectOption(option: string): Promise<void> {
    await this.praticaSelect.sendKeys(option);
  }

  getPraticaSelect(): ElementFinder {
    return this.praticaSelect;
  }

  async getPraticaSelectedOption(): Promise<string> {
    return await this.praticaSelect.element(by.css('option:checked')).getText();
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
