import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IConsuntivoTask, ConsuntivoTask } from 'app/shared/model/consuntivo-task.model';
import { ConsuntivoTaskService } from './consuntivo-task.service';

@Component({
  selector: 'jhi-consuntivo-task-update',
  templateUrl: './consuntivo-task-update.component.html',
})
export class ConsuntivoTaskUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    idTask: [null, [Validators.required, Validators.max(8)]],
    dataInizio: [],
    dataFine: [],
    timeLine: [],
    version: [],
  });

  constructor(protected consuntivoTaskService: ConsuntivoTaskService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ consuntivoTask }) => {
      this.updateForm(consuntivoTask);
    });
  }

  updateForm(consuntivoTask: IConsuntivoTask): void {
    this.editForm.patchValue({
      id: consuntivoTask.id,
      idTask: consuntivoTask.idTask,
      dataInizio: consuntivoTask.dataInizio,
      dataFine: consuntivoTask.dataFine,
      timeLine: consuntivoTask.timeLine,
      version: consuntivoTask.version,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const consuntivoTask = this.createFromForm();
    if (consuntivoTask.id !== undefined) {
      this.subscribeToSaveResponse(this.consuntivoTaskService.update(consuntivoTask));
    } else {
      this.subscribeToSaveResponse(this.consuntivoTaskService.create(consuntivoTask));
    }
  }

  private createFromForm(): IConsuntivoTask {
    return {
      ...new ConsuntivoTask(),
      id: this.editForm.get(['id'])!.value,
      idTask: this.editForm.get(['idTask'])!.value,
      dataInizio: this.editForm.get(['dataInizio'])!.value,
      dataFine: this.editForm.get(['dataFine'])!.value,
      timeLine: this.editForm.get(['timeLine'])!.value,
      version: this.editForm.get(['version'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IConsuntivoTask>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
