import { element, by, ElementFinder } from 'protractor';

export class CondivisionePraticaComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-condivisione-pratica div table .btn-danger'));
  title = element.all(by.css('jhi-condivisione-pratica div h2#page-heading span')).first();
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

export class CondivisionePraticaUpdatePage {
  pageTitle = element(by.id('jhi-condivisione-pratica-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  idUserAmmessoInput = element(by.id('field_idUserAmmesso'));
  ruoloInput = element(by.id('field_ruolo'));
  idUserConcedenteInput = element(by.id('field_idUserConcedente'));
  statoInvitoInput = element(by.id('field_statoInvito'));
  idPraticaRefInput = element(by.id('field_idPraticaRef'));

  ruoloSelect = element(by.id('field_ruolo'));
  idUserConcedenteSelect = element(by.id('field_idUserConcedente'));
  idUserAmmessoSelect = element(by.id('field_idUserAmmesso'));
  idPraticaRefSelect = element(by.id('field_idPraticaRef'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setIdUserAmmessoInput(idUserAmmesso: string): Promise<void> {
    await this.idUserAmmessoInput.sendKeys(idUserAmmesso);
  }

  async getIdUserAmmessoInput(): Promise<string> {
    return await this.idUserAmmessoInput.getAttribute('value');
  }

  async setRuoloInput(ruolo: string): Promise<void> {
    await this.ruoloInput.sendKeys(ruolo);
  }

  async getRuoloInput(): Promise<string> {
    return await this.ruoloInput.getAttribute('value');
  }

  async setIdUserConcedenteInput(idUserConcedente: string): Promise<void> {
    await this.idUserConcedenteInput.sendKeys(idUserConcedente);
  }

  async getIdUserConcedenteInput(): Promise<string> {
    return await this.idUserConcedenteInput.getAttribute('value');
  }

  async setStatoInvitoInput(statoInvito: string): Promise<void> {
    await this.statoInvitoInput.sendKeys(statoInvito);
  }

  async getStatoInvitoInput(): Promise<string> {
    return await this.statoInvitoInput.getAttribute('value');
  }

  async setIdPraticaRefInput(idPraticaRef: string): Promise<void> {
    await this.idPraticaRefInput.sendKeys(idPraticaRef);
  }

  async getIdPraticaRefInput(): Promise<string> {
    return await this.idPraticaRefInput.getAttribute('value');
  }

  async ruoloSelectLastOption(): Promise<void> {
    await this.ruoloSelect.all(by.tagName('option')).last().click();
  }

  async ruoloSelectOption(option: string): Promise<void> {
    await this.ruoloSelect.sendKeys(option);
  }

  getRuoloSelect(): ElementFinder {
    return this.ruoloSelect;
  }

  async getRuoloSelectedOption(): Promise<string> {
    return await this.ruoloSelect.element(by.css('option:checked')).getText();
  }

  async idUserConcedenteSelectLastOption(): Promise<void> {
    await this.idUserConcedenteSelect.all(by.tagName('option')).last().click();
  }

  async idUserConcedenteSelectOption(option: string): Promise<void> {
    await this.idUserConcedenteSelect.sendKeys(option);
  }

  getIdUserConcedenteSelect(): ElementFinder {
    return this.idUserConcedenteSelect;
  }

  async getIdUserConcedenteSelectedOption(): Promise<string> {
    return await this.idUserConcedenteSelect.element(by.css('option:checked')).getText();
  }

  async idUserAmmessoSelectLastOption(): Promise<void> {
    await this.idUserAmmessoSelect.all(by.tagName('option')).last().click();
  }

  async idUserAmmessoSelectOption(option: string): Promise<void> {
    await this.idUserAmmessoSelect.sendKeys(option);
  }

  getIdUserAmmessoSelect(): ElementFinder {
    return this.idUserAmmessoSelect;
  }

  async getIdUserAmmessoSelectedOption(): Promise<string> {
    return await this.idUserAmmessoSelect.element(by.css('option:checked')).getText();
  }

  async idPraticaRefSelectLastOption(): Promise<void> {
    await this.idPraticaRefSelect.all(by.tagName('option')).last().click();
  }

  async idPraticaRefSelectOption(option: string): Promise<void> {
    await this.idPraticaRefSelect.sendKeys(option);
  }

  getIdPraticaRefSelect(): ElementFinder {
    return this.idPraticaRefSelect;
  }

  async getIdPraticaRefSelectedOption(): Promise<string> {
    return await this.idPraticaRefSelect.element(by.css('option:checked')).getText();
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

export class CondivisionePraticaDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-condivisionePratica-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-condivisionePratica'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
