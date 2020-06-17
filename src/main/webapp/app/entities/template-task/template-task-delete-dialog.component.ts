import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITemplateTask } from 'app/shared/model/template-task.model';
import { TemplateTaskService } from './template-task.service';

@Component({
  templateUrl: './template-task-delete-dialog.component.html',
})
export class TemplateTaskDeleteDialogComponent {
  templateTask?: ITemplateTask;

  constructor(
    protected templateTaskService: TemplateTaskService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.templateTaskService.delete(id).subscribe(() => {
      this.eventManager.broadcast('templateTaskListModification');
      this.activeModal.close();
    });
  }
}
