import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITemplateTask } from 'app/shared/model/template-task.model';

@Component({
  selector: 'jhi-template-task-detail',
  templateUrl: './template-task-detail.component.html',
})
export class TemplateTaskDetailComponent implements OnInit {
  templateTask: ITemplateTask | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ templateTask }) => (this.templateTask = templateTask));
  }

  previousState(): void {
    window.history.back();
  }
}
