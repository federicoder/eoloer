import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IInvitoPratica } from 'app/shared/model/invito-pratica.model';
import { InvitoPraticaService } from './invito-pratica.service';

@Component({
  templateUrl: './invito-pratica-delete-dialog.component.html',
})
export class InvitoPraticaDeleteDialogComponent {
  invitoPratica?: IInvitoPratica;

  constructor(
    protected invitoPraticaService: InvitoPraticaService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.invitoPraticaService.delete(id).subscribe(() => {
      this.eventManager.broadcast('invitoPraticaListModification');
      this.activeModal.close();
    });
  }
}
