import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IConsuntivoTask } from 'app/shared/model/consuntivo-task.model';

@Component({
  selector: 'jhi-consuntivo-task-detail',
  templateUrl: './consuntivo-task-detail.component.html',
})
export class ConsuntivoTaskDetailComponent implements OnInit {
  consuntivoTask: IConsuntivoTask | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ consuntivoTask }) => (this.consuntivoTask = consuntivoTask));
  }

  previousState(): void {
    window.history.back();
  }
}
