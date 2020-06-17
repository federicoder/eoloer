import { element, by, ElementFinder } from 'protractor';

export class AllegatoTemplateTaskComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-allegato-template-task div table .btn-danger'));
  title = element.all(by.css('jhi-allegato-template-task div h2#page-heading span')).first();
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

export class AllegatoTemplateTaskUpdatePage {
  pageTitle = element(by.id('jhi-allegato-template-task-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  idTemplateTaskRefInput = element(by.id('field_idTemplateTaskRef'));
  idTipoAllegatoRefInput = element(by.id('field_idTipoAllegatoRef'));
  formatoInput = element(by.id('field_formato'));
  idFileRefInput = element(by.id('field_idFileRef'));
  pubPrivInput = element(by.id('field_pubPriv'));

  idTemplateTaskRefSelect = element(by.id('field_idTemplateTaskRef'));
  tipoAllegatoSelect = element(by.id('field_tipoAllegato'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setIdTemplateTaskRefInput(idTemplateTaskRef: string): Promise<void> {
    await this.idTemplateTaskRefInput.sendKeys(idTemplateTaskRef);
  }

  async getIdTemplateTaskRefInput(): Promise<string> {
    return await this.idTemplateTaskRefInput.getAttribute('value');
  }

  async setIdTipoAllegatoRefInput(idTipoAllegatoRef: string): Promise<void> {
    await this.idTipoAllegatoRefInput.sendKeys(idTipoAllegatoRef);
  }

  async getIdTipoAllegatoRefInput(): Promise<string> {
    return await this.idTipoAllegatoRefInput.getAttribute('value');
  }

  async setFormatoInput(formato: string): Promise<void> {
    await this.formatoInput.sendKeys(formato);
  }

  async getFormatoInput(): Promise<string> {
    return await this.formatoInput.getAttribute('value');
  }

  async setIdFileRefInput(idFileRef: string): Promise<void> {
    await this.idFileRefInput.sendKeys(idFileRef);
  }

  async getIdFileRefInput(): Promise<string> {
    return await this.idFileRefInput.getAttribute('value');
  }

  async setPubPrivInput(pubPriv: string): Promise<void> {
    await this.pubPrivInput.sendKeys(pubPriv);
  }

  async getPubPrivInput(): Promise<string> {
    return await this.pubPrivInput.getAttribute('value');
  }

  async idTemplateTaskRefSelectLastOption(): Promise<void> {
    await this.idTemplateTaskRefSelect.all(by.tagName('option')).last().click();
  }

  async idTemplateTaskRefSelectOption(option: string): Promise<void> {
    await this.idTemplateTaskRefSelect.sendKeys(option);
  }

  getIdTemplateTaskRefSelect(): ElementFinder {
    return this.idTemplateTaskRefSelect;
  }

  async getIdTemplateTaskRefSelectedOption(): Promise<string> {
    return await this.idTemplateTaskRefSelect.element(by.css('option:checked')).getText();
  }

  async tipoAllegatoSelectLastOption(): Promise<void> {
    await this.tipoAllegatoSelect.all(by.tagName('option')).last().click();
  }

  async tipoAllegatoSelectOption(option: string): Promise<void> {
    await this.tipoAllegatoSelect.sendKeys(option);
  }

  getTipoAllegatoSelect(): ElementFinder {
    return this.tipoAllegatoSelect;
  }

  async getTipoAllegatoSelectedOption(): Promise<string> {
    return await this.tipoAllegatoSelect.element(by.css('option:checked')).getText();
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

export class AllegatoTemplateTaskDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-allegatoTemplateTask-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-allegatoTemplateTask'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
