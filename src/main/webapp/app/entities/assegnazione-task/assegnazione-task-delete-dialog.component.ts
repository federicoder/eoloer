import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAssegnazioneTask } from 'app/shared/model/assegnazione-task.model';
import { AssegnazioneTaskService } from './assegnazione-task.service';

@Component({
  templateUrl: './assegnazione-task-delete-dialog.component.html',
})
export class AssegnazioneTaskDeleteDialogComponent {
  assegnazioneTask?: IAssegnazioneTask;

  constructor(
    protected assegnazioneTaskService: AssegnazioneTaskService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.assegnazioneTaskService.delete(id).subscribe(() => {
      this.eventManager.broadcast('assegnazioneTaskListModification');
      this.activeModal.close();
    });
  }
}
