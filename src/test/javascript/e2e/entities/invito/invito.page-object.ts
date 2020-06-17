import { element, by, ElementFinder } from 'protractor';

export class InvitoComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-invito div table .btn-danger'));
  title = element.all(by.css('jhi-invito div h2#page-heading span')).first();
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

export class InvitoUpdatePage {
  pageTitle = element(by.id('jhi-invito-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  idStudioProfessionaleInput = element(by.id('field_idStudioProfessionale'));
  dataInvitoInput = element(by.id('field_dataInvito'));
  idUserInvitanteInput = element(by.id('field_idUserInvitante'));
  nomeUserInvitanteInput = element(by.id('field_nomeUserInvitante'));
  dataScadenzaInvitoInput = element(by.id('field_dataScadenzaInvito'));
  testoInvitoInput = element(by.id('field_testoInvito'));
  idPraticaInput = element(by.id('field_idPratica'));
  idAttivitaInput = element(by.id('field_idAttivita'));
  luogoFisicoInput = element(by.id('field_luogoFisico'));
  indicazioniLuogoInput = element(by.id('field_indicazioniLuogo'));
  dataInizioInput = element(by.id('field_dataInizio'));
  oraInizioInput = element(by.id('field_oraInizio'));
  dataFineInput = element(by.id('field_dataFine'));
  oraFineInput = element(by.id('field_oraFine'));
  urlStanzaVirtualeInput = element(by.id('field_urlStanzaVirtuale'));
  discriminatorInput = element(by.id('field_discriminator'));

  idStudioProfessionaleSelect = element(by.id('field_idStudioProfessionale'));
  assegnazioneTaskSelect = element(by.id('field_assegnazioneTask'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setIdStudioProfessionaleInput(idStudioProfessionale: string): Promise<void> {
    await this.idStudioProfessionaleInput.sendKeys(idStudioProfessionale);
  }

  async getIdStudioProfessionaleInput(): Promise<string> {
    return await this.idStudioProfessionaleInput.getAttribute('value');
  }

  async setDataInvitoInput(dataInvito: string): Promise<void> {
    await this.dataInvitoInput.sendKeys(dataInvito);
  }

  async getDataInvitoInput(): Promise<string> {
    return await this.dataInvitoInput.getAttribute('value');
  }

  async setIdUserInvitanteInput(idUserInvitante: string): Promise<void> {
    await this.idUserInvitanteInput.sendKeys(idUserInvitante);
  }

  async getIdUserInvitanteInput(): Promise<string> {
    return await this.idUserInvitanteInput.getAttribute('value');
  }

  async setNomeUserInvitanteInput(nomeUserInvitante: string): Promise<void> {
    await this.nomeUserInvitanteInput.sendKeys(nomeUserInvitante);
  }

  async getNomeUserInvitanteInput(): Promise<string> {
    return await this.nomeUserInvitanteInput.getAttribute('value');
  }

  async setDataScadenzaInvitoInput(dataScadenzaInvito: string): Promise<void> {
    await this.dataScadenzaInvitoInput.sendKeys(dataScadenzaInvito);
  }

  async getDataScadenzaInvitoInput(): Promise<string> {
    return await this.dataScadenzaInvitoInput.getAttribute('value');
  }

  async setTestoInvitoInput(testoInvito: string): Promise<void> {
    await this.testoInvitoInput.sendKeys(testoInvito);
  }

  async getTestoInvitoInput(): Promise<string> {
    return await this.testoInvitoInput.getAttribute('value');
  }

  async setIdPraticaInput(idPratica: string): Promise<void> {
    await this.idPraticaInput.sendKeys(idPratica);
  }

  async getIdPraticaInput(): Promise<string> {
    return await this.idPraticaInput.getAttribute('value');
  }

  async setIdAttivitaInput(idAttivita: string): Promise<void> {
    await this.idAttivitaInput.sendKeys(idAttivita);
  }

  async getIdAttivitaInput(): Promise<string> {
    return await this.idAttivitaInput.getAttribute('value');
  }

  async setLuogoFisicoInput(luogoFisico: string): Promise<void> {
    await this.luogoFisicoInput.sendKeys(luogoFisico);
  }

  async getLuogoFisicoInput(): Promise<string> {
    return await this.luogoFisicoInput.getAttribute('value');
  }

  async setIndicazioniLuogoInput(indicazioniLuogo: string): Promise<void> {
    await this.indicazioniLuogoInput.sendKeys(indicazioniLuogo);
  }

  async getIndicazioniLuogoInput(): Promise<string> {
    return await this.indicazioniLuogoInput.getAttribute('value');
  }

  async setDataInizioInput(dataInizio: string): Promise<void> {
    await this.dataInizioInput.sendKeys(dataInizio);
  }

  async getDataInizioInput(): Promise<string> {
    return await this.dataInizioInput.getAttribute('value');
  }

  async setOraInizioInput(oraInizio: string): Promise<void> {
    await this.oraInizioInput.sendKeys(oraInizio);
  }

  async getOraInizioInput(): Promise<string> {
    return await this.oraInizioInput.getAttribute('value');
  }

  async setDataFineInput(dataFine: string): Promise<void> {
    await this.dataFineInput.sendKeys(dataFine);
  }

  async getDataFineInput(): Promise<string> {
    return await this.dataFineInput.getAttribute('value');
  }

  async setOraFineInput(oraFine: string): Promise<void> {
    await this.oraFineInput.sendKeys(oraFine);
  }

  async getOraFineInput(): Promise<string> {
    return await this.oraFineInput.getAttribute('value');
  }

  async setUrlStanzaVirtualeInput(urlStanzaVirtuale: string): Promise<void> {
    await this.urlStanzaVirtualeInput.sendKeys(urlStanzaVirtuale);
  }

  async getUrlStanzaVirtualeInput(): Promise<string> {
    return await this.urlStanzaVirtualeInput.getAttribute('value');
  }

  async setDiscriminatorInput(discriminator: string): Promise<void> {
    await this.discriminatorInput.sendKeys(discriminator);
  }

  async getDiscriminatorInput(): Promise<string> {
    return await this.discriminatorInput.getAttribute('value');
  }

  async idStudioProfessionaleSelectLastOption(): Promise<void> {
    await this.idStudioProfessionaleSelect.all(by.tagName('option')).last().click();
  }

  async idStudioProfessionaleSelectOption(option: string): Promise<void> {
    await this.idStudioProfessionaleSelect.sendKeys(option);
  }

  getIdStudioProfessionaleSelect(): ElementFinder {
    return this.idStudioProfessionaleSelect;
  }

  async getIdStudioProfessionaleSelectedOption(): Promise<string> {
    return await this.idStudioProfessionaleSelect.element(by.css('option:checked')).getText();
  }

  async assegnazioneTaskSelectLastOption(): Promise<void> {
    await this.assegnazioneTaskSelect.all(by.tagName('option')).last().click();
  }

  async assegnazioneTaskSelectOption(option: string): Promise<void> {
    await this.assegnazioneTaskSelect.sendKeys(option);
  }

  getAssegnazioneTaskSelect(): ElementFinder {
    return this.assegnazioneTaskSelect;
  }

  async getAssegnazioneTaskSelectedOption(): Promise<string> {
    return await this.assegnazioneTaskSelect.element(by.css('option:checked')).getText();
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

export class InvitoDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-invito-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-invito'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
