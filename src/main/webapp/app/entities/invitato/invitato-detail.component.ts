import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IInvitato } from 'app/shared/model/invitato.model';

@Component({
  selector: 'jhi-invitato-detail',
  templateUrl: './invitato-detail.component.html',
})
export class InvitatoDetailComponent implements OnInit {
  invitato: IInvitato | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ invitato }) => (this.invitato = invitato));
  }

  previousState(): void {
    window.history.back();
  }
}
