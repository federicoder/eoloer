import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INotePersona } from 'app/shared/model/note-persona.model';
import { NotePersonaService } from './note-persona.service';

@Component({
  templateUrl: './note-persona-delete-dialog.component.html',
})
export class NotePersonaDeleteDialogComponent {
  notePersona?: INotePersona;

  constructor(
    protected notePersonaService: NotePersonaService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.notePersonaService.delete(id).subscribe(() => {
      this.eventManager.broadcast('notePersonaListModification');
      this.activeModal.close();
    });
  }
}
