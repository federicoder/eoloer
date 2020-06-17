import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPrevisioneEvento } from 'app/shared/model/previsione-evento.model';
import { PrevisioneEventoService } from './previsione-evento.service';

@Component({
  templateUrl: './previsione-evento-delete-dialog.component.html',
})
export class PrevisioneEventoDeleteDialogComponent {
  previsioneEvento?: IPrevisioneEvento;

  constructor(
    protected previsioneEventoService: PrevisioneEventoService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.previsioneEventoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('previsioneEventoListModification');
      this.activeModal.close();
    });
  }
}
