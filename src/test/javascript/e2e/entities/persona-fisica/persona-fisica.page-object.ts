import { element, by, ElementFinder } from 'protractor';

export class PersonaFisicaComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-persona-fisica div table .btn-danger'));
  title = element.all(by.css('jhi-persona-fisica div h2#page-heading span')).first();
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

export class PersonaFisicaUpdatePage {
  pageTitle = element(by.id('jhi-persona-fisica-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  idPersonaRefInput = element(by.id('field_idPersonaRef'));
  idRuoloPersonaRefInput = element(by.id('field_idRuoloPersonaRef'));
  titoloInput = element(by.id('field_titolo'));
  cognomeInput = element(by.id('field_cognome'));
  nomeInput = element(by.id('field_nome'));
  dataDiNascitaInput = element(by.id('field_dataDiNascita'));
  luogoDiNascitaInput = element(by.id('field_luogoDiNascita'));
  professioneInput = element(by.id('field_professione'));

  idPersonaRefSelect = element(by.id('field_idPersonaRef'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setIdPersonaRefInput(idPersonaRef: string): Promise<void> {
    await this.idPersonaRefInput.sendKeys(idPersonaRef);
  }

  async getIdPersonaRefInput(): Promise<string> {
    return await this.idPersonaRefInput.getAttribute('value');
  }

  async setIdRuoloPersonaRefInput(idRuoloPersonaRef: string): Promise<void> {
    await this.idRuoloPersonaRefInput.sendKeys(idRuoloPersonaRef);
  }

  async getIdRuoloPersonaRefInput(): Promise<string> {
    return await this.idRuoloPersonaRefInput.getAttribute('value');
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

  async idPersonaRefSelectLastOption(): Promise<void> {
    await this.idPersonaRefSelect.all(by.tagName('option')).last().click();
  }

  async idPersonaRefSelectOption(option: string): Promise<void> {
    await this.idPersonaRefSelect.sendKeys(option);
  }

  getIdPersonaRefSelect(): ElementFinder {
    return this.idPersonaRefSelect;
  }

  async getIdPersonaRefSelectedOption(): Promise<string> {
    return await this.idPersonaRefSelect.element(by.css('option:checked')).getText();
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

export class PersonaFisicaDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-personaFisica-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-personaFisica'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
