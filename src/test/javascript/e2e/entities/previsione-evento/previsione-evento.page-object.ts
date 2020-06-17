import { element, by, ElementFinder } from 'protractor';

export class PrevisioneEventoComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-previsione-evento div table .btn-danger'));
  title = element.all(by.css('jhi-previsione-evento div h2#page-heading span')).first();
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

export class PrevisioneEventoUpdatePage {
  pageTitle = element(by.id('jhi-previsione-evento-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  idTaskInput = element(by.id('field_idTask'));
  dataInizioInput = element(by.id('field_dataInizio'));
  dataFineInput = element(by.id('field_dataFine'));
  luogoInput = element(by.id('field_luogo'));
  indicazioniLuogoInput = element(by.id('field_indicazioniLuogo'));
  versionInput = element(by.id('field_version'));

  idTaskSelect = element(by.id('field_idTask'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setIdTaskInput(idTask: string): Promise<void> {
    await this.idTaskInput.sendKeys(idTask);
  }

  async getIdTaskInput(): Promise<string> {
    return await this.idTaskInput.getAttribute('value');
  }

  async setDataInizioInput(dataInizio: string): Promise<void> {
    await this.dataInizioInput.sendKeys(dataInizio);
  }

  async getDataInizioInput(): Promise<string> {
    return await this.dataInizioInput.getAttribute('value');
  }

  async setDataFineInput(dataFine: string): Promise<void> {
    await this.dataFineInput.sendKeys(dataFine);
  }

  async getDataFineInput(): Promise<string> {
    return await this.dataFineInput.getAttribute('value');
  }

  async setLuogoInput(luogo: string): Promise<void> {
    await this.luogoInput.sendKeys(luogo);
  }

  async getLuogoInput(): Promise<string> {
    return await this.luogoInput.getAttribute('value');
  }

  async setIndicazioniLuogoInput(indicazioniLuogo: string): Promise<void> {
    await this.indicazioniLuogoInput.sendKeys(indicazioniLuogo);
  }

  async getIndicazioniLuogoInput(): Promise<string> {
    return await this.indicazioniLuogoInput.getAttribute('value');
  }

  async setVersionInput(version: string): Promise<void> {
    await this.versionInput.sendKeys(version);
  }

  async getVersionInput(): Promise<string> {
    return await this.versionInput.getAttribute('value');
  }

  async idTaskSelectLastOption(): Promise<void> {
    await this.idTaskSelect.all(by.tagName('option')).last().click();
  }

  async idTaskSelectOption(option: string): Promise<void> {
    await this.idTaskSelect.sendKeys(option);
  }

  getIdTaskSelect(): ElementFinder {
    return this.idTaskSelect;
  }

  async getIdTaskSelectedOption(): Promise<string> {
    return await this.idTaskSelect.element(by.css('option:checked')).getText();
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

export class PrevisioneEventoDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-previsioneEvento-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-previsioneEvento'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}