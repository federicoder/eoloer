import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IUserPersona, UserPersona } from 'app/shared/model/user-persona.model';
import { UserPersonaService } from './user-persona.service';
import { IPersonaFisica } from 'app/shared/model/persona-fisica.model';
import { PersonaFisicaService } from 'app/entities/persona-fisica/persona-fisica.service';

@Component({
  selector: 'jhi-user-persona-update',
  templateUrl: './user-persona-update.component.html',
})
export class UserPersonaUpdateComponent implements OnInit {
  isSaving = false;
  personafisicas: IPersonaFisica[] = [];

  editForm = this.fb.group({
    id: [],
    idPersonaRef: [],
    nomeUser: [],
    idPersonaFisicaId: [],
  });

  constructor(
    protected userPersonaService: UserPersonaService,
    protected personaFisicaService: PersonaFisicaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ userPersona }) => {
      this.updateForm(userPersona);

      this.personaFisicaService.query().subscribe((res: HttpResponse<IPersonaFisica[]>) => (this.personafisicas = res.body || []));
    });
  }

  updateForm(userPersona: IUserPersona): void {
    this.editForm.patchValue({
      id: userPersona.id,
      idPersonaRef: userPersona.idPersonaRef,
      nomeUser: userPersona.nomeUser,
      idPersonaFisicaId: userPersona.idPersonaFisicaId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const userPersona = this.createFromForm();
    if (userPersona.id !== undefined) {
      this.subscribeToSaveResponse(this.userPersonaService.update(userPersona));
    } else {
      this.subscribeToSaveResponse(this.userPersonaService.create(userPersona));
    }
  }

  private createFromForm(): IUserPersona {
    return {
      ...new UserPersona(),
      id: this.editForm.get(['id'])!.value,
      idPersonaRef: this.editForm.get(['idPersonaRef'])!.value,
      nomeUser: this.editForm.get(['nomeUser'])!.value,
      idPersonaFisicaId: this.editForm.get(['idPersonaFisicaId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUserPersona>>): void {
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

  trackById(index: number, item: IPersonaFisica): any {
    return item.id;
  }
}
