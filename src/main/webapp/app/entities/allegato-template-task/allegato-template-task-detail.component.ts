import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAllegatoTemplateTask } from 'app/shared/model/allegato-template-task.model';

@Component({
  selector: 'jhi-allegato-template-task-detail',
  templateUrl: './allegato-template-task-detail.component.html',
})
export class AllegatoTemplateTaskDetailComponent implements OnInit {
  allegatoTemplateTask: IAllegatoTemplateTask | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ allegatoTemplateTask }) => (this.allegatoTemplateTask = allegatoTemplateTask));
  }

  previousState(): void {
    window.history.back();
  }
}
