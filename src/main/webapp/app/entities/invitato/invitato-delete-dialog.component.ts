import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IInvitato } from 'app/shared/model/invitato.model';
import { InvitatoService } from './invitato.service';

@Component({
  templateUrl: './invitato-delete-dialog.component.html',
})
export class InvitatoDeleteDialogComponent {
  invitato?: IInvitato;

  constructor(protected invitatoService: InvitatoService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.invitatoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('invitatoListModification');
      this.activeModal.close();
    });
  }
}
