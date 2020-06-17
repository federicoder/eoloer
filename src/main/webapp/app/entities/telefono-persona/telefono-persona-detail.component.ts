import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITelefonoPersona } from 'app/shared/model/telefono-persona.model';

@Component({
  selector: 'jhi-telefono-persona-detail',
  templateUrl: './telefono-persona-detail.component.html',
})
export class TelefonoPersonaDetailComponent implements OnInit {
  telefonoPersona: ITelefonoPersona | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ telefonoPersona }) => (this.telefonoPersona = telefonoPersona));
  }

  previousState(): void {
    window.history.back();
  }
}
