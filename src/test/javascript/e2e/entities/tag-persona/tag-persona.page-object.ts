import { element, by, ElementFinder } from 'protractor';

export class TagPersonaComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-tag-persona div table .btn-danger'));
  title = element.all(by.css('jhi-tag-persona div h2#page-heading span')).first();
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

export class TagPersonaUpdatePage {
  pageTitle = element(by.id('jhi-tag-persona-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  idPersonaInput = element(by.id('field_idPersona'));
  tagInput = element(by.id('field_tag'));

  personaSelect = element(by.id('field_persona'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setIdPersonaInput(idPersona: string): Promise<void> {
    await this.idPersonaInput.sendKeys(idPersona);
  }

  async getIdPersonaInput(): Promise<string> {
    return await this.idPersonaInput.getAttribute('value');
  }

  async setTagInput(tag: string): Promise<void> {
    await this.tagInput.sendKeys(tag);
  }

  async getTagInput(): Promise<string> {
    return await this.tagInput.getAttribute('value');
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

export class TagPersonaDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-tagPersona-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-tagPersona'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}