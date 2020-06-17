import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDatiContabili } from 'app/shared/model/dati-contabili.model';

@Component({
  selector: 'jhi-dati-contabili-detail',
  templateUrl: './dati-contabili-detail.component.html',
})
export class DatiContabiliDetailComponent implements OnInit {
  datiContabili: IDatiContabili | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ datiContabili }) => (this.datiContabili = datiContabili));
  }

  previousState(): void {
    window.history.back();
  }
}
