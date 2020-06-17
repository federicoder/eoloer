import { element, by, ElementFinder } from 'protractor';

export class PraticaComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-pratica div table .btn-danger'));
  title = element.all(by.css('jhi-pratica div h2#page-heading span')).first();
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

export class PraticaUpdatePage {
  pageTitle = element(by.id('jhi-pratica-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  idStudioProfessionaleRefInput = element(by.id('field_idStudioProfessionaleRef'));
  numeroInput = element(by.id('field_numero'));
  nomeInput = element(by.id('field_nome'));
  dataAperturaInput = element(by.id('field_dataApertura'));
  dataChiusuraInput = element(by.id('field_dataChiusura'));
  dataScadenzaInput = element(by.id('field_dataScadenza'));
  statoInput = element(by.id('field_stato'));
  motivoChiusuraInput = element(by.id('field_motivoChiusura'));
  idTitolareInput = element(by.id('field_idTitolare'));
  prcAvanzatoInput = element(by.id('field_prcAvanzato'));
  versionInput = element(by.id('field_version'));
  valutaInput = element(by.id('field_valuta'));
  idTemplatePraticaRefInput = element(by.id('field_idTemplatePraticaRef'));

  idTemplatePraticaRefSelect = element(by.id('field_idTemplatePraticaRef'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setIdStudioProfessionaleRefInput(idStudioProfessionaleRef: string): Promise<void> {
    await this.idStudioProfessionaleRefInput.sendKeys(idStudioProfessionaleRef);
  }

  async getIdStudioProfessionaleRefInput(): Promise<string> {
    return await this.idStudioProfessionaleRefInput.getAttribute('value');
  }

  async setNumeroInput(numero: string): Promise<void> {
    await this.numeroInput.sendKeys(numero);
  }

  async getNumeroInput(): Promise<string> {
    return await this.numeroInput.getAttribute('value');
  }

  async setNomeInput(nome: string): Promise<void> {
    await this.nomeInput.sendKeys(nome);
  }

  async getNomeInput(): Promise<string> {
    return await this.nomeInput.getAttribute('value');
  }

  async setDataAperturaInput(dataApertura: string): Promise<void> {
    await this.dataAperturaInput.sendKeys(dataApertura);
  }

  async getDataAperturaInput(): Promise<string> {
    return await this.dataAperturaInput.getAttribute('value');
  }

  async setDataChiusuraInput(dataChiusura: string): Promise<void> {
    await this.dataChiusuraInput.sendKeys(dataChiusura);
  }

  async getDataChiusuraInput(): Promise<string> {
    return await this.dataChiusuraInput.getAttribute('value');
  }

  async setDataScadenzaInput(dataScadenza: string): Promise<void> {
    await this.dataScadenzaInput.sendKeys(dataScadenza);
  }

  async getDataScadenzaInput(): Promise<string> {
    return await this.dataScadenzaInput.getAttribute('value');
  }

  async setStatoInput(stato: string): Promise<void> {
    await this.statoInput.sendKeys(stato);
  }

  async getStatoInput(): Promise<string> {
    return await this.statoInput.getAttribute('value');
  }

  async setMotivoChiusuraInput(motivoChiusura: string): Promise<void> {
    await this.motivoChiusuraInput.sendKeys(motivoChiusura);
  }

  async getMotivoChiusuraInput(): Promise<string> {
    return await this.motivoChiusuraInput.getAttribute('value');
  }

  async setIdTitolareInput(idTitolare: string): Promise<void> {
    await this.idTitolareInput.sendKeys(idTitolare);
  }

  async getIdTitolareInput(): Promise<string> {
    return await this.idTitolareInput.getAttribute('value');
  }

  async setPrcAvanzatoInput(prcAvanzato: string): Promise<void> {
    await this.prcAvanzatoInput.sendKeys(prcAvanzato);
  }

  async getPrcAvanzatoInput(): Promise<string> {
    return await this.prcAvanzatoInput.getAttribute('value');
  }

  async setVersionInput(version: string): Promise<void> {
    await this.versionInput.sendKeys(version);
  }

  async getVersionInput(): Promise<string> {
    return await this.versionInput.getAttribute('value');
  }

  async setValutaInput(valuta: string): Promise<void> {
    await this.valutaInput.sendKeys(valuta);
  }

  async getValutaInput(): Promise<string> {
    return await this.valutaInput.getAttribute('value');
  }

  async setIdTemplatePraticaRefInput(idTemplatePraticaRef: string): Promise<void> {
    await this.idTemplatePraticaRefInput.sendKeys(idTemplatePraticaRef);
  }

  async getIdTemplatePraticaRefInput(): Promise<string> {
    return await this.idTemplatePraticaRefInput.getAttribute('value');
  }

  async idTemplatePraticaRefSelectLastOption(): Promise<void> {
    await this.idTemplatePraticaRefSelect.all(by.tagName('option')).last().click();
  }

  async idTemplatePraticaRefSelectOption(option: string): Promise<void> {
    await this.idTemplatePraticaRefSelect.sendKeys(option);
  }

  getIdTemplatePraticaRefSelect(): ElementFinder {
    return this.idTemplatePraticaRefSelect;
  }

  async getIdTemplatePraticaRefSelectedOption(): Promise<string> {
    return await this.idTemplatePraticaRefSelect.element(by.css('option:checked')).getText();
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

export class PraticaDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-pratica-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-pratica'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
