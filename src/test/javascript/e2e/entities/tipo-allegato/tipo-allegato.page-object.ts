import { element, by, ElementFinder } from 'protractor';

export class TipoAllegatoComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-tipo-allegato div table .btn-danger'));
  title = element.all(by.css('jhi-tipo-allegato div h2#page-heading span')).first();
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

export class TipoAllegatoUpdatePage {
  pageTitle = element(by.id('jhi-tipo-allegato-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  nomeInput = element(by.id('field_nome'));
  formatiAmmessiInput = element(by.id('field_formatiAmmessi'));
  maxDimensioneAmmessaInput = element(by.id('field_maxDimensioneAmmessa'));
  versionInput = element(by.id('field_version'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setNomeInput(nome: string): Promise<void> {
    await this.nomeInput.sendKeys(nome);
  }

  async getNomeInput(): Promise<string> {
    return await this.nomeInput.getAttribute('value');
  }

  async setFormatiAmmessiInput(formatiAmmessi: string): Promise<void> {
    await this.formatiAmmessiInput.sendKeys(formatiAmmessi);
  }

  async getFormatiAmmessiInput(): Promise<string> {
    return await this.formatiAmmessiInput.getAttribute('value');
  }

  async setMaxDimensioneAmmessaInput(maxDimensioneAmmessa: string): Promise<void> {
    await this.maxDimensioneAmmessaInput.sendKeys(maxDimensioneAmmessa);
  }

  async getMaxDimensioneAmmessaInput(): Promise<string> {
    return await this.maxDimensioneAmmessaInput.getAttribute('value');
  }

  async setVersionInput(version: string): Promise<void> {
    await this.versionInput.sendKeys(version);
  }

  async getVersionInput(): Promise<string> {
    return await this.versionInput.getAttribute('value');
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

export class TipoAllegatoDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-tipoAllegato-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-tipoAllegato'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
