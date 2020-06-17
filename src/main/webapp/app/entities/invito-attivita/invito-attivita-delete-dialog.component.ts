import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IInvitoAttivita } from 'app/shared/model/invito-attivita.model';
import { InvitoAttivitaService } from './invito-attivita.service';

@Component({
  templateUrl: './invito-attivita-delete-dialog.component.html',
})
export class InvitoAttivitaDeleteDialogComponent {
  invitoAttivita?: IInvitoAttivita;

  constructor(
    protected invitoAttivitaService: InvitoAttivitaService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.invitoAttivitaService.delete(id).subscribe(() => {
      this.eventManager.broadcast('invitoAttivitaListModification');
      this.activeModal.close();
    });
  }
}
