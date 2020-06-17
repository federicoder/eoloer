import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IIndirizzoPersona } from 'app/shared/model/indirizzo-persona.model';
import { IndirizzoPersonaService } from './indirizzo-persona.service';

@Component({
  templateUrl: './indirizzo-persona-delete-dialog.component.html',
})
export class IndirizzoPersonaDeleteDialogComponent {
  indirizzoPersona?: IIndirizzoPersona;

  constructor(
    protected indirizzoPersonaService: IndirizzoPersonaService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.indirizzoPersonaService.delete(id).subscribe(() => {
      this.eventManager.broadcast('indirizzoPersonaListModification');
      this.activeModal.close();
    });
  }
}
