import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRuoloOrganizzazione } from 'app/shared/model/ruolo-organizzazione.model';
import { RuoloOrganizzazioneService } from './ruolo-organizzazione.service';

@Component({
  templateUrl: './ruolo-organizzazione-delete-dialog.component.html',
})
export class RuoloOrganizzazioneDeleteDialogComponent {
  ruoloOrganizzazione?: IRuoloOrganizzazione;

  constructor(
    protected ruoloOrganizzazioneService: RuoloOrganizzazioneService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ruoloOrganizzazioneService.delete(id).subscribe(() => {
      this.eventManager.broadcast('ruoloOrganizzazioneListModification');
      this.activeModal.close();
    });
  }
}
