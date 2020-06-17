import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAllegatoTemplateTask } from 'app/shared/model/allegato-template-task.model';
import { AllegatoTemplateTaskService } from './allegato-template-task.service';

@Component({
  templateUrl: './allegato-template-task-delete-dialog.component.html',
})
export class AllegatoTemplateTaskDeleteDialogComponent {
  allegatoTemplateTask?: IAllegatoTemplateTask;

  constructor(
    protected allegatoTemplateTaskService: AllegatoTemplateTaskService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.allegatoTemplateTaskService.delete(id).subscribe(() => {
      this.eventManager.broadcast('allegatoTemplateTaskListModification');
      this.activeModal.close();
    });
  }
}
