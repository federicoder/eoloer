import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDatiContabili } from 'app/shared/model/dati-contabili.model';
import { DatiContabiliService } from './dati-contabili.service';

@Component({
  templateUrl: './dati-contabili-delete-dialog.component.html',
})
export class DatiContabiliDeleteDialogComponent {
  datiContabili?: IDatiContabili;

  constructor(
    protected datiContabiliService: DatiContabiliService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.datiContabiliService.delete(id).subscribe(() => {
      this.eventManager.broadcast('datiContabiliListModification');
      this.activeModal.close();
    });
  }
}
