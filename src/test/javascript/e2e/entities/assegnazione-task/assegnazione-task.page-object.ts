import { element, by, ElementFinder } from 'protractor';

export class AssegnazioneTaskComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-assegnazione-task div table .btn-danger'));
  title = element.all(by.css('jhi-assegnazione-task div h2#page-heading span')).first();
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

export class AssegnazioneTaskUpdatePage {
  pageTitle = element(by.id('jhi-assegnazione-task-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  idTaskRefInput = element(by.id('field_idTaskRef'));
  idUserAmmessoInput = element(by.id('field_idUserAmmesso'));
  ruoloInput = element(by.id('field_ruolo'));
  idUserConcedenteInput = element(by.id('field_idUserConcedente'));
  statoAssegnazioneInput = element(by.id('field_statoAssegnazione'));

  idTaskSelect = element(by.id('field_idTask'));
  idRuoloPersonaSelect = element(by.id('field_idRuoloPersona'));
  idPersonaSelect = element(by.id('field_idPersona'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setIdTaskRefInput(idTaskRef: string): Promise<void> {
    await this.idTaskRefInput.sendKeys(idTaskRef);
  }

  async getIdTaskRefInput(): Promise<string> {
    return await this.idTaskRefInput.getAttribute('value');
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

  async setStatoAssegnazioneInput(statoAssegnazione: string): Promise<void> {
    await this.statoAssegnazioneInput.sendKeys(statoAssegnazione);
  }

  async getStatoAssegnazioneInput(): Promise<string> {
    return await this.statoAssegnazioneInput.getAttribute('value');
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

export class AssegnazioneTaskDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-assegnazioneTask-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-assegnazioneTask'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
