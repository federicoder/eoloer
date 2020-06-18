import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IIndirizzoPersona, IndirizzoPersona } from 'app/shared/model/indirizzo-persona.model';
import { IndirizzoPersonaService } from './indirizzo-persona.service';
import { IPersona } from 'app/shared/model/persona.model';
import { PersonaService } from 'app/entities/persona/persona.service';

@Component({
  selector: 'jhi-indirizzo-persona-update',
  templateUrl: './indirizzo-persona-update.component.html',
})
export class IndirizzoPersonaUpdateComponent implements OnInit {
  isSaving = false;
  idpersonas: IPersona[] = [];

  editForm = this.fb.group({
    id: [],
    idPersonaRef: [null, [Validators.required]],
    indirizzo: [],
    comune: [],
    cap: [],
    provincia: [],
    regione: [],
    nazione: [],
    idPersonaId: [],
  });

  constructor(
    protected indirizzoPersonaService: IndirizzoPersonaService,
    protected personaService: PersonaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ indirizzoPersona }) => {
      this.updateForm(indirizzoPersona);

      this.personaService
        .query({ filter: 'indirizzopersona-is-null' })
        .pipe(
          map((res: HttpResponse<IPersona[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IPersona[]) => {
          if (!indirizzoPersona.idPersonaId) {
            this.idpersonas = resBody;
          } else {
            this.personaService
              .find(indirizzoPersona.idPersonaId)
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

  updateForm(indirizzoPersona: IIndirizzoPersona): void {
    this.editForm.patchValue({
      id: indirizzoPersona.id,
      idPersonaRef: indirizzoPersona.idPersonaRef,
      indirizzo: indirizzoPersona.indirizzo,
      comune: indirizzoPersona.comune,
      cap: indirizzoPersona.cap,
      provincia: indirizzoPersona.provincia,
      regione: indirizzoPersona.regione,
      nazione: indirizzoPersona.nazione,
      idPersonaId: indirizzoPersona.idPersonaId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const indirizzoPersona = this.createFromForm();
    if (indirizzoPersona.id !== undefined) {
      this.subscribeToSaveResponse(this.indirizzoPersonaService.update(indirizzoPersona));
    } else {
      this.subscribeToSaveResponse(this.indirizzoPersonaService.create(indirizzoPersona));
    }
  }

  private createFromForm(): IIndirizzoPersona {
    return {
      ...new IndirizzoPersona(),
      id: this.editForm.get(['id'])!.value,
      idPersonaRef: this.editForm.get(['idPersonaRef'])!.value,
      indirizzo: this.editForm.get(['indirizzo'])!.value,
      comune: this.editForm.get(['comune'])!.value,
      cap: this.editForm.get(['cap'])!.value,
      provincia: this.editForm.get(['provincia'])!.value,
      regione: this.editForm.get(['regione'])!.value,
      nazione: this.editForm.get(['nazione'])!.value,
      idPersonaId: this.editForm.get(['idPersonaId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IIndirizzoPersona>>): void {
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
