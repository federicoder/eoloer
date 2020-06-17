import { element, by, ElementFinder } from 'protractor';

export class StudioProfessionaleComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-studio-professionale div table .btn-danger'));
  title = element.all(by.css('jhi-studio-professionale div h2#page-heading span')).first();
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

export class StudioProfessionaleUpdatePage {
  pageTitle = element(by.id('jhi-studio-professionale-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  idUserAmministratoreInput = element(by.id('field_idUserAmministratore'));

  idUserAmministratoreSelect = element(by.id('field_idUserAmministratore'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setIdUserAmministratoreInput(idUserAmministratore: string): Promise<void> {
    await this.idUserAmministratoreInput.sendKeys(idUserAmministratore);
  }

  async getIdUserAmministratoreInput(): Promise<string> {
    return await this.idUserAmministratoreInput.getAttribute('value');
  }

  async idUserAmministratoreSelectLastOption(): Promise<void> {
    await this.idUserAmministratoreSelect.all(by.tagName('option')).last().click();
  }

  async idUserAmministratoreSelectOption(option: string): Promise<void> {
    await this.idUserAmministratoreSelect.sendKeys(option);
  }

  getIdUserAmministratoreSelect(): ElementFinder {
    return this.idUserAmministratoreSelect;
  }

  async getIdUserAmministratoreSelectedOption(): Promise<string> {
    return await this.idUserAmministratoreSelect.element(by.css('option:checked')).getText();
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

export class StudioProfessionaleDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-studioProfessionale-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-studioProfessionale'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
