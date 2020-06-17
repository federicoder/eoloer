import { element, by, ElementFinder } from 'protractor';

export class NotePersonaComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-note-persona div table .btn-danger'));
  title = element.all(by.css('jhi-note-persona div h2#page-heading span')).first();
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

export class NotePersonaUpdatePage {
  pageTitle = element(by.id('jhi-note-persona-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  idPersonaRefInput = element(by.id('field_idPersonaRef'));
  idNotePersonaInput = element(by.id('field_idNotePersona'));
  testoInput = element(by.id('field_testo'));

  personaSelect = element(by.id('field_persona'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setIdPersonaRefInput(idPersonaRef: string): Promise<void> {
    await this.idPersonaRefInput.sendKeys(idPersonaRef);
  }

  async getIdPersonaRefInput(): Promise<string> {
    return await this.idPersonaRefInput.getAttribute('value');
  }

  async setIdNotePersonaInput(idNotePersona: string): Promise<void> {
    await this.idNotePersonaInput.sendKeys(idNotePersona);
  }

  async getIdNotePersonaInput(): Promise<string> {
    return await this.idNotePersonaInput.getAttribute('value');
  }

  async setTestoInput(testo: string): Promise<void> {
    await this.testoInput.sendKeys(testo);
  }

  async getTestoInput(): Promise<string> {
    return await this.testoInput.getAttribute('value');
  }

  async personaSelectLastOption(): Promise<void> {
    await this.personaSelect.all(by.tagName('option')).last().click();
  }

  async personaSelectOption(option: string): Promise<void> {
    await this.personaSelect.sendKeys(option);
  }

  getPersonaSelect(): ElementFinder {
    return this.personaSelect;
  }

  async getPersonaSelectedOption(): Promise<string> {
    return await this.personaSelect.element(by.css('option:checked')).getText();
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

export class NotePersonaDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-notePersona-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-notePersona'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
