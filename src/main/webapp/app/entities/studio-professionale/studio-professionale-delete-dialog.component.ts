import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IStudioProfessionale } from 'app/shared/model/studio-professionale.model';
import { StudioProfessionaleService } from './studio-professionale.service';

@Component({
  templateUrl: './studio-professionale-delete-dialog.component.html',
})
export class StudioProfessionaleDeleteDialogComponent {
  studioProfessionale?: IStudioProfessionale;

  constructor(
    protected studioProfessionaleService: StudioProfessionaleService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.studioProfessionaleService.delete(id).subscribe(() => {
      this.eventManager.broadcast('studioProfessionaleListModification');
      this.activeModal.close();
    });
  }
}
