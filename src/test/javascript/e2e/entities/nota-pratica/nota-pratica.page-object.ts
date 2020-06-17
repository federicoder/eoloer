import { element, by, ElementFinder } from 'protractor';

export class NotaPraticaComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-nota-pratica div table .btn-danger'));
  title = element.all(by.css('jhi-nota-pratica div h2#page-heading span')).first();
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

export class NotaPraticaUpdatePage {
  pageTitle = element(by.id('jhi-nota-pratica-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  idNotaPraticaInput = element(by.id('field_idNotaPratica'));
  idPraticaRefInput = element(by.id('field_idPraticaRef'));
  dataInput = element(by.id('field_data'));
  notaInput = element(by.id('field_nota'));
  versionInput = element(by.id('field_version'));

  praticaSelect = element(by.id('field_pratica'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setIdNotaPraticaInput(idNotaPratica: string): Promise<void> {
    await this.idNotaPraticaInput.sendKeys(idNotaPratica);
  }

  async getIdNotaPraticaInput(): Promise<string> {
    return await this.idNotaPraticaInput.getAttribute('value');
  }

  async setIdPraticaRefInput(idPraticaRef: string): Promise<void> {
    await this.idPraticaRefInput.sendKeys(idPraticaRef);
  }

  async getIdPraticaRefInput(): Promise<string> {
    return await this.idPraticaRefInput.getAttribute('value');
  }

  async setDataInput(data: string): Promise<void> {
    await this.dataInput.sendKeys(data);
  }

  async getDataInput(): Promise<string> {
    return await this.dataInput.getAttribute('value');
  }

  async setNotaInput(nota: string): Promise<void> {
    await this.notaInput.sendKeys(nota);
  }

  async getNotaInput(): Promise<string> {
    return await this.notaInput.getAttribute('value');
  }

  async setVersionInput(version: string): Promise<void> {
    await this.versionInput.sendKeys(version);
  }

  async getVersionInput(): Promise<string> {
    return await this.versionInput.getAttribute('value');
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

export class NotaPraticaDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-notaPratica-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-notaPratica'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
