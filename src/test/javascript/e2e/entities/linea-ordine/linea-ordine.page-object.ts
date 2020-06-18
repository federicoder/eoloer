import { element, by, ElementFinder } from 'protractor';

export class LineaOrdineComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-linea-ordine div table .btn-danger'));
  title = element.all(by.css('jhi-linea-ordine div h2#page-heading span')).first();
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

export class LineaOrdineUpdatePage {
  pageTitle = element(by.id('jhi-linea-ordine-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  idOrdineRefInput = element(by.id('field_idOrdineRef'));
  idProdottoRefInput = element(by.id('field_idProdottoRef'));
  quantitaInput = element(by.id('field_quantita'));
  importoInput = element(by.id('field_importo'));
  codIvaInput = element(by.id('field_codIva'));

  idProdottoSelect = element(by.id('field_idProdotto'));
  idOrdineSelect = element(by.id('field_idOrdine'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setIdOrdineRefInput(idOrdineRef: string): Promise<void> {
    await this.idOrdineRefInput.sendKeys(idOrdineRef);
  }

  async getIdOrdineRefInput(): Promise<string> {
    return await this.idOrdineRefInput.getAttribute('value');
  }

  async setIdProdottoRefInput(idProdottoRef: string): Promise<void> {
    await this.idProdottoRefInput.sendKeys(idProdottoRef);
  }

  async getIdProdottoRefInput(): Promise<string> {
    return await this.idProdottoRefInput.getAttribute('value');
  }

  async setQuantitaInput(quantita: string): Promise<void> {
    await this.quantitaInput.sendKeys(quantita);
  }

  async getQuantitaInput(): Promise<string> {
    return await this.quantitaInput.getAttribute('value');
  }

  async setImportoInput(importo: string): Promise<void> {
    await this.importoInput.sendKeys(importo);
  }

  async getImportoInput(): Promise<string> {
    return await this.importoInput.getAttribute('value');
  }

  async setCodIvaInput(codIva: string): Promise<void> {
    await this.codIvaInput.sendKeys(codIva);
  }

  async getCodIvaInput(): Promise<string> {
    return await this.codIvaInput.getAttribute('value');
  }

  async idProdottoSelectLastOption(): Promise<void> {
    await this.idProdottoSelect.all(by.tagName('option')).last().click();
  }

  async idProdottoSelectOption(option: string): Promise<void> {
    await this.idProdottoSelect.sendKeys(option);
  }

  getIdProdottoSelect(): ElementFinder {
    return this.idProdottoSelect;
  }

  async getIdProdottoSelectedOption(): Promise<string> {
    return await this.idProdottoSelect.element(by.css('option:checked')).getText();
  }

  async idOrdineSelectLastOption(): Promise<void> {
    await this.idOrdineSelect.all(by.tagName('option')).last().click();
  }

  async idOrdineSelectOption(option: string): Promise<void> {
    await this.idOrdineSelect.sendKeys(option);
  }

  getIdOrdineSelect(): ElementFinder {
    return this.idOrdineSelect;
  }

  async getIdOrdineSelectedOption(): Promise<string> {
    return await this.idOrdineSelect.element(by.css('option:checked')).getText();
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

export class LineaOrdineDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-lineaOrdine-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-lineaOrdine'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
