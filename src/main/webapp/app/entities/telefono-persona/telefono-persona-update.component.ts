import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITelefonoPersona, TelefonoPersona } from 'app/shared/model/telefono-persona.model';
import { TelefonoPersonaService } from './telefono-persona.service';
import { IPersona } from 'app/shared/model/persona.model';
import { PersonaService } from 'app/entities/persona/persona.service';

@Component({
  selector: 'jhi-telefono-persona-update',
  templateUrl: './telefono-persona-update.component.html',
})
export class TelefonoPersonaUpdateComponent implements OnInit {
  isSaving = false;
  personas: IPersona[] = [];

  editForm = this.fb.group({
    id: [],
    idPersonaRef: [null, [Validators.required]],
    etichetta: [],
    valore: [],
    idPersonaRefId: [],
  });

  constructor(
    protected telefonoPersonaService: TelefonoPersonaService,
    protected personaService: PersonaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ telefonoPersona }) => {
      this.updateForm(telefonoPersona);

      this.personaService.query().subscribe((res: HttpResponse<IPersona[]>) => (this.personas = res.body || []));
    });
  }

  updateForm(telefonoPersona: ITelefonoPersona): void {
    this.editForm.patchValue({
      id: telefonoPersona.id,
      idPersonaRef: telefonoPersona.idPersonaRef,
      etichetta: telefonoPersona.etichetta,
      valore: telefonoPersona.valore,
      idPersonaRefId: telefonoPersona.idPersonaRefId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const telefonoPersona = this.createFromForm();
    if (telefonoPersona.id !== undefined) {
      this.subscribeToSaveResponse(this.telefonoPersonaService.update(telefonoPersona));
    } else {
      this.subscribeToSaveResponse(this.telefonoPersonaService.create(telefonoPersona));
    }
  }

  private createFromForm(): ITelefonoPersona {
    return {
      ...new TelefonoPersona(),
      id: this.editForm.get(['id'])!.value,
      idPersonaRef: this.editForm.get(['idPersonaRef'])!.value,
      etichetta: this.editForm.get(['etichetta'])!.value,
      valore: this.editForm.get(['valore'])!.value,
      idPersonaRefId: this.editForm.get(['idPersonaRefId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITelefonoPersona>>): void {
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
