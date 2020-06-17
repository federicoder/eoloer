import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRappresentanzaPratica } from 'app/shared/model/rappresentanza-pratica.model';

@Component({
  selector: 'jhi-rappresentanza-pratica-detail',
  templateUrl: './rappresentanza-pratica-detail.component.html',
})
export class RappresentanzaPraticaDetailComponent implements OnInit {
  rappresentanzaPratica: IRappresentanzaPratica | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ rappresentanzaPratica }) => (this.rappresentanzaPratica = rappresentanzaPratica));
  }

  previousState(): void {
    window.history.back();
  }
}
