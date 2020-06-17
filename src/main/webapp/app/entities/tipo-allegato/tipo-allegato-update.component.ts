import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITipoAllegato, TipoAllegato } from 'app/shared/model/tipo-allegato.model';
import { TipoAllegatoService } from './tipo-allegato.service';

@Component({
  selector: 'jhi-tipo-allegato-update',
  templateUrl: './tipo-allegato-update.component.html',
})
export class TipoAllegatoUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nome: [],
    formatiAmmessi: [],
    maxDimensioneAmmessa: [],
    version: [],
  });

  constructor(protected tipoAllegatoService: TipoAllegatoService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tipoAllegato }) => {
      this.updateForm(tipoAllegato);
    });
  }

  updateForm(tipoAllegato: ITipoAllegato): void {
    this.editForm.patchValue({
      id: tipoAllegato.id,
      nome: tipoAllegato.nome,
      formatiAmmessi: tipoAllegato.formatiAmmessi,
      maxDimensioneAmmessa: tipoAllegato.maxDimensioneAmmessa,
      version: tipoAllegato.version,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tipoAllegato = this.createFromForm();
    if (tipoAllegato.id !== undefined) {
      this.subscribeToSaveResponse(this.tipoAllegatoService.update(tipoAllegato));
    } else {
      this.subscribeToSaveResponse(this.tipoAllegatoService.create(tipoAllegato));
    }
  }

  private createFromForm(): ITipoAllegato {
    return {
      ...new TipoAllegato(),
      id: this.editForm.get(['id'])!.value,
      nome: this.editForm.get(['nome'])!.value,
      formatiAmmessi: this.editForm.get(['formatiAmmessi'])!.value,
      maxDimensioneAmmessa: this.editForm.get(['maxDimensioneAmmessa'])!.value,
      version: this.editForm.get(['version'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITipoAllegato>>): void {
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
}
