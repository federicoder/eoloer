import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITelefonoPersona } from 'app/shared/model/telefono-persona.model';
import { TelefonoPersonaService } from './telefono-persona.service';

@Component({
  templateUrl: './telefono-persona-delete-dialog.component.html',
})
export class TelefonoPersonaDeleteDialogComponent {
  telefonoPersona?: ITelefonoPersona;

  constructor(
    protected telefonoPersonaService: TelefonoPersonaService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.telefonoPersonaService.delete(id).subscribe(() => {
      this.eventManager.broadcast('telefonoPersonaListModification');
      this.activeModal.close();
    });
  }
}
