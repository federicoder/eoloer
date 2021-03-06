import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { ITask, Task } from 'app/shared/model/task.model';
import { TaskService } from './task.service';
import { IConsuntivoTask } from 'app/shared/model/consuntivo-task.model';
import { ConsuntivoTaskService } from 'app/entities/consuntivo-task/consuntivo-task.service';
import { IPrevisioneTask } from 'app/shared/model/previsione-task.model';
import { PrevisioneTaskService } from 'app/entities/previsione-task/previsione-task.service';
import { IPratica } from 'app/shared/model/pratica.model';
import { PraticaService } from 'app/entities/pratica/pratica.service';

type SelectableEntity = IConsuntivoTask | IPrevisioneTask | IPratica;

@Component({
  selector: 'jhi-task-update',
  templateUrl: './task-update.component.html',
})
export class TaskUpdateComponent implements OnInit {
  isSaving = false;
  idtasks: IConsuntivoTask[] = [];
  idtasks: IPrevisioneTask[] = [];
  praticas: IPratica[] = [];

  editForm = this.fb.group({
    id: [],
    idPraticaRef: [null, [Validators.max(8)]],
    nome: [],
    stato: [],
    prioritario: [],
    pubblico: [],
    version: [],
    idCondivisionePraticaRef: [],
    idAssegnazioneTaskRef: [null, [Validators.max(8)]],
    idInvitoRef: [null, [Validators.max(8)]],
    idTaskId: [],
    idTaskId: [],
    idPraticaId: [],
  });

  constructor(
    protected taskService: TaskService,
    protected consuntivoTaskService: ConsuntivoTaskService,
    protected previsioneTaskService: PrevisioneTaskService,
    protected praticaService: PraticaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ task }) => {
      this.updateForm(task);

      this.consuntivoTaskService
        .query({ filter: 'task-is-null' })
        .pipe(
          map((res: HttpResponse<IConsuntivoTask[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IConsuntivoTask[]) => {
          if (!task.idTaskId) {
            this.idtasks = resBody;
          } else {
            this.consuntivoTaskService
              .find(task.idTaskId)
              .pipe(
                map((subRes: HttpResponse<IConsuntivoTask>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IConsuntivoTask[]) => (this.idtasks = concatRes));
          }
        });

      this.previsioneTaskService
        .query({ filter: 'task-is-null' })
        .pipe(
          map((res: HttpResponse<IPrevisioneTask[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IPrevisioneTask[]) => {
          if (!task.idTaskId) {
            this.idtasks = resBody;
          } else {
            this.previsioneTaskService
              .find(task.idTaskId)
              .pipe(
                map((subRes: HttpResponse<IPrevisioneTask>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IPrevisioneTask[]) => (this.idtasks = concatRes));
          }
        });

      this.praticaService.query().subscribe((res: HttpResponse<IPratica[]>) => (this.praticas = res.body || []));
    });
  }

  updateForm(task: ITask): void {
    this.editForm.patchValue({
      id: task.id,
      idPraticaRef: task.idPraticaRef,
      nome: task.nome,
      stato: task.stato,
      prioritario: task.prioritario,
      pubblico: task.pubblico,
      version: task.version,
      idCondivisionePraticaRef: task.idCondivisionePraticaRef,
      idAssegnazioneTaskRef: task.idAssegnazioneTaskRef,
      idInvitoRef: task.idInvitoRef,
      idTaskId: task.idTaskId,
      idTaskId: task.idTaskId,
      idPraticaId: task.idPraticaId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const task = this.createFromForm();
    if (task.id !== undefined) {
      this.subscribeToSaveResponse(this.taskService.update(task));
    } else {
      this.subscribeToSaveResponse(this.taskService.create(task));
    }
  }

  private createFromForm(): ITask {
    return {
      ...new Task(),
      id: this.editForm.get(['id'])!.value,
      idPraticaRef: this.editForm.get(['idPraticaRef'])!.value,
      nome: this.editForm.get(['nome'])!.value,
      stato: this.editForm.get(['stato'])!.value,
      prioritario: this.editForm.get(['prioritario'])!.value,
      pubblico: this.editForm.get(['pubblico'])!.value,
      version: this.editForm.get(['version'])!.value,
      idCondivisionePraticaRef: this.editForm.get(['idCondivisionePraticaRef'])!.value,
      idAssegnazioneTaskRef: this.editForm.get(['idAssegnazioneTaskRef'])!.value,
      idInvitoRef: this.editForm.get(['idInvitoRef'])!.value,
      idTaskId: this.editForm.get(['idTaskId'])!.value,
      idTaskId: this.editForm.get(['idTaskId'])!.value,
      idPraticaId: this.editForm.get(['idPraticaId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITask>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
