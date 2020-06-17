import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITagPersona } from 'app/shared/model/tag-persona.model';
import { TagPersonaService } from './tag-persona.service';

@Component({
  templateUrl: './tag-persona-delete-dialog.component.html',
})
export class TagPersonaDeleteDialogComponent {
  tagPersona?: ITagPersona;

  constructor(
    protected tagPersonaService: TagPersonaService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tagPersonaService.delete(id).subscribe(() => {
      this.eventManager.broadcast('tagPersonaListModification');
      this.activeModal.close();
    });
  }
}
