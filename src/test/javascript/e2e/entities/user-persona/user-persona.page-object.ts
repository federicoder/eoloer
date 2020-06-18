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

  idPersonaRefInput = element(by.id('field_idPersonaRef'));
  nomeUserInput = element(by.id('field_nomeUser'));

  idPersonaFisicaSelect = element(by.id('field_idPersonaFisica'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setIdPersonaRefInput(idPersonaRef: string): Promise<void> {
    await this.idPersonaRefInput.sendKeys(idPersonaRef);
  }

  async getIdPersonaRefInput(): Promise<string> {
    return await this.idPersonaRefInput.getAttribute('value');
  }

  async setNomeUserInput(nomeUser: string): Promise<void> {
    await this.nomeUserInput.sendKeys(nomeUser);
  }

  async getNomeUserInput(): Promise<string> {
    return await this.nomeUserInput.getAttribute('value');
  }

  async idPersonaFisicaSelectLastOption(): Promise<void> {
    await this.idPersonaFisicaSelect.all(by.tagName('option')).last().click();
  }

  async idPersonaFisicaSelectOption(option: string): Promise<void> {
    await this.idPersonaFisicaSelect.sendKeys(option);
  }

  getIdPersonaFisicaSelect(): ElementFinder {
    return this.idPersonaFisicaSelect;
  }

  async getIdPersonaFisicaSelectedOption(): Promise<string> {
    return await this.idPersonaFisicaSelect.element(by.css('option:checked')).getText();
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
