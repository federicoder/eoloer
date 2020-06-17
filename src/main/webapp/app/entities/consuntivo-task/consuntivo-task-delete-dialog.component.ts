import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IConsuntivoTask } from 'app/shared/model/consuntivo-task.model';
import { ConsuntivoTaskService } from './consuntivo-task.service';

@Component({
  templateUrl: './consuntivo-task-delete-dialog.component.html',
})
export class ConsuntivoTaskDeleteDialogComponent {
  consuntivoTask?: IConsuntivoTask;

  constructor(
    protected consuntivoTaskService: ConsuntivoTaskService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.consuntivoTaskService.delete(id).subscribe(() => {
      this.eventManager.broadcast('consuntivoTaskListModification');
      this.activeModal.close();
    });
  }
}
