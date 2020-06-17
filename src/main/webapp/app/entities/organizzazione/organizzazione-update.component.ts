import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IOrganizzazione, Organizzazione } from 'app/shared/model/organizzazione.model';
import { OrganizzazioneService } from './organizzazione.service';
import { IPersona } from 'app/shared/model/persona.model';
import { PersonaService } from 'app/entities/persona/persona.service';

@Component({
  selector: 'jhi-organizzazione-update',
  templateUrl: './organizzazione-update.component.html',
})
export class OrganizzazioneUpdateComponent implements OnInit {
  isSaving = false;
  idpersonarefs: IPersona[] = [];

  editForm = this.fb.group({
    id: [],
    idPersonaRef: [null, [Validators.required]],
    nome: [],
    tipo: [],
    idPersonaRefId: [],
  });

  constructor(
    protected organizzazioneService: OrganizzazioneService,
    protected personaService: PersonaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ organizzazione }) => {
      this.updateForm(organizzazione);

      this.personaService
        .query({ filter: 'id-is-null' })
        .pipe(
          map((res: HttpResponse<IPersona[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IPersona[]) => {
          if (!organizzazione.idPersonaRefId) {
            this.idpersonarefs = resBody;
          } else {
            this.personaService
              .find(organizzazione.idPersonaRefId)
              .pipe(
                map((subRes: HttpResponse<IPersona>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IPersona[]) => (this.idpersonarefs = concatRes));
          }
        });
    });
  }

  updateForm(organizzazione: IOrganizzazione): void {
    this.editForm.patchValue({
      id: organizzazione.id,
      idPersonaRef: organizzazione.idPersonaRef,
      nome: organizzazione.nome,
      tipo: organizzazione.tipo,
      idPersonaRefId: organizzazione.idPersonaRefId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const organizzazione = this.createFromForm();
    if (organizzazione.id !== undefined) {
      this.subscribeToSaveResponse(this.organizzazioneService.update(organizzazione));
    } else {
      this.subscribeToSaveResponse(this.organizzazioneService.create(organizzazione));
    }
  }

  private createFromForm(): IOrganizzazione {
    return {
      ...new Organizzazione(),
      id: this.editForm.get(['id'])!.value,
      idPersonaRef: this.editForm.get(['idPersonaRef'])!.value,
      nome: this.editForm.get(['nome'])!.value,
      tipo: this.editForm.get(['tipo'])!.value,
      idPersonaRefId: this.editForm.get(['idPersonaRefId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOrganizzazione>>): void {
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
