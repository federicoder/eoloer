import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITemplatePratica } from 'app/shared/model/template-pratica.model';

@Component({
  selector: 'jhi-template-pratica-detail',
  templateUrl: './template-pratica-detail.component.html',
})
export class TemplatePraticaDetailComponent implements OnInit {
  templatePratica: ITemplatePratica | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ templatePratica }) => (this.templatePratica = templatePratica));
  }

  previousState(): void {
    window.history.back();
  }
}
