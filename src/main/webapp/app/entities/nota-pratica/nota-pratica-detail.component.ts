import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INotaPratica } from 'app/shared/model/nota-pratica.model';

@Component({
  selector: 'jhi-nota-pratica-detail',
  templateUrl: './nota-pratica-detail.component.html',
})
export class NotaPraticaDetailComponent implements OnInit {
  notaPratica: INotaPratica | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ notaPratica }) => (this.notaPratica = notaPratica));
  }

  previousState(): void {
    window.history.back();
  }
}
