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

  idAttivitaInput = element(by.id('field_idAttivita'));
  idUserAmmessoInput = element(by.id('field_idUserAmmesso'));
  ruoloInput = element(by.id('field_ruolo'));
  idUserConcedenteInput = element(by.id('field_idUserConcedente'));
  statoAssegnazioneInput = element(by.id('field_statoAssegnazione'));

  ruoloSelect = element(by.id('field_ruolo'));
  userPersonaSelect = element(by.id('field_userPersona'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setIdAttivitaInput(idAttivita: string): Promise<void> {
    await this.idAttivitaInput.sendKeys(idAttivita);
  }

  async getIdAttivitaInput(): Promise<string> {
    return await this.idAttivitaInput.getAttribute('value');
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