import { element, by, ElementFinder } from 'protractor';

export class OrdineComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-ordine div table .btn-danger'));
  title = element.all(by.css('jhi-ordine div h2#page-heading span')).first();
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

export class OrdineUpdatePage {
  pageTitle = element(by.id('jhi-ordine-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  idStudioProfessionaleRefInput = element(by.id('field_idStudioProfessionaleRef'));
  statoOrdineInput = element(by.id('field_statoOrdine'));
  totImponibileInput = element(by.id('field_totImponibile'));
  totIvaInput = element(by.id('field_totIva'));
  totOrdineInput = element(by.id('field_totOrdine'));

  studioProfessionaleSelect = element(by.id('field_studioProfessionale'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setIdStudioProfessionaleRefInput(idStudioProfessionaleRef: string): Promise<void> {
    await this.idStudioProfessionaleRefInput.sendKeys(idStudioProfessionaleRef);
  }

  async getIdStudioProfessionaleRefInput(): Promise<string> {
    return await this.idStudioProfessionaleRefInput.getAttribute('value');
  }

  async setStatoOrdineInput(statoOrdine: string): Promise<void> {
    await this.statoOrdineInput.sendKeys(statoOrdine);
  }

  async getStatoOrdineInput(): Promise<string> {
    return await this.statoOrdineInput.getAttribute('value');
  }

  async setTotImponibileInput(totImponibile: string): Promise<void> {
    await this.totImponibileInput.sendKeys(totImponibile);
  }

  async getTotImponibileInput(): Promise<string> {
    return await this.totImponibileInput.getAttribute('value');
  }

  async setTotIvaInput(totIva: string): Promise<void> {
    await this.totIvaInput.sendKeys(totIva);
  }

  async getTotIvaInput(): Promise<string> {
    return await this.totIvaInput.getAttribute('value');
  }

  async setTotOrdineInput(totOrdine: string): Promise<void> {
    await this.totOrdineInput.sendKeys(totOrdine);
  }

  async getTotOrdineInput(): Promise<string> {
    return await this.totOrdineInput.getAttribute('value');
  }

  async studioProfessionaleSelectLastOption(): Promise<void> {
    await this.studioProfessionaleSelect.all(by.tagName('option')).last().click();
  }

  async studioProfessionaleSelectOption(option: string): Promise<void> {
    await this.studioProfessionaleSelect.sendKeys(option);
  }

  getStudioProfessionaleSelect(): ElementFinder {
    return this.studioProfessionaleSelect;
  }

  async getStudioProfessionaleSelectedOption(): Promise<string> {
    return await this.studioProfessionaleSelect.element(by.css('option:checked')).getText();
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

export class OrdineDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-ordine-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-ordine'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
