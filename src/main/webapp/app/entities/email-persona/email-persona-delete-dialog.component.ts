import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEmailPersona } from 'app/shared/model/email-persona.model';
import { EmailPersonaService } from './email-persona.service';

@Component({
  templateUrl: './email-persona-delete-dialog.component.html',
})
export class EmailPersonaDeleteDialogComponent {
  emailPersona?: IEmailPersona;

  constructor(
    protected emailPersonaService: EmailPersonaService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.emailPersonaService.delete(id).subscribe(() => {
      this.eventManager.broadcast('emailPersonaListModification');
      this.activeModal.close();
    });
  }
}
