import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICondivisionePratica } from 'app/shared/model/condivisione-pratica.model';
import { CondivisionePraticaService } from './condivisione-pratica.service';

@Component({
  templateUrl: './condivisione-pratica-delete-dialog.component.html',
})
export class CondivisionePraticaDeleteDialogComponent {
  condivisionePratica?: ICondivisionePratica;

  constructor(
    protected condivisionePraticaService: CondivisionePraticaService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.condivisionePraticaService.delete(id).subscribe(() => {
      this.eventManager.broadcast('condivisionePraticaListModification');
      this.activeModal.close();
    });
  }
}
