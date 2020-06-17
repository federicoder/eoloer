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
  idPersonaInput = element(by.id('field_idPersona'));
  ruoliSelect = element(by.id('field_ruoli'));

  personaSelect = element(by.id('field_persona'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setIdRuoloPersonaInput(idRuoloPersona: string): Promise<void> {
    await this.idRuoloPersonaInput.sendKeys(idRuoloPersona);
  }

  async getIdRuoloPersonaInput(): Promise<string> {
    return await this.idRuoloPersonaInput.getAttribute('value');
  }

  async setIdPersonaInput(idPersona: string): Promise<void> {
    await this.idPersonaInput.sendKeys(idPersona);
  }

  async getIdPersonaInput(): Promise<string> {
    return await this.idPersonaInput.getAttribute('value');
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
