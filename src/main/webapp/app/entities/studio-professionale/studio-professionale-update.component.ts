import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IStudioProfessionale, StudioProfessionale } from 'app/shared/model/studio-professionale.model';
import { StudioProfessionaleService } from './studio-professionale.service';
import { IPersona } from 'app/shared/model/persona.model';
import { PersonaService } from 'app/entities/persona/persona.service';

@Component({
  selector: 'jhi-studio-professionale-update',
  templateUrl: './studio-professionale-update.component.html',
})
export class StudioProfessionaleUpdateComponent implements OnInit {
  isSaving = false;
  personas: IPersona[] = [];

  editForm = this.fb.group({
    id: [],
    idUserAmministratore: [null, [Validators.required]],
    personaId: [],
  });

  constructor(
    protected studioProfessionaleService: StudioProfessionaleService,
    protected personaService: PersonaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ studioProfessionale }) => {
      this.updateForm(studioProfessionale);

      this.personaService.query().subscribe((res: HttpResponse<IPersona[]>) => (this.personas = res.body || []));
    });
  }

  updateForm(studioProfessionale: IStudioProfessionale): void {
    this.editForm.patchValue({
      id: studioProfessionale.id,
      idUserAmministratore: studioProfessionale.idUserAmministratore,
      personaId: studioProfessionale.personaId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const studioProfessionale = this.createFromForm();
    if (studioProfessionale.id !== undefined) {
      this.subscribeToSaveResponse(this.studioProfessionaleService.update(studioProfessionale));
    } else {
      this.subscribeToSaveResponse(this.studioProfessionaleService.create(studioProfessionale));
    }
  }

  private createFromForm(): IStudioProfessionale {
    return {
      ...new StudioProfessionale(),
      id: this.editForm.get(['id'])!.value,
      idUserAmministratore: this.editForm.get(['idUserAmministratore'])!.value,
      personaId: this.editForm.get(['personaId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IStudioProfessionale>>): void {
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
