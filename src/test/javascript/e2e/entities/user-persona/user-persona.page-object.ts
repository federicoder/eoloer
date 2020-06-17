import { element, by, ElementFinder } from 'protractor';

export class UserPersonaComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-user-persona div table .btn-danger'));
  title = element.all(by.css('jhi-user-persona div h2#page-heading span')).first();
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

export class UserPersonaUpdatePage {
  pageTitle = element(by.id('jhi-user-persona-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  idPersonaInput = element(by.id('field_idPersona'));
  nomeUserInput = element(by.id('field_nomeUser'));

  personaFisicaSelect = element(by.id('field_personaFisica'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setIdPersonaInput(idPersona: string): Promise<void> {
    await this.idPersonaInput.sendKeys(idPersona);
  }

  async getIdPersonaInput(): Promise<string> {
    return await this.idPersonaInput.getAttribute('value');
  }

  async setNomeUserInput(nomeUser: string): Promise<void> {
    await this.nomeUserInput.sendKeys(nomeUser);
  }

  async getNomeUserInput(): Promise<string> {
    return await this.nomeUserInput.getAttribute('value');
  }

  async personaFisicaSelectLastOption(): Promise<void> {
    await this.personaFisicaSelect.all(by.tagName('option')).last().click();
  }

  async personaFisicaSelectOption(option: string): Promise<void> {
    await this.personaFisicaSelect.sendKeys(option);
  }

  getPersonaFisicaSelect(): ElementFinder {
    return this.personaFisicaSelect;
  }

  async getPersonaFisicaSelectedOption(): Promise<string> {
    return await this.personaFisicaSelect.element(by.css('option:checked')).getText();
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

export class UserPersonaDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-userPersona-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-userPersona'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}