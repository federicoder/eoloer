import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRisorseDisponibili } from 'app/shared/model/risorse-disponibili.model';

@Component({
  selector: 'jhi-risorse-disponibili-detail',
  templateUrl: './risorse-disponibili-detail.component.html',
})
export class RisorseDisponibiliDetailComponent implements OnInit {
  risorseDisponibili: IRisorseDisponibili | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ risorseDisponibili }) => (this.risorseDisponibili = risorseDisponibili));
  }

  previousState(): void {
    window.history.back();
  }
}
