import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAssegnazioneTask } from 'app/shared/model/assegnazione-task.model';

@Component({
  selector: 'jhi-assegnazione-task-detail',
  templateUrl: './assegnazione-task-detail.component.html',
})
export class AssegnazioneTaskDetailComponent implements OnInit {
  assegnazioneTask: IAssegnazioneTask | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ assegnazioneTask }) => (this.assegnazioneTask = assegnazioneTask));
  }

  previousState(): void {
    window.history.back();
  }
}
