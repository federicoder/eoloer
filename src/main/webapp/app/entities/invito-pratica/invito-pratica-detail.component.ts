import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IInvitoPratica } from 'app/shared/model/invito-pratica.model';

@Component({
  selector: 'jhi-invito-pratica-detail',
  templateUrl: './invito-pratica-detail.component.html',
})
export class InvitoPraticaDetailComponent implements OnInit {
  invitoPratica: IInvitoPratica | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ invitoPratica }) => (this.invitoPratica = invitoPratica));
  }

  previousState(): void {
    window.history.back();
  }
}
