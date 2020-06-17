import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IPersonaFisica, PersonaFisica } from 'app/shared/model/persona-fisica.model';
import { PersonaFisicaService } from './persona-fisica.service';
import { IPersona } from 'app/shared/model/persona.model';
import { PersonaService } from 'app/entities/persona/persona.service';

@Component({
  selector: 'jhi-persona-fisica-update',
  templateUrl: './persona-fisica-update.component.html',
})
export class PersonaFisicaUpdateComponent implements OnInit {
  isSaving = false;
  idpersonas: IPersona[] = [];

  editForm = this.fb.group({
    id: [],
    idPersonaFisica: [null, [Validators.required]],
    idPersona: [null, [Validators.required]],
    idRuoloPersona: [],
    titolo: [],
    cognome: [],
    nome: [],
    dataDiNascita: [],
    luogoDiNascita: [],
    professione: [],
    idPersonaId: [],
  });

  constructor(
    protected personaFisicaService: PersonaFisicaService,
    protected personaService: PersonaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ personaFisica }) => {
      this.updateForm(personaFisica);

      this.personaService
        .query({ filter: 'id-is-null' })
        .pipe(
          map((res: HttpResponse<IPersona[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IPersona[]) => {
          if (!personaFisica.idPersonaId) {
            this.idpersonas = resBody;
          } else {
            this.personaService
              .find(personaFisica.idPersonaId)
              .pipe(
                map((subRes: HttpResponse<IPersona>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IPersona[]) => (this.idpersonas = concatRes));
          }
        });
    });
  }

  updateForm(personaFisica: IPersonaFisica): void {
    this.editForm.patchValue({
      id: personaFisica.id,
      idPersonaFisica: personaFisica.idPersonaFisica,
      idPersona: personaFisica.idPersona,
      idRuoloPersona: personaFisica.idRuoloPersona,
      titolo: personaFisica.titolo,
      cognome: personaFisica.cognome,
      nome: personaFisica.nome,
      dataDiNascita: personaFisica.dataDiNascita,
      luogoDiNascita: personaFisica.luogoDiNascita,
      professione: personaFisica.professione,
      idPersonaId: personaFisica.idPersonaId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const personaFisica = this.createFromForm();
    if (personaFisica.id !== undefined) {
      this.subscribeToSaveResponse(this.personaFisicaService.update(personaFisica));
    } else {
      this.subscribeToSaveResponse(this.personaFisicaService.create(personaFisica));
    }
  }

  private createFromForm(): IPersonaFisica {
    return {
      ...new PersonaFisica(),
      id: this.editForm.get(['id'])!.value,
      idPersonaFisica: this.editForm.get(['idPersonaFisica'])!.value,
      idPersona: this.editForm.get(['idPersona'])!.value,
      idRuoloPersona: this.editForm.get(['idRuoloPersona'])!.value,
      titolo: this.editForm.get(['titolo'])!.value,
      cognome: this.editForm.get(['cognome'])!.value,
      nome: this.editForm.get(['nome'])!.value,
      dataDiNascita: this.editForm.get(['dataDiNascita'])!.value,
      luogoDiNascita: this.editForm.get(['luogoDiNascita'])!.value,
      professione: this.editForm.get(['professione'])!.value,
      idPersonaId: this.editForm.get(['idPersonaId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPersonaFisica>>): void {
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
