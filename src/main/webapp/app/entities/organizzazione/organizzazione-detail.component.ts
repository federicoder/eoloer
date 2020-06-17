import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IOrganizzazione } from 'app/shared/model/organizzazione.model';

@Component({
  selector: 'jhi-organizzazione-detail',
  templateUrl: './organizzazione-detail.component.html',
})
export class OrganizzazioneDetailComponent implements OnInit {
  organizzazione: IOrganizzazione | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ organizzazione }) => (this.organizzazione = organizzazione));
  }

  previousState(): void {
    window.history.back();
  }
}
