import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITemplatePratica } from 'app/shared/model/template-pratica.model';
import { TemplatePraticaService } from './template-pratica.service';

@Component({
  templateUrl: './template-pratica-delete-dialog.component.html',
})
export class TemplatePraticaDeleteDialogComponent {
  templatePratica?: ITemplatePratica;

  constructor(
    protected templatePraticaService: TemplatePraticaService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.templatePraticaService.delete(id).subscribe(() => {
      this.eventManager.broadcast('templatePraticaListModification');
      this.activeModal.close();
    });
  }
}
