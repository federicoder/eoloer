import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IInvitoEvento } from 'app/shared/model/invito-evento.model';

@Component({
  selector: 'jhi-invito-evento-detail',
  templateUrl: './invito-evento-detail.component.html',
})
export class InvitoEventoDetailComponent implements OnInit {
  invitoEvento: IInvitoEvento | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ invitoEvento }) => (this.invitoEvento = invitoEvento));
  }

  previousState(): void {
    window.history.back();
  }
}
