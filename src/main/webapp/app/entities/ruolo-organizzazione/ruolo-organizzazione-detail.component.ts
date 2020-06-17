import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRuoloOrganizzazione } from 'app/shared/model/ruolo-organizzazione.model';

@Component({
  selector: 'jhi-ruolo-organizzazione-detail',
  templateUrl: './ruolo-organizzazione-detail.component.html',
})
export class RuoloOrganizzazioneDetailComponent implements OnInit {
  ruoloOrganizzazione: IRuoloOrganizzazione | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ruoloOrganizzazione }) => (this.ruoloOrganizzazione = ruoloOrganizzazione));
  }

  previousState(): void {
    window.history.back();
  }
}
