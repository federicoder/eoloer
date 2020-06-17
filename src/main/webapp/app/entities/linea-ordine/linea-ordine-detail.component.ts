import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILineaOrdine } from 'app/shared/model/linea-ordine.model';

@Component({
  selector: 'jhi-linea-ordine-detail',
  templateUrl: './linea-ordine-detail.component.html',
})
export class LineaOrdineDetailComponent implements OnInit {
  lineaOrdine: ILineaOrdine | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ lineaOrdine }) => (this.lineaOrdine = lineaOrdine));
  }

  previousState(): void {
    window.history.back();
  }
}
