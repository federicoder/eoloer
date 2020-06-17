import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPersonaFisica } from 'app/shared/model/persona-fisica.model';

@Component({
  selector: 'jhi-persona-fisica-detail',
  templateUrl: './persona-fisica-detail.component.html',
})
export class PersonaFisicaDetailComponent implements OnInit {
  personaFisica: IPersonaFisica | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ personaFisica }) => (this.personaFisica = personaFisica));
  }

  previousState(): void {
    window.history.back();
  }
}
