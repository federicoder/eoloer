import { element, by, ElementFinder } from 'protractor';

export class RuoloOrganizzazioneComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-ruolo-organizzazione div table .btn-danger'));
  title = element.all(by.css('jhi-ruolo-organizzazione div h2#page-heading span')).first();
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

export class RuoloOrganizzazioneUpdatePage {
  pageTitle = element(by.id('jhi-ruolo-organizzazione-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  idRuoloOrganizzazioneInput = element(by.id('field_idRuoloOrganizzazione'));
  ruoloInOrgInput = element(by.id('field_ruoloInOrg'));

  idRuoloOrganizzazioneSelect = element(by.id('field_idRuoloOrganizzazione'));
  idRuoloOrganizzazioneSelect = element(by.id('field_idRuoloOrganizzazione'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setIdRuoloOrganizzazioneInput(idRuoloOrganizzazione: string): Promise<void> {
    await this.idRuoloOrganizzazioneInput.sendKeys(idRuoloOrganizzazione);
  }

  async getIdRuoloOrganizzazioneInput(): Promise<string> {
    return await this.idRuoloOrganizzazioneInput.getAttribute('value');
  }

  async setRuoloInOrgInput(ruoloInOrg: string): Promise<void> {
    await this.ruoloInOrgInput.sendKeys(ruoloInOrg);
  }

  async getRuoloInOrgInput(): Promise<string> {
    return await this.ruoloInOrgInput.getAttribute('value');
  }

  async idRuoloOrganizzazioneSelectLastOption(): Promise<void> {
    await this.idRuoloOrganizzazioneSelect.all(by.tagName('option')).last().click();
  }

  async idRuoloOrganizzazioneSelectOption(option: string): Promise<void> {
    await this.idRuoloOrganizzazioneSelect.sendKeys(option);
  }

  getIdRuoloOrganizzazioneSelect(): ElementFinder {
    return this.idRuoloOrganizzazioneSelect;
  }

  async getIdRuoloOrganizzazioneSelectedOption(): Promise<string> {
    return await this.idRuoloOrganizzazioneSelect.element(by.css('option:checked')).getText();
  }

  async idRuoloOrganizzazioneSelectLastOption(): Promise<void> {
    await this.idRuoloOrganizzazioneSelect.all(by.tagName('option')).last().click();
  }

  async idRuoloOrganizzazioneSelectOption(option: string): Promise<void> {
    await this.idRuoloOrganizzazioneSelect.sendKeys(option);
  }

  getIdRuoloOrganizzazioneSelect(): ElementFinder {
    return this.idRuoloOrganizzazioneSelect;
  }

  async getIdRuoloOrganizzazioneSelectedOption(): Promise<string> {
    return await this.idRuoloOrganizzazioneSelect.element(by.css('option:checked')).getText();
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

export class RuoloOrganizzazioneDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-ruoloOrganizzazione-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-ruoloOrganizzazione'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
