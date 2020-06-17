import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPersonaFisica } from 'app/shared/model/persona-fisica.model';
import { PersonaFisicaService } from './persona-fisica.service';

@Component({
  templateUrl: './persona-fisica-delete-dialog.component.html',
})
export class PersonaFisicaDeleteDialogComponent {
  personaFisica?: IPersonaFisica;

  constructor(
    protected personaFisicaService: PersonaFisicaService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.personaFisicaService.delete(id).subscribe(() => {
      this.eventManager.broadcast('personaFisicaListModification');
      this.activeModal.close();
    });
  }
}
