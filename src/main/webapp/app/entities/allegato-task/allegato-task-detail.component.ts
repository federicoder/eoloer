import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAllegatoTask } from 'app/shared/model/allegato-task.model';

@Component({
  selector: 'jhi-allegato-task-detail',
  templateUrl: './allegato-task-detail.component.html',
})
export class AllegatoTaskDetailComponent implements OnInit {
  allegatoTask: IAllegatoTask | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ allegatoTask }) => (this.allegatoTask = allegatoTask));
  }

  previousState(): void {
    window.history.back();
  }
}
