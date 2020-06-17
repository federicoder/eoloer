import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITipoAllegato } from 'app/shared/model/tipo-allegato.model';

@Component({
  selector: 'jhi-tipo-allegato-detail',
  templateUrl: './tipo-allegato-detail.component.html',
})
export class TipoAllegatoDetailComponent implements OnInit {
  tipoAllegato: ITipoAllegato | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tipoAllegato }) => (this.tipoAllegato = tipoAllegato));
  }

  previousState(): void {
    window.history.back();
  }
}
