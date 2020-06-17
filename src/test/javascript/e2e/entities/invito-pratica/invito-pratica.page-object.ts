import { element, by, ElementFinder } from 'protractor';

export class InvitoPraticaComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-invito-pratica div table .btn-danger'));
  title = element.all(by.css('jhi-invito-pratica div h2#page-heading span')).first();
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

export class InvitoPraticaUpdatePage {
  pageTitle = element(by.id('jhi-invito-pratica-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  idPraticaInput = element(by.id('field_idPratica'));

  idPraticaSelect = element(by.id('field_idPratica'));
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

  async idPraticaSelectLastOption(): Promise<void> {
    await this.idPraticaSelect.all(by.tagName('option')).last().click();
  }

  async idPraticaSelectOption(option: string): Promise<void> {
    await this.idPraticaSelect.sendKeys(option);
  }

  getIdPraticaSelect(): ElementFinder {
    return this.idPraticaSelect;
  }

  async getIdPraticaSelectedOption(): Promise<string> {
    return await this.idPraticaSelect.element(by.css('option:checked')).getText();
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

export class InvitoPraticaDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-invitoPratica-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-invitoPratica'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
