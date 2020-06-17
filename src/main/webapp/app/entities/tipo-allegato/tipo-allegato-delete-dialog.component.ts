import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITipoAllegato } from 'app/shared/model/tipo-allegato.model';
import { TipoAllegatoService } from './tipo-allegato.service';

@Component({
  templateUrl: './tipo-allegato-delete-dialog.component.html',
})
export class TipoAllegatoDeleteDialogComponent {
  tipoAllegato?: ITipoAllegato;

  constructor(
    protected tipoAllegatoService: TipoAllegatoService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tipoAllegatoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('tipoAllegatoListModification');
      this.activeModal.close();
    });
  }
}
