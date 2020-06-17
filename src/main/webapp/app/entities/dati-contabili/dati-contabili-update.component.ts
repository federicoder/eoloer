import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IDatiContabili, DatiContabili } from 'app/shared/model/dati-contabili.model';
import { DatiContabiliService } from './dati-contabili.service';
import { IPersona } from 'app/shared/model/persona.model';
import { PersonaService } from 'app/entities/persona/persona.service';

@Component({
  selector: 'jhi-dati-contabili-update',
  templateUrl: './dati-contabili-update.component.html',
})
export class DatiContabiliUpdateComponent implements OnInit {
  isSaving = false;
  personas: IPersona[] = [];

  editForm = this.fb.group({
    id: [],
    idPersonaRef: [null, [Validators.required]],
    idPersonaRefId: [],
  });

  constructor(
    protected datiContabiliService: DatiContabiliService,
    protected personaService: PersonaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ datiContabili }) => {
      this.updateForm(datiContabili);

      this.personaService.query().subscribe((res: HttpResponse<IPersona[]>) => (this.personas = res.body || []));
    });
  }

  updateForm(datiContabili: IDatiContabili): void {
    this.editForm.patchValue({
      id: datiContabili.id,
      idPersonaRef: datiContabili.idPersonaRef,
      idPersonaRefId: datiContabili.idPersonaRefId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const datiContabili = this.createFromForm();
    if (datiContabili.id !== undefined) {
      this.subscribeToSaveResponse(this.datiContabiliService.update(datiContabili));
    } else {
      this.subscribeToSaveResponse(this.datiContabiliService.create(datiContabili));
    }
  }

  private createFromForm(): IDatiContabili {
    return {
      ...new DatiContabili(),
      id: this.editForm.get(['id'])!.value,
      idPersonaRef: this.editForm.get(['idPersonaRef'])!.value,
      idPersonaRefId: this.editForm.get(['idPersonaRefId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDatiContabili>>): void {
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

  trackById(index: number, item: IPersona): any {
    return item.id;
  }
}
