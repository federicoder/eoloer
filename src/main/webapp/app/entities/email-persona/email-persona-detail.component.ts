import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEmailPersona } from 'app/shared/model/email-persona.model';

@Component({
  selector: 'jhi-email-persona-detail',
  templateUrl: './email-persona-detail.component.html',
})
export class EmailPersonaDetailComponent implements OnInit {
  emailPersona: IEmailPersona | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ emailPersona }) => (this.emailPersona = emailPersona));
  }

  previousState(): void {
    window.history.back();
  }
}
