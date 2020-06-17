import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPrevisioneTask } from 'app/shared/model/previsione-task.model';
import { PrevisioneTaskService } from './previsione-task.service';

@Component({
  templateUrl: './previsione-task-delete-dialog.component.html',
})
export class PrevisioneTaskDeleteDialogComponent {
  previsioneTask?: IPrevisioneTask;

  constructor(
    protected previsioneTaskService: PrevisioneTaskService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.previsioneTaskService.delete(id).subscribe(() => {
      this.eventManager.broadcast('previsioneTaskListModification');
      this.activeModal.close();
    });
  }
}
