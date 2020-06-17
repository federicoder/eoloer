import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRappresentanzaPratica } from 'app/shared/model/rappresentanza-pratica.model';
import { RappresentanzaPraticaService } from './rappresentanza-pratica.service';

@Component({
  templateUrl: './rappresentanza-pratica-delete-dialog.component.html',
})
export class RappresentanzaPraticaDeleteDialogComponent {
  rappresentanzaPratica?: IRappresentanzaPratica;

  constructor(
    protected rappresentanzaPraticaService: RappresentanzaPraticaService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.rappresentanzaPraticaService.delete(id).subscribe(() => {
      this.eventManager.broadcast('rappresentanzaPraticaListModification');
      this.activeModal.close();
    });
  }
}
