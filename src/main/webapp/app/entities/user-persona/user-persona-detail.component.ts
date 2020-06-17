import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IUserPersona } from 'app/shared/model/user-persona.model';

@Component({
  selector: 'jhi-user-persona-detail',
  templateUrl: './user-persona-detail.component.html',
})
export class UserPersonaDetailComponent implements OnInit {
  userPersona: IUserPersona | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ userPersona }) => (this.userPersona = userPersona));
  }

  previousState(): void {
    window.history.back();
  }
}
