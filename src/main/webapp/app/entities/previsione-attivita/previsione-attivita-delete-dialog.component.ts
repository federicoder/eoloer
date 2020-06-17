import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPrevisioneAttivita } from 'app/shared/model/previsione-attivita.model';
import { PrevisioneAttivitaService } from './previsione-attivita.service';

@Component({
  templateUrl: './previsione-attivita-delete-dialog.component.html',
})
export class PrevisioneAttivitaDeleteDialogComponent {
  previsioneAttivita?: IPrevisioneAttivita;

  constructor(
    protected previsioneAttivitaService: PrevisioneAttivitaService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.previsioneAttivitaService.delete(id).subscribe(() => {
      this.eventManager.broadcast('previsioneAttivitaListModification');
      this.activeModal.close();
    });
  }
}
