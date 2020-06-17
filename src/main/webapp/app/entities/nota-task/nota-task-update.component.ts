import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { INotaTask, NotaTask } from 'app/shared/model/nota-task.model';
import { NotaTaskService } from './nota-task.service';
import { ITask } from 'app/shared/model/task.model';
import { TaskService } from 'app/entities/task/task.service';

@Component({
  selector: 'jhi-nota-task-update',
  templateUrl: './nota-task-update.component.html',
})
export class NotaTaskUpdateComponent implements OnInit {
  isSaving = false;
  tasks: ITask[] = [];

  editForm = this.fb.group({
    id: [],
    idNotaTask: [null, [Validators.required, Validators.max(8)]],
    idTaskRef: [null, [Validators.max(8)]],
    data: [],
    nota: [],
    version: [],
    idTaskRefId: [],
  });

  constructor(
    protected notaTaskService: NotaTaskService,
    protected taskService: TaskService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ notaTask }) => {
      this.updateForm(notaTask);

      this.taskService.query().subscribe((res: HttpResponse<ITask[]>) => (this.tasks = res.body || []));
    });
  }

  updateForm(notaTask: INotaTask): void {
    this.editForm.patchValue({
      id: notaTask.id,
      idNotaTask: notaTask.idNotaTask,
      idTaskRef: notaTask.idTaskRef,
      data: notaTask.data,
      nota: notaTask.nota,
      version: notaTask.version,
      idTaskRefId: notaTask.idTaskRefId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const notaTask = this.createFromForm();
    if (notaTask.id !== undefined) {
      this.subscribeToSaveResponse(this.notaTaskService.update(notaTask));
    } else {
      this.subscribeToSaveResponse(this.notaTaskService.create(notaTask));
    }
  }

  private createFromForm(): INotaTask {
    return {
      ...new NotaTask(),
      id: this.editForm.get(['id'])!.value,
      idNotaTask: this.editForm.get(['idNotaTask'])!.value,
      idTaskRef: this.editForm.get(['idTaskRef'])!.value,
      data: this.editForm.get(['data'])!.value,
      nota: this.editForm.get(['nota'])!.value,
      version: this.editForm.get(['version'])!.value,
      idTaskRefId: this.editForm.get(['idTaskRefId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<INotaTask>>): void {
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

  trackById(index: number, item: ITask): any {
    return item.id;
  }
}
