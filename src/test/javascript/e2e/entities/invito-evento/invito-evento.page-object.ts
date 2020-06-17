import { element, by, ElementFinder } from 'protractor';

export class InvitoEventoComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-invito-evento div table .btn-danger'));
  title = element.all(by.css('jhi-invito-evento div h2#page-heading span')).first();
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

export class InvitoEventoUpdatePage {
  pageTitle = element(by.id('jhi-invito-evento-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  idTaskRefInput = element(by.id('field_idTaskRef'));
  luogoFisicoInput = element(by.id('field_luogoFisico'));
  indicazioniLuogoInput = element(by.id('field_indicazioniLuogo'));
  dataInizioInput = element(by.id('field_dataInizio'));
  oraInizioInput = element(by.id('field_oraInizio'));
  dataFineInput = element(by.id('field_dataFine'));
  oraFineInput = element(by.id('field_oraFine'));
  urlStanzaVirtualeInput = element(by.id('field_urlStanzaVirtuale'));

  idTaskRefSelect = element(by.id('field_idTaskRef'));
  previsioneEventoSelect = element(by.id('field_previsioneEvento'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setIdTaskRefInput(idTaskRef: string): Promise<void> {
    await this.idTaskRefInput.sendKeys(idTaskRef);
  }

  async getIdTaskRefInput(): Promise<string> {
    return await this.idTaskRefInput.getAttribute('value');
  }

  async setLuogoFisicoInput(luogoFisico: string): Promise<void> {
    await this.luogoFisicoInput.sendKeys(luogoFisico);
  }

  async getLuogoFisicoInput(): Promise<string> {
    return await this.luogoFisicoInput.getAttribute('value');
  }

  async setIndicazioniLuogoInput(indicazioniLuogo: string): Promise<void> {
    await this.indicazioniLuogoInput.sendKeys(indicazioniLuogo);
  }

  async getIndicazioniLuogoInput(): Promise<string> {
    return await this.indicazioniLuogoInput.getAttribute('value');
  }

  async setDataInizioInput(dataInizio: string): Promise<void> {
    await this.dataInizioInput.sendKeys(dataInizio);
  }

  async getDataInizioInput(): Promise<string> {
    return await this.dataInizioInput.getAttribute('value');
  }

  async setOraInizioInput(oraInizio: string): Promise<void> {
    await this.oraInizioInput.sendKeys(oraInizio);
  }

  async getOraInizioInput(): Promise<string> {
    return await this.oraInizioInput.getAttribute('value');
  }

  async setDataFineInput(dataFine: string): Promise<void> {
    await this.dataFineInput.sendKeys(dataFine);
  }

  async getDataFineInput(): Promise<string> {
    return await this.dataFineInput.getAttribute('value');
  }

  async setOraFineInput(oraFine: string): Promise<void> {
    await this.oraFineInput.sendKeys(oraFine);
  }

  async getOraFineInput(): Promise<string> {
    return await this.oraFineInput.getAttribute('value');
  }

  async setUrlStanzaVirtualeInput(urlStanzaVirtuale: string): Promise<void> {
    await this.urlStanzaVirtualeInput.sendKeys(urlStanzaVirtuale);
  }

  async getUrlStanzaVirtualeInput(): Promise<string> {
    return await this.urlStanzaVirtualeInput.getAttribute('value');
  }

  async idTaskRefSelectLastOption(): Promise<void> {
    await this.idTaskRefSelect.all(by.tagName('option')).last().click();
  }

  async idTaskRefSelectOption(option: string): Promise<void> {
    await this.idTaskRefSelect.sendKeys(option);
  }

  getIdTaskRefSelect(): ElementFinder {
    return this.idTaskRefSelect;
  }

  async getIdTaskRefSelectedOption(): Promise<string> {
    return await this.idTaskRefSelect.element(by.css('option:checked')).getText();
  }

  async previsioneEventoSelectLastOption(): Promise<void> {
    await this.previsioneEventoSelect.all(by.tagName('option')).last().click();
  }

  async previsioneEventoSelectOption(option: string): Promise<void> {
    await this.previsioneEventoSelect.sendKeys(option);
  }

  getPrevisioneEventoSelect(): ElementFinder {
    return this.previsioneEventoSelect;
  }

  async getPrevisioneEventoSelectedOption(): Promise<string> {
    return await this.previsioneEventoSelect.element(by.css('option:checked')).getText();
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

export class InvitoEventoDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-invitoEvento-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-invitoEvento'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
