import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IStudioProfessionale } from 'app/shared/model/studio-professionale.model';

@Component({
  selector: 'jhi-studio-professionale-detail',
  templateUrl: './studio-professionale-detail.component.html',
})
export class StudioProfessionaleDetailComponent implements OnInit {
  studioProfessionale: IStudioProfessionale | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ studioProfessionale }) => (this.studioProfessionale = studioProfessionale));
  }

  previousState(): void {
    window.history.back();
  }
}
