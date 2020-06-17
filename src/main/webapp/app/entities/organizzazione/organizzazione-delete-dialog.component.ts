import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IOrganizzazione } from 'app/shared/model/organizzazione.model';
import { OrganizzazioneService } from './organizzazione.service';

@Component({
  templateUrl: './organizzazione-delete-dialog.component.html',
})
export class OrganizzazioneDeleteDialogComponent {
  organizzazione?: IOrganizzazione;

  constructor(
    protected organizzazioneService: OrganizzazioneService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.organizzazioneService.delete(id).subscribe(() => {
      this.eventManager.broadcast('organizzazioneListModification');
      this.activeModal.close();
    });
  }
}
