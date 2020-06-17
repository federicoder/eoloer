import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILineaOrdine } from 'app/shared/model/linea-ordine.model';
import { LineaOrdineService } from './linea-ordine.service';

@Component({
  templateUrl: './linea-ordine-delete-dialog.component.html',
})
export class LineaOrdineDeleteDialogComponent {
  lineaOrdine?: ILineaOrdine;

  constructor(
    protected lineaOrdineService: LineaOrdineService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.lineaOrdineService.delete(id).subscribe(() => {
      this.eventManager.broadcast('lineaOrdineListModification');
      this.activeModal.close();
    });
  }
}
