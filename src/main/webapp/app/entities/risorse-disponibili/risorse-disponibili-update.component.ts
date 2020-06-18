import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IRisorseDisponibili, RisorseDisponibili } from 'app/shared/model/risorse-disponibili.model';
import { RisorseDisponibiliService } from './risorse-disponibili.service';
import { IStudioProfessionale } from 'app/shared/model/studio-professionale.model';
import { StudioProfessionaleService } from 'app/entities/studio-professionale/studio-professionale.service';

@Component({
  selector: 'jhi-risorse-disponibili-update',
  templateUrl: './risorse-disponibili-update.component.html',
})
export class RisorseDisponibiliUpdateComponent implements OnInit {
  isSaving = false;
  studioprofessionales: IStudioProfessionale[] = [];

  editForm = this.fb.group({
    id: [],
    idStudioProfessionaleRef: [null, [Validators.required]],
    dataAttivazioneLicenza: [],
    nrLicenza: [],
    storageTotale: [],
    idStudioProfessionaleId: [],
  });

  constructor(
    protected risorseDisponibiliService: RisorseDisponibiliService,
    protected studioProfessionaleService: StudioProfessionaleService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ risorseDisponibili }) => {
      this.updateForm(risorseDisponibili);

      this.studioProfessionaleService
        .query()
        .subscribe((res: HttpResponse<IStudioProfessionale[]>) => (this.studioprofessionales = res.body || []));
    });
  }

  updateForm(risorseDisponibili: IRisorseDisponibili): void {
    this.editForm.patchValue({
      id: risorseDisponibili.id,
      idStudioProfessionaleRef: risorseDisponibili.idStudioProfessionaleRef,
      dataAttivazioneLicenza: risorseDisponibili.dataAttivazioneLicenza,
      nrLicenza: risorseDisponibili.nrLicenza,
      storageTotale: risorseDisponibili.storageTotale,
      idStudioProfessionaleId: risorseDisponibili.idStudioProfessionaleId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const risorseDisponibili = this.createFromForm();
    if (risorseDisponibili.id !== undefined) {
      this.subscribeToSaveResponse(this.risorseDisponibiliService.update(risorseDisponibili));
    } else {
      this.subscribeToSaveResponse(this.risorseDisponibiliService.create(risorseDisponibili));
    }
  }

  private createFromForm(): IRisorseDisponibili {
    return {
      ...new RisorseDisponibili(),
      id: this.editForm.get(['id'])!.value,
      idStudioProfessionaleRef: this.editForm.get(['idStudioProfessionaleRef'])!.value,
      dataAttivazioneLicenza: this.editForm.get(['dataAttivazioneLicenza'])!.value,
      nrLicenza: this.editForm.get(['nrLicenza'])!.value,
      storageTotale: this.editForm.get(['storageTotale'])!.value,
      idStudioProfessionaleId: this.editForm.get(['idStudioProfessionaleId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRisorseDisponibili>>): void {
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
