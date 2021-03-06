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

  idTemplatePraticaSelect = element(by.id('field_idTemplatePratica'));
  idTemplateTaskSelect = element(by.id('field_idTemplateTask'));
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

  async idTemplatePraticaSelectLastOption(): Promise<void> {
    await this.idTemplatePraticaSelect.all(by.tagName('option')).last().click();
  }

  async idTemplatePraticaSelectOption(option: string): Promise<void> {
    await this.idTemplatePraticaSelect.sendKeys(option);
  }

  getIdTemplatePraticaSelect(): ElementFinder {
    return this.idTemplatePraticaSelect;
  }

  async getIdTemplatePraticaSelectedOption(): Promise<string> {
    return await this.idTemplatePraticaSelect.element(by.css('option:checked')).getText();
  }

  async idTemplateTaskSelectLastOption(): Promise<void> {
    await this.idTemplateTaskSelect.all(by.tagName('option')).last().click();
  }

  async idTemplateTaskSelectOption(option: string): Promise<void> {
    await this.idTemplateTaskSelect.sendKeys(option);
  }

  getIdTemplateTaskSelect(): ElementFinder {
    return this.idTemplateTaskSelect;
  }

  async getIdTemplateTaskSelectedOption(): Promise<string> {
    return await this.idTemplateTaskSelect.element(by.css('option:checked')).getText();
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
