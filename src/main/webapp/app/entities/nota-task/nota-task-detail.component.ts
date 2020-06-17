import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INotaTask } from 'app/shared/model/nota-task.model';

@Component({
  selector: 'jhi-nota-task-detail',
  templateUrl: './nota-task-detail.component.html',
})
export class NotaTaskDetailComponent implements OnInit {
  notaTask: INotaTask | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ notaTask }) => (this.notaTask = notaTask));
  }

  previousState(): void {
    window.history.back();
  }
}
