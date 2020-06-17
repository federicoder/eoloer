import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITagPersona } from 'app/shared/model/tag-persona.model';

@Component({
  selector: 'jhi-tag-persona-detail',
  templateUrl: './tag-persona-detail.component.html',
})
export class TagPersonaDetailComponent implements OnInit {
  tagPersona: ITagPersona | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tagPersona }) => (this.tagPersona = tagPersona));
  }

  previousState(): void {
    window.history.back();
  }
}
