import { element, by, ElementFinder } from 'protractor';

export class IndirizzoPersonaComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-indirizzo-persona div table .btn-danger'));
  title = element.all(by.css('jhi-indirizzo-persona div h2#page-heading span')).first();
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

export class IndirizzoPersonaUpdatePage {
  pageTitle = element(by.id('jhi-indirizzo-persona-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  idPersonaRefInput = element(by.id('field_idPersonaRef'));
  indirizzoInput = element(by.id('field_indirizzo'));
  comuneInput = element(by.id('field_comune'));
  capInput = element(by.id('field_cap'));
  provinciaInput = element(by.id('field_provincia'));
  regioneInput = element(by.id('field_regione'));
  nazioneInput = element(by.id('field_nazione'));

  idPersonaSelect = element(by.id('field_idPersona'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setIdPersonaRefInput(idPersonaRef: string): Promise<void> {
    await this.idPersonaRefInput.sendKeys(idPersonaRef);
  }

  async getIdPersonaRefInput(): Promise<string> {
    return await this.idPersonaRefInput.getAttribute('value');
  }

  async setIndirizzoInput(indirizzo: string): Promise<void> {
    await this.indirizzoInput.sendKeys(indirizzo);
  }

  async getIndirizzoInput(): Promise<string> {
    return await this.indirizzoInput.getAttribute('value');
  }

  async setComuneInput(comune: string): Promise<void> {
    await this.comuneInput.sendKeys(comune);
  }

  async getComuneInput(): Promise<string> {
    return await this.comuneInput.getAttribute('value');
  }

  async setCapInput(cap: string): Promise<void> {
    await this.capInput.sendKeys(cap);
  }

  async getCapInput(): Promise<string> {
    return await this.capInput.getAttribute('value');
  }

  async setProvinciaInput(provincia: string): Promise<void> {
    await this.provinciaInput.sendKeys(provincia);
  }

  async getProvinciaInput(): Promise<string> {
    return await this.provinciaInput.getAttribute('value');
  }

  async setRegioneInput(regione: string): Promise<void> {
    await this.regioneInput.sendKeys(regione);
  }

  async getRegioneInput(): Promise<string> {
    return await this.regioneInput.getAttribute('value');
  }

  async setNazioneInput(nazione: string): Promise<void> {
    await this.nazioneInput.sendKeys(nazione);
  }

  async getNazioneInput(): Promise<string> {
    return await this.nazioneInput.getAttribute('value');
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

export class IndirizzoPersonaDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-indirizzoPersona-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-indirizzoPersona'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
