import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IInvito } from 'app/shared/model/invito.model';

@Component({
  selector: 'jhi-invito-detail',
  templateUrl: './invito-detail.component.html',
})
export class InvitoDetailComponent implements OnInit {
  invito: IInvito | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ invito }) => (this.invito = invito));
  }

  previousState(): void {
    window.history.back();
  }
}
