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

  idOrdineInput = element(by.id('field_idOrdine'));
  idProdottoInput = element(by.id('field_idProdotto'));
  quantitaInput = element(by.id('field_quantita'));
  importoInput = element(by.id('field_importo'));
  codIvaInput = element(by.id('field_codIva'));

  ordineSelect = element(by.id('field_ordine'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setIdOrdineInput(idOrdine: string): Promise<void> {
    await this.idOrdineInput.sendKeys(idOrdine);
  }

  async getIdOrdineInput(): Promise<string> {
    return await this.idOrdineInput.getAttribute('value');
  }

  async setIdProdottoInput(idProdotto: string): Promise<void> {
    await this.idProdottoInput.sendKeys(idProdotto);
  }

  async getIdProdottoInput(): Promise<string> {
    return await this.idProdottoInput.getAttribute('value');
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

  async ordineSelectLastOption(): Promise<void> {
    await this.ordineSelect.all(by.tagName('option')).last().click();
  }

  async ordineSelectOption(option: string): Promise<void> {
    await this.ordineSelect.sendKeys(option);
  }

  getOrdineSelect(): ElementFinder {
    return this.ordineSelect;
  }

  async getOrdineSelectedOption(): Promise<string> {
    return await this.ordineSelect.element(by.css('option:checked')).getText();
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
