import { element, by, ElementFinder } from 'protractor';

export class PersonaComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-persona div table .btn-danger'));
  title = element.all(by.css('jhi-persona div h2#page-heading span')).first();
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

export class PersonaUpdatePage {
  pageTitle = element(by.id('jhi-persona-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  idStudioProfessionaleRefInput = element(by.id('field_idStudioProfessionaleRef'));
  codiceFiscaleInput = element(by.id('field_codiceFiscale'));
  areaDiInteresseInput = element(by.id('field_areaDiInteresse'));
  titoloInput = element(by.id('field_titolo'));
  cognomeInput = element(by.id('field_cognome'));
  nomeInput = element(by.id('field_nome'));
  dataDiNascitaInput = element(by.id('field_dataDiNascita'));
  luogoDiNascitaInput = element(by.id('field_luogoDiNascita'));
  professioneInput = element(by.id('field_professione'));
  tipoInput = element(by.id('field_tipo'));
  discriminatorInput = element(by.id('field_discriminator'));
  idRuoloPersonaRefInput = element(by.id('field_idRuoloPersonaRef'));
  tipoRuoloUtenteInput = element(by.id('field_tipoRuoloUtente'));

  idSelect = element(by.id('field_id'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setIdStudioProfessionaleRefInput(idStudioProfessionaleRef: string): Promise<void> {
    await this.idStudioProfessionaleRefInput.sendKeys(idStudioProfessionaleRef);
  }

  async getIdStudioProfessionaleRefInput(): Promise<string> {
    return await this.idStudioProfessionaleRefInput.getAttribute('value');
  }

  async setCodiceFiscaleInput(codiceFiscale: string): Promise<void> {
    await this.codiceFiscaleInput.sendKeys(codiceFiscale);
  }

  async getCodiceFiscaleInput(): Promise<string> {
    return await this.codiceFiscaleInput.getAttribute('value');
  }

  async setAreaDiInteresseInput(areaDiInteresse: string): Promise<void> {
    await this.areaDiInteresseInput.sendKeys(areaDiInteresse);
  }

  async getAreaDiInteresseInput(): Promise<string> {
    return await this.areaDiInteresseInput.getAttribute('value');
  }

  async setTitoloInput(titolo: string): Promise<void> {
    await this.titoloInput.sendKeys(titolo);
  }

  async getTitoloInput(): Promise<string> {
    return await this.titoloInput.getAttribute('value');
  }

  async setCognomeInput(cognome: string): Promise<void> {
    await this.cognomeInput.sendKeys(cognome);
  }

  async getCognomeInput(): Promise<string> {
    return await this.cognomeInput.getAttribute('value');
  }

  async setNomeInput(nome: string): Promise<void> {
    await this.nomeInput.sendKeys(nome);
  }

  async getNomeInput(): Promise<string> {
    return await this.nomeInput.getAttribute('value');
  }

  async setDataDiNascitaInput(dataDiNascita: string): Promise<void> {
    await this.dataDiNascitaInput.sendKeys(dataDiNascita);
  }

  async getDataDiNascitaInput(): Promise<string> {
    return await this.dataDiNascitaInput.getAttribute('value');
  }

  async setLuogoDiNascitaInput(luogoDiNascita: string): Promise<void> {
    await this.luogoDiNascitaInput.sendKeys(luogoDiNascita);
  }

  async getLuogoDiNascitaInput(): Promise<string> {
    return await this.luogoDiNascitaInput.getAttribute('value');
  }

  async setProfessioneInput(professione: string): Promise<void> {
    await this.professioneInput.sendKeys(professione);
  }

  async getProfessioneInput(): Promise<string> {
    return await this.professioneInput.getAttribute('value');
  }

  async setTipoInput(tipo: string): Promise<void> {
    await this.tipoInput.sendKeys(tipo);
  }

  async getTipoInput(): Promise<string> {
    return await this.tipoInput.getAttribute('value');
  }

  async setDiscriminatorInput(discriminator: string): Promise<void> {
    await this.discriminatorInput.sendKeys(discriminator);
  }

  async getDiscriminatorInput(): Promise<string> {
    return await this.discriminatorInput.getAttribute('value');
  }

  async setIdRuoloPersonaRefInput(idRuoloPersonaRef: string): Promise<void> {
    await this.idRuoloPersonaRefInput.sendKeys(idRuoloPersonaRef);
  }

  async getIdRuoloPersonaRefInput(): Promise<string> {
    return await this.idRuoloPersonaRefInput.getAttribute('value');
  }

  async setTipoRuoloUtenteInput(tipoRuoloUtente: string): Promise<void> {
    await this.tipoRuoloUtenteInput.sendKeys(tipoRuoloUtente);
  }

  async getTipoRuoloUtenteInput(): Promise<string> {
    return await this.tipoRuoloUtenteInput.getAttribute('value');
  }

  async idSelectLastOption(): Promise<void> {
    await this.idSelect.all(by.tagName('option')).last().click();
  }

  async idSelectOption(option: string): Promise<void> {
    await this.idSelect.sendKeys(option);
  }

  getIdSelect(): ElementFinder {
    return this.idSelect;
  }

  async getIdSelectedOption(): Promise<string> {
    return await this.idSelect.element(by.css('option:checked')).getText();
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

export class PersonaDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-persona-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-persona'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
