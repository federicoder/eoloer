import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICondivisionePratica } from 'app/shared/model/condivisione-pratica.model';

@Component({
  selector: 'jhi-condivisione-pratica-detail',
  templateUrl: './condivisione-pratica-detail.component.html',
})
export class CondivisionePraticaDetailComponent implements OnInit {
  condivisionePratica: ICondivisionePratica | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ condivisionePratica }) => (this.condivisionePratica = condivisionePratica));
  }

  previousState(): void {
    window.history.back();
  }
}
