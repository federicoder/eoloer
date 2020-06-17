import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INotaTask } from 'app/shared/model/nota-task.model';
import { NotaTaskService } from './nota-task.service';

@Component({
  templateUrl: './nota-task-delete-dialog.component.html',
})
export class NotaTaskDeleteDialogComponent {
  notaTask?: INotaTask;

  constructor(protected notaTaskService: NotaTaskService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.notaTaskService.delete(id).subscribe(() => {
      this.eventManager.broadcast('notaTaskListModification');
      this.activeModal.close();
    });
  }
}
