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
  idPraticaInput = element(by.id('field_idPratica'));

  ruoloSelect = element(by.id('field_ruolo'));
  idUserConcedenteSelect = element(by.id('field_idUserConcedente'));
  praticaSelect = element(by.id('field_pratica'));
  userPersonaSelect = element(by.id('field_userPersona'));

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

  async setIdPraticaInput(idPratica: string): Promise<void> {
    await this.idPraticaInput.sendKeys(idPratica);
  }

  async getIdPraticaInput(): Promise<string> {
    return await this.idPraticaInput.getAttribute('value');
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

  async praticaSelectLastOption(): Promise<void> {
    await this.praticaSelect.all(by.tagName('option')).last().click();
  }

  async praticaSelectOption(option: string): Promise<void> {
    await this.praticaSelect.sendKeys(option);
  }

  getPraticaSelect(): ElementFinder {
    return this.praticaSelect;
  }

  async getPraticaSelectedOption(): Promise<string> {
    return await this.praticaSelect.element(by.css('option:checked')).getText();
  }

  async userPersonaSelectLastOption(): Promise<void> {
    await this.userPersonaSelect.all(by.tagName('option')).last().click();
  }

  async userPersonaSelectOption(option: string): Promise<void> {
    await this.userPersonaSelect.sendKeys(option);
  }

  getUserPersonaSelect(): ElementFinder {
    return this.userPersonaSelect;
  }

  async getUserPersonaSelectedOption(): Promise<string> {
    return await this.userPersonaSelect.element(by.css('option:checked')).getText();
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
