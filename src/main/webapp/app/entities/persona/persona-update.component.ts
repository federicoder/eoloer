import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IPersona, Persona } from 'app/shared/model/persona.model';
import { PersonaService } from './persona.service';
import { IIndirizzoPersona } from 'app/shared/model/indirizzo-persona.model';
import { IndirizzoPersonaService } from 'app/entities/indirizzo-persona/indirizzo-persona.service';

@Component({
  selector: 'jhi-persona-update',
  templateUrl: './persona-update.component.html',
})
export class PersonaUpdateComponent implements OnInit {
  isSaving = false;
  ids: IIndirizzoPersona[] = [];

  editForm = this.fb.group({
    id: [],
    idStudio: [null, [Validators.max(8)]],
    codiceFiscale: [],
    areaDiInteresse: [],
    titolo: [],
    cognome: [],
    nome: [],
    dataDiNascita: [],
    luogoDiNascita: [],
    professione: [],
    tipo: [],
    discriminator: [],
    idRuoloPersona: [],
    tipoRuoloUtente: [],
    idId: [],
  });

  constructor(
    protected personaService: PersonaService,
    protected indirizzoPersonaService: IndirizzoPersonaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ persona }) => {
      this.updateForm(persona);

      this.indirizzoPersonaService
        .query({ filter: 'idpersona-is-null' })
        .pipe(
          map((res: HttpResponse<IIndirizzoPersona[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IIndirizzoPersona[]) => {
          if (!persona.idId) {
            this.ids = resBody;
          } else {
            this.indirizzoPersonaService
              .find(persona.idId)
              .pipe(
                map((subRes: HttpResponse<IIndirizzoPersona>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IIndirizzoPersona[]) => (this.ids = concatRes));
          }
        });
    });
  }

  updateForm(persona: IPersona): void {
    this.editForm.patchValue({
      id: persona.id,
      idStudio: persona.idStudio,
      codiceFiscale: persona.codiceFiscale,
      areaDiInteresse: persona.areaDiInteresse,
      titolo: persona.titolo,
      cognome: persona.cognome,
      nome: persona.nome,
      dataDiNascita: persona.dataDiNascita,
      luogoDiNascita: persona.luogoDiNascita,
      professione: persona.professione,
      tipo: persona.tipo,
      discriminator: persona.discriminator,
      idRuoloPersona: persona.idRuoloPersona,
      tipoRuoloUtente: persona.tipoRuoloUtente,
      idId: persona.idId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const persona = this.createFromForm();
    if (persona.id !== undefined) {
      this.subscribeToSaveResponse(this.personaService.update(persona));
    } else {
      this.subscribeToSaveResponse(this.personaService.create(persona));
    }
  }

  private createFromForm(): IPersona {
    return {
      ...new Persona(),
      id: this.editForm.get(['id'])!.value,
      idStudio: this.editForm.get(['idStudio'])!.value,
      codiceFiscale: this.editForm.get(['codiceFiscale'])!.value,
      areaDiInteresse: this.editForm.get(['areaDiInteresse'])!.value,
      titolo: this.editForm.get(['titolo'])!.value,
      cognome: this.editForm.get(['cognome'])!.value,
      nome: this.editForm.get(['nome'])!.value,
      dataDiNascita: this.editForm.get(['dataDiNascita'])!.value,
      luogoDiNascita: this.editForm.get(['luogoDiNascita'])!.value,
      professione: this.editForm.get(['professione'])!.value,
      tipo: this.editForm.get(['tipo'])!.value,
      discriminator: this.editForm.get(['discriminator'])!.value,
      idRuoloPersona: this.editForm.get(['idRuoloPersona'])!.value,
      tipoRuoloUtente: this.editForm.get(['tipoRuoloUtente'])!.value,
      idId: this.editForm.get(['idId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPersona>>): void {
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

  trackById(index: number, item: IIndirizzoPersona): any {
    return item.id;
  }
}
