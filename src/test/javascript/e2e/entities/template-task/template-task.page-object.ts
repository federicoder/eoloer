import { element, by, ElementFinder } from 'protractor';

export class TemplateTaskComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-template-task div table .btn-danger'));
  title = element.all(by.css('jhi-template-task div h2#page-heading span')).first();
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

export class TemplateTaskUpdatePage {
  pageTitle = element(by.id('jhi-template-task-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  ordineEsecuzioneInput = element(by.id('field_ordineEsecuzione'));
  nomeInput = element(by.id('field_nome'));
  noteInput = element(by.id('field_note'));
  pubPrivInput = element(by.id('field_pubPriv'));
  idTemplatePraticaRefInput = element(by.id('field_idTemplatePraticaRef'));

  idTemplatePraticaRefSelect = element(by.id('field_idTemplatePraticaRef'));
  idSelect = element(by.id('field_id'));
  templateTaskSelect = element(by.id('field_templateTask'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setOrdineEsecuzioneInput(ordineEsecuzione: string): Promise<void> {
    await this.ordineEsecuzioneInput.sendKeys(ordineEsecuzione);
  }

  async getOrdineEsecuzioneInput(): Promise<string> {
    return await this.ordineEsecuzioneInput.getAttribute('value');
  }

  async setNomeInput(nome: string): Promise<void> {
    await this.nomeInput.sendKeys(nome);
  }

  async getNomeInput(): Promise<string> {
    return await this.nomeInput.getAttribute('value');
  }

  async setNoteInput(note: string): Promise<void> {
    await this.noteInput.sendKeys(note);
  }

  async getNoteInput(): Promise<string> {
    return await this.noteInput.getAttribute('value');
  }

  async setPubPrivInput(pubPriv: string): Promise<void> {
    await this.pubPrivInput.sendKeys(pubPriv);
  }

  async getPubPrivInput(): Promise<string> {
    return await this.pubPrivInput.getAttribute('value');
  }

  async setIdTemplatePraticaRefInput(idTemplatePraticaRef: string): Promise<void> {
    await this.idTemplatePraticaRefInput.sendKeys(idTemplatePraticaRef);
  }

  async getIdTemplatePraticaRefInput(): Promise<string> {
    return await this.idTemplatePraticaRefInput.getAttribute('value');
  }

  async idTemplatePraticaRefSelectLastOption(): Promise<void> {
    await this.idTemplatePraticaRefSelect.all(by.tagName('option')).last().click();
  }

  async idTemplatePraticaRefSelectOption(option: string): Promise<void> {
    await this.idTemplatePraticaRefSelect.sendKeys(option);
  }

  getIdTemplatePraticaRefSelect(): ElementFinder {
    return this.idTemplatePraticaRefSelect;
  }

  async getIdTemplatePraticaRefSelectedOption(): Promise<string> {
    return await this.idTemplatePraticaRefSelect.element(by.css('option:checked')).getText();
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

  async templateTaskSelectLastOption(): Promise<void> {
    await this.templateTaskSelect.all(by.tagName('option')).last().click();
  }

  async templateTaskSelectOption(option: string): Promise<void> {
    await this.templateTaskSelect.sendKeys(option);
  }

  getTemplateTaskSelect(): ElementFinder {
    return this.templateTaskSelect;
  }

  async getTemplateTaskSelectedOption(): Promise<string> {
    return await this.templateTaskSelect.element(by.css('option:checked')).getText();
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

export class TemplateTaskDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-templateTask-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-templateTask'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
