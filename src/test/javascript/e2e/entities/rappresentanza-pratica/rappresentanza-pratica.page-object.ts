import { element, by, ElementFinder } from 'protractor';

export class RappresentanzaPraticaComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-rappresentanza-pratica div table .btn-danger'));
  title = element.all(by.css('jhi-rappresentanza-pratica div h2#page-heading span')).first();
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

export class RappresentanzaPraticaUpdatePage {
  pageTitle = element(by.id('jhi-rappresentanza-pratica-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  idRuoloPersonaInput = element(by.id('field_idRuoloPersona'));
  idPersonaRefInput = element(by.id('field_idPersonaRef'));
  ruoliSelect = element(by.id('field_ruoli'));

  idPersonaRefSelect = element(by.id('field_idPersonaRef'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setIdRuoloPersonaInput(idRuoloPersona: string): Promise<void> {
    await this.idRuoloPersonaInput.sendKeys(idRuoloPersona);
  }

  async getIdRuoloPersonaInput(): Promise<string> {
    return await this.idRuoloPersonaInput.getAttribute('value');
  }

  async setIdPersonaRefInput(idPersonaRef: string): Promise<void> {
    await this.idPersonaRefInput.sendKeys(idPersonaRef);
  }

  async getIdPersonaRefInput(): Promise<string> {
    return await this.idPersonaRefInput.getAttribute('value');
  }

  async setRuoliSelect(ruoli: string): Promise<void> {
    await this.ruoliSelect.sendKeys(ruoli);
  }

  async getRuoliSelect(): Promise<string> {
    return await this.ruoliSelect.element(by.css('option:checked')).getText();
  }

  async ruoliSelectLastOption(): Promise<void> {
    await this.ruoliSelect.all(by.tagName('option')).last().click();
  }

  async idPersonaRefSelectLastOption(): Promise<void> {
    await this.idPersonaRefSelect.all(by.tagName('option')).last().click();
  }

  async idPersonaRefSelectOption(option: string): Promise<void> {
    await this.idPersonaRefSelect.sendKeys(option);
  }

  getIdPersonaRefSelect(): ElementFinder {
    return this.idPersonaRefSelect;
  }

  async getIdPersonaRefSelectedOption(): Promise<string> {
    return await this.idPersonaRefSelect.element(by.css('option:checked')).getText();
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

export class RappresentanzaPraticaDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-rappresentanzaPratica-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-rappresentanzaPratica'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
