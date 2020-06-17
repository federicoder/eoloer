import { element, by, ElementFinder } from 'protractor';

export class TemplatePraticaComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-template-pratica div table .btn-danger'));
  title = element.all(by.css('jhi-template-pratica div h2#page-heading span')).first();
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

export class TemplatePraticaUpdatePage {
  pageTitle = element(by.id('jhi-template-pratica-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  nomeTemplateInput = element(by.id('field_nomeTemplate'));
  elencoTagAmbitoInput = element(by.id('field_elencoTagAmbito'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setNomeTemplateInput(nomeTemplate: string): Promise<void> {
    await this.nomeTemplateInput.sendKeys(nomeTemplate);
  }

  async getNomeTemplateInput(): Promise<string> {
    return await this.nomeTemplateInput.getAttribute('value');
  }

  async setElencoTagAmbitoInput(elencoTagAmbito: string): Promise<void> {
    await this.elencoTagAmbitoInput.sendKeys(elencoTagAmbito);
  }

  async getElencoTagAmbitoInput(): Promise<string> {
    return await this.elencoTagAmbitoInput.getAttribute('value');
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

export class TemplatePraticaDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-templatePratica-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-templatePratica'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
