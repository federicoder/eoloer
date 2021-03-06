import { element, by, ElementFinder } from 'protractor';

export class InvitatoComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-invitato div table .btn-danger'));
  title = element.all(by.css('jhi-invitato div h2#page-heading span')).first();
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

export class InvitatoUpdatePage {
  pageTitle = element(by.id('jhi-invitato-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  idInvitoRefInput = element(by.id('field_idInvitoRef'));
  tokenInvitoInput = element(by.id('field_tokenInvito'));
  canalePrimarioInvitoInput = element(by.id('field_canalePrimarioInvito'));
  canaleBackupInvitoInput = element(by.id('field_canaleBackupInvito'));
  statoInvitoInput = element(by.id('field_statoInvito'));
  idUserInvitatoInput = element(by.id('field_idUserInvitato'));
  idPersonaInvitataInput = element(by.id('field_idPersonaInvitata'));
  nomeUserInvitatoInput = element(by.id('field_nomeUserInvitato'));
  dataRispostaInvitoInput = element(by.id('field_dataRispostaInvito'));
  ruoloInvitatoInput = element(by.id('field_ruoloInvitato'));
  indInvitatiInput = element(by.id('field_indInvitati'));

  idUserPersonaSelect = element(by.id('field_idUserPersona'));
  idInvitoSelect = element(by.id('field_idInvito'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setIdInvitoRefInput(idInvitoRef: string): Promise<void> {
    await this.idInvitoRefInput.sendKeys(idInvitoRef);
  }

  async getIdInvitoRefInput(): Promise<string> {
    return await this.idInvitoRefInput.getAttribute('value');
  }

  async setTokenInvitoInput(tokenInvito: string): Promise<void> {
    await this.tokenInvitoInput.sendKeys(tokenInvito);
  }

  async getTokenInvitoInput(): Promise<string> {
    return await this.tokenInvitoInput.getAttribute('value');
  }

  async setCanalePrimarioInvitoInput(canalePrimarioInvito: string): Promise<void> {
    await this.canalePrimarioInvitoInput.sendKeys(canalePrimarioInvito);
  }

  async getCanalePrimarioInvitoInput(): Promise<string> {
    return await this.canalePrimarioInvitoInput.getAttribute('value');
  }

  async setCanaleBackupInvitoInput(canaleBackupInvito: string): Promise<void> {
    await this.canaleBackupInvitoInput.sendKeys(canaleBackupInvito);
  }

  async getCanaleBackupInvitoInput(): Promise<string> {
    return await this.canaleBackupInvitoInput.getAttribute('value');
  }

  async setStatoInvitoInput(statoInvito: string): Promise<void> {
    await this.statoInvitoInput.sendKeys(statoInvito);
  }

  async getStatoInvitoInput(): Promise<string> {
    return await this.statoInvitoInput.getAttribute('value');
  }

  async setIdUserInvitatoInput(idUserInvitato: string): Promise<void> {
    await this.idUserInvitatoInput.sendKeys(idUserInvitato);
  }

  async getIdUserInvitatoInput(): Promise<string> {
    return await this.idUserInvitatoInput.getAttribute('value');
  }

  async setIdPersonaInvitataInput(idPersonaInvitata: string): Promise<void> {
    await this.idPersonaInvitataInput.sendKeys(idPersonaInvitata);
  }

  async getIdPersonaInvitataInput(): Promise<string> {
    return await this.idPersonaInvitataInput.getAttribute('value');
  }

  async setNomeUserInvitatoInput(nomeUserInvitato: string): Promise<void> {
    await this.nomeUserInvitatoInput.sendKeys(nomeUserInvitato);
  }

  async getNomeUserInvitatoInput(): Promise<string> {
    return await this.nomeUserInvitatoInput.getAttribute('value');
  }

  async setDataRispostaInvitoInput(dataRispostaInvito: string): Promise<void> {
    await this.dataRispostaInvitoInput.sendKeys(dataRispostaInvito);
  }

  async getDataRispostaInvitoInput(): Promise<string> {
    return await this.dataRispostaInvitoInput.getAttribute('value');
  }

  async setRuoloInvitatoInput(ruoloInvitato: string): Promise<void> {
    await this.ruoloInvitatoInput.sendKeys(ruoloInvitato);
  }

  async getRuoloInvitatoInput(): Promise<string> {
    return await this.ruoloInvitatoInput.getAttribute('value');
  }

  async setIndInvitatiInput(indInvitati: string): Promise<void> {
    await this.indInvitatiInput.sendKeys(indInvitati);
  }

  async getIndInvitatiInput(): Promise<string> {
    return await this.indInvitatiInput.getAttribute('value');
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

  async idInvitoSelectLastOption(): Promise<void> {
    await this.idInvitoSelect.all(by.tagName('option')).last().click();
  }

  async idInvitoSelectOption(option: string): Promise<void> {
    await this.idInvitoSelect.sendKeys(option);
  }

  getIdInvitoSelect(): ElementFinder {
    return this.idInvitoSelect;
  }

  async getIdInvitoSelectedOption(): Promise<string> {
    return await this.idInvitoSelect.element(by.css('option:checked')).getText();
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

export class InvitatoDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-invitato-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-invitato'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
