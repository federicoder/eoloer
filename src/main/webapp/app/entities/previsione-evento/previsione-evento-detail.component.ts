import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPrevisioneEvento } from 'app/shared/model/previsione-evento.model';

@Component({
  selector: 'jhi-previsione-evento-detail',
  templateUrl: './previsione-evento-detail.component.html',
})
export class PrevisioneEventoDetailComponent implements OnInit {
  previsioneEvento: IPrevisioneEvento | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ previsioneEvento }) => (this.previsioneEvento = previsioneEvento));
  }

  previousState(): void {
    window.history.back();
  }
}
