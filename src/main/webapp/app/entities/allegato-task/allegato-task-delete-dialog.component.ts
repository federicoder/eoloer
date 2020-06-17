import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAllegatoTask } from 'app/shared/model/allegato-task.model';
import { AllegatoTaskService } from './allegato-task.service';

@Component({
  templateUrl: './allegato-task-delete-dialog.component.html',
})
export class AllegatoTaskDeleteDialogComponent {
  allegatoTask?: IAllegatoTask;

  constructor(
    protected allegatoTaskService: AllegatoTaskService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.allegatoTaskService.delete(id).subscribe(() => {
      this.eventManager.broadcast('allegatoTaskListModification');
      this.activeModal.close();
    });
  }
}
