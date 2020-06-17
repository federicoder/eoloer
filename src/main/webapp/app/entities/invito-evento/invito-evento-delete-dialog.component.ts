import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IInvitoEvento } from 'app/shared/model/invito-evento.model';
import { InvitoEventoService } from './invito-evento.service';

@Component({
  templateUrl: './invito-evento-delete-dialog.component.html',
})
export class InvitoEventoDeleteDialogComponent {
  invitoEvento?: IInvitoEvento;

  constructor(
    protected invitoEventoService: InvitoEventoService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.invitoEventoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('invitoEventoListModification');
      this.activeModal.close();
    });
  }
}
