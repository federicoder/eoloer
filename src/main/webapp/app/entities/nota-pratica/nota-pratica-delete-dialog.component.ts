import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INotaPratica } from 'app/shared/model/nota-pratica.model';
import { NotaPraticaService } from './nota-pratica.service';

@Component({
  templateUrl: './nota-pratica-delete-dialog.component.html',
})
export class NotaPraticaDeleteDialogComponent {
  notaPratica?: INotaPratica;

  constructor(
    protected notaPraticaService: NotaPraticaService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.notaPraticaService.delete(id).subscribe(() => {
      this.eventManager.broadcast('notaPraticaListModification');
      this.activeModal.close();
    });
  }
}
