import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IIndirizzoPersona } from 'app/shared/model/indirizzo-persona.model';

@Component({
  selector: 'jhi-indirizzo-persona-detail',
  templateUrl: './indirizzo-persona-detail.component.html',
})
export class IndirizzoPersonaDetailComponent implements OnInit {
  indirizzoPersona: IIndirizzoPersona | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ indirizzoPersona }) => (this.indirizzoPersona = indirizzoPersona));
  }

  previousState(): void {
    window.history.back();
  }
}
