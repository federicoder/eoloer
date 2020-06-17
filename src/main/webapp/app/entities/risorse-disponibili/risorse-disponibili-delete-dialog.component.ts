import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRisorseDisponibili } from 'app/shared/model/risorse-disponibili.model';
import { RisorseDisponibiliService } from './risorse-disponibili.service';

@Component({
  templateUrl: './risorse-disponibili-delete-dialog.component.html',
})
export class RisorseDisponibiliDeleteDialogComponent {
  risorseDisponibili?: IRisorseDisponibili;

  constructor(
    protected risorseDisponibiliService: RisorseDisponibiliService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.risorseDisponibiliService.delete(id).subscribe(() => {
      this.eventManager.broadcast('risorseDisponibiliListModification');
      this.activeModal.close();
    });
  }
}
