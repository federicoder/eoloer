import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IInvitoAttivita } from 'app/shared/model/invito-attivita.model';

@Component({
  selector: 'jhi-invito-attivita-detail',
  templateUrl: './invito-attivita-detail.component.html',
})
export class InvitoAttivitaDetailComponent implements OnInit {
  invitoAttivita: IInvitoAttivita | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ invitoAttivita }) => (this.invitoAttivita = invitoAttivita));
  }

  previousState(): void {
    window.history.back();
  }
}
