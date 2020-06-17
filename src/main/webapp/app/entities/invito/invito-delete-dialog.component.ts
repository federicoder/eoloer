import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IInvito } from 'app/shared/model/invito.model';
import { InvitoService } from './invito.service';

@Component({
  templateUrl: './invito-delete-dialog.component.html',
})
export class InvitoDeleteDialogComponent {
  invito?: IInvito;

  constructor(protected invitoService: InvitoService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.invitoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('invitoListModification');
      this.activeModal.close();
    });
  }
}
