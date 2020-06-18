import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPersona, Persona } from 'app/shared/model/persona.model';
import { PersonaService } from './persona.service';

@Component({
  selector: 'jhi-persona-update',
  templateUrl: './persona-update.component.html',
})
export class PersonaUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    idStudioProfessionaleRef: [null, [Validators.max(8)]],
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
    idRuoloPersonaRef: [],
    tipoRuoloUtente: [],
  });

  constructor(protected personaService: PersonaService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ persona }) => {
      this.updateForm(persona);
    });
  }

  updateForm(persona: IPersona): void {
    this.editForm.patchValue({
      id: persona.id,
      idStudioProfessionaleRef: persona.idStudioProfessionaleRef,
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
      idRuoloPersonaRef: persona.idRuoloPersonaRef,
      tipoRuoloUtente: persona.tipoRuoloUtente,
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
      idStudioProfessionaleRef: this.editForm.get(['idStudioProfessionaleRef'])!.value,
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
      idRuoloPersonaRef: this.editForm.get(['idRuoloPersonaRef'])!.value,
      tipoRuoloUtente: this.editForm.get(['tipoRuoloUtente'])!.value,
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
}
