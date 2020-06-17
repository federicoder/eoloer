import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IOrdine, Ordine } from 'app/shared/model/ordine.model';
import { OrdineService } from './ordine.service';
import { IStudioProfessionale } from 'app/shared/model/studio-professionale.model';
import { StudioProfessionaleService } from 'app/entities/studio-professionale/studio-professionale.service';

@Component({
  selector: 'jhi-ordine-update',
  templateUrl: './ordine-update.component.html',
})
export class OrdineUpdateComponent implements OnInit {
  isSaving = false;
  studioprofessionales: IStudioProfessionale[] = [];

  editForm = this.fb.group({
    id: [],
    idStudioProfessionaleRef: [null, [Validators.required]],
    statoOrdine: [],
    totImponibile: [],
    totIva: [],
    totOrdine: [],
    idStudioProfessionaleRefId: [],
  });

  constructor(
    protected ordineService: OrdineService,
    protected studioProfessionaleService: StudioProfessionaleService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ordine }) => {
      this.updateForm(ordine);

      this.studioProfessionaleService
        .query()
        .subscribe((res: HttpResponse<IStudioProfessionale[]>) => (this.studioprofessionales = res.body || []));
    });
  }

  updateForm(ordine: IOrdine): void {
    this.editForm.patchValue({
      id: ordine.id,
      idStudioProfessionaleRef: ordine.idStudioProfessionaleRef,
      statoOrdine: ordine.statoOrdine,
      totImponibile: ordine.totImponibile,
      totIva: ordine.totIva,
      totOrdine: ordine.totOrdine,
      idStudioProfessionaleRefId: ordine.idStudioProfessionaleRefId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const ordine = this.createFromForm();
    if (ordine.id !== undefined) {
      this.subscribeToSaveResponse(this.ordineService.update(ordine));
    } else {
      this.subscribeToSaveResponse(this.ordineService.create(ordine));
    }
  }

  private createFromForm(): IOrdine {
    return {
      ...new Ordine(),
      id: this.editForm.get(['id'])!.value,
      idStudioProfessionaleRef: this.editForm.get(['idStudioProfessionaleRef'])!.value,
      statoOrdine: this.editForm.get(['statoOrdine'])!.value,
      totImponibile: this.editForm.get(['totImponibile'])!.value,
      totIva: this.editForm.get(['totIva'])!.value,
      totOrdine: this.editForm.get(['totOrdine'])!.value,
      idStudioProfessionaleRefId: this.editForm.get(['idStudioProfessionaleRefId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrdine>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IStudioProfessionale): any {
    return item.id;
  }
}
