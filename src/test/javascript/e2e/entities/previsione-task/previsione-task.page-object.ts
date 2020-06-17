import { element, by, ElementFinder } from 'protractor';

export class PrevisioneTaskComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-previsione-task div table .btn-danger'));
  title = element.all(by.css('jhi-previsione-task div h2#page-heading span')).first();
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

export class PrevisioneTaskUpdatePage {
  pageTitle = element(by.id('jhi-previsione-task-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  idTaskRefInput = element(by.id('field_idTaskRef'));
  qntOrdineInput = element(by.id('field_qntOrdine'));
  prcPrevisioneInput = element(by.id('field_prcPrevisione'));
  checkListInput = element(by.id('field_checkList'));
  idTaskMilestoneInput = element(by.id('field_idTaskMilestone'));
  tipoTaskInput = element(by.id('field_tipoTask'));
  versionInput = element(by.id('field_version'));

  previsioneTaskSelect = element(by.id('field_previsioneTask'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setIdTaskRefInput(idTaskRef: string): Promise<void> {
    await this.idTaskRefInput.sendKeys(idTaskRef);
  }

  async getIdTaskRefInput(): Promise<string> {
    return await this.idTaskRefInput.getAttribute('value');
  }

  async setQntOrdineInput(qntOrdine: string): Promise<void> {
    await this.qntOrdineInput.sendKeys(qntOrdine);
  }

  async getQntOrdineInput(): Promise<string> {
    return await this.qntOrdineInput.getAttribute('value');
  }

  async setPrcPrevisioneInput(prcPrevisione: string): Promise<void> {
    await this.prcPrevisioneInput.sendKeys(prcPrevisione);
  }

  async getPrcPrevisioneInput(): Promise<string> {
    return await this.prcPrevisioneInput.getAttribute('value');
  }

  async setCheckListInput(checkList: string): Promise<void> {
    await this.checkListInput.sendKeys(checkList);
  }

  async getCheckListInput(): Promise<string> {
    return await this.checkListInput.getAttribute('value');
  }

  async setIdTaskMilestoneInput(idTaskMilestone: string): Promise<void> {
    await this.idTaskMilestoneInput.sendKeys(idTaskMilestone);
  }

  async getIdTaskMilestoneInput(): Promise<string> {
    return await this.idTaskMilestoneInput.getAttribute('value');
  }

  async setTipoTaskInput(tipoTask: string): Promise<void> {
    await this.tipoTaskInput.sendKeys(tipoTask);
  }

  async getTipoTaskInput(): Promise<string> {
    return await this.tipoTaskInput.getAttribute('value');
  }

  async setVersionInput(version: string): Promise<void> {
    await this.versionInput.sendKeys(version);
  }

  async getVersionInput(): Promise<string> {
    return await this.versionInput.getAttribute('value');
  }

  async previsioneTaskSelectLastOption(): Promise<void> {
    await this.previsioneTaskSelect.all(by.tagName('option')).last().click();
  }

  async previsioneTaskSelectOption(option: string): Promise<void> {
    await this.previsioneTaskSelect.sendKeys(option);
  }

  getPrevisioneTaskSelect(): ElementFinder {
    return this.previsioneTaskSelect;
  }

  async getPrevisioneTaskSelectedOption(): Promise<string> {
    return await this.previsioneTaskSelect.element(by.css('option:checked')).getText();
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

export class PrevisioneTaskDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-previsioneTask-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-previsioneTask'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
