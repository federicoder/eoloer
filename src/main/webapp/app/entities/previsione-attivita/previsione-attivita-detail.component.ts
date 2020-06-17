import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPrevisioneAttivita } from 'app/shared/model/previsione-attivita.model';

@Component({
  selector: 'jhi-previsione-attivita-detail',
  templateUrl: './previsione-attivita-detail.component.html',
})
export class PrevisioneAttivitaDetailComponent implements OnInit {
  previsioneAttivita: IPrevisioneAttivita | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ previsioneAttivita }) => (this.previsioneAttivita = previsioneAttivita));
  }

  previousState(): void {
    window.history.back();
  }
}
