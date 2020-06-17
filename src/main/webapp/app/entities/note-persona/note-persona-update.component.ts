import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { INotePersona, NotePersona } from 'app/shared/model/note-persona.model';
import { NotePersonaService } from './note-persona.service';
import { IPersona } from 'app/shared/model/persona.model';
import { PersonaService } from 'app/entities/persona/persona.service';

@Component({
  selector: 'jhi-note-persona-update',
  templateUrl: './note-persona-update.component.html',
})
export class NotePersonaUpdateComponent implements OnInit {
  isSaving = false;
  personas: IPersona[] = [];

  editForm = this.fb.group({
    id: [],
    idPersonaRef: [null, [Validators.required]],
    idNotePersona: [null, [Validators.required]],
    testo: [],
    personaId: [],
  });

  constructor(
    protected notePersonaService: NotePersonaService,
    protected personaService: PersonaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ notePersona }) => {
      this.updateForm(notePersona);

      this.personaService.query().subscribe((res: HttpResponse<IPersona[]>) => (this.personas = res.body || []));
    });
  }

  updateForm(notePersona: INotePersona): void {
    this.editForm.patchValue({
      id: notePersona.id,
      idPersonaRef: notePersona.idPersonaRef,
      idNotePersona: notePersona.idNotePersona,
      testo: notePersona.testo,
      personaId: notePersona.personaId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const notePersona = this.createFromForm();
    if (notePersona.id !== undefined) {
      this.subscribeToSaveResponse(this.notePersonaService.update(notePersona));
    } else {
      this.subscribeToSaveResponse(this.notePersonaService.create(notePersona));
    }
  }

  private createFromForm(): INotePersona {
    return {
      ...new NotePersona(),
      id: this.editForm.get(['id'])!.value,
      idPersonaRef: this.editForm.get(['idPersonaRef'])!.value,
      idNotePersona: this.editForm.get(['idNotePersona'])!.value,
      testo: this.editForm.get(['testo'])!.value,
      personaId: this.editForm.get(['personaId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<INotePersona>>): void {
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
