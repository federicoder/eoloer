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

  idRuoloPersonaSelect = element(by.id('field_idRuoloPersona'));
  idPersonaSelect = element(by.id('field_idPersona'));
  idUserPersonaSelect = element(by.id('field_idUserPersona'));
  idPraticaSelect = element(by.id('field_idPratica'));

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

  async idRuoloPersonaSelectLastOption(): Promise<void> {
    await this.idRuoloPersonaSelect.all(by.tagName('option')).last().click();
  }

  async idRuoloPersonaSelectOption(option: string): Promise<void> {
    await this.idRuoloPersonaSelect.sendKeys(option);
  }

  getIdRuoloPersonaSelect(): ElementFinder {
    return this.idRuoloPersonaSelect;
  }

  async getIdRuoloPersonaSelectedOption(): Promise<string> {
    return await this.idRuoloPersonaSelect.element(by.css('option:checked')).getText();
  }

  async idPersonaSelectLastOption(): Promise<void> {
    await this.idPersonaSelect.all(by.tagName('option')).last().click();
  }

  async idPersonaSelectOption(option: string): Promise<void> {
    await this.idPersonaSelect.sendKeys(option);
  }

  getIdPersonaSelect(): ElementFinder {
    return this.idPersonaSelect;
  }

  async getIdPersonaSelectedOption(): Promise<string> {
    return await this.idPersonaSelect.element(by.css('option:checked')).getText();
  }

  async idUserPersonaSelectLastOption(): Promise<void> {
    await this.idUserPersonaSelect.all(by.tagName('option')).last().click();
  }

  async idUserPersonaSelectOption(option: string): Promise<void> {
    await this.idUserPersonaSelect.sendKeys(option);
  }

  getIdUserPersonaSelect(): ElementFinder {
    return this.idUserPersonaSelect;
  }

  async getIdUserPersonaSelectedOption(): Promise<string> {
    return await this.idUserPersonaSelect.element(by.css('option:checked')).getText();
  }

  async idPraticaSelectLastOption(): Promise<void> {
    await this.idPraticaSelect.all(by.tagName('option')).last().click();
  }

  async idPraticaSelectOption(option: string): Promise<void> {
    await this.idPraticaSelect.sendKeys(option);
  }

  getIdPraticaSelect(): ElementFinder {
    return this.idPraticaSelect;
  }

  async getIdPraticaSelectedOption(): Promise<string> {
    return await this.idPraticaSelect.element(by.css('option:checked')).getText();
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
