import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPrevisioneTask } from 'app/shared/model/previsione-task.model';

@Component({
  selector: 'jhi-previsione-task-detail',
  templateUrl: './previsione-task-detail.component.html',
})
export class PrevisioneTaskDetailComponent implements OnInit {
  previsioneTask: IPrevisioneTask | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ previsioneTask }) => (this.previsioneTask = previsioneTask));
  }

  previousState(): void {
    window.history.back();
  }
}
