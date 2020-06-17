import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IUserPersona } from 'app/shared/model/user-persona.model';
import { UserPersonaService } from './user-persona.service';

@Component({
  templateUrl: './user-persona-delete-dialog.component.html',
})
export class UserPersonaDeleteDialogComponent {
  userPersona?: IUserPersona;

  constructor(
    protected userPersonaService: UserPersonaService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.userPersonaService.delete(id).subscribe(() => {
      this.eventManager.broadcast('userPersonaListModification');
      this.activeModal.close();
    });
  }
}
