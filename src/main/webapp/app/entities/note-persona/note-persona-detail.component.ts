import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INotePersona } from 'app/shared/model/note-persona.model';

@Component({
  selector: 'jhi-note-persona-detail',
  templateUrl: './note-persona-detail.component.html',
})
export class NotePersonaDetailComponent implements OnInit {
  notePersona: INotePersona | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ notePersona }) => (this.notePersona = notePersona));
  }

  previousState(): void {
    window.history.back();
  }
}
