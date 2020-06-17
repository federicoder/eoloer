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
import { IAssegnazioneTask } from 'app/shared/model/assegnazione-task.model';
import { AssegnazioneTaskService } from 'app/entities/assegnazione-task/assegnazione-task.service';
import { IInvitoAttivita } from 'app/shared/model/invito-attivita.model';
import { InvitoAttivitaService } from 'app/entities/invito-attivita/invito-attivita.service';
import { IPratica } from 'app/shared/model/pratica.model';
import { PraticaService } from 'app/entities/pratica/pratica.service';

type SelectableEntity = IConsuntivoTask | IPrevisioneTask | IAssegnazioneTask | IInvitoAttivita | IPratica;

@Component({
  selector: 'jhi-task-update',
  templateUrl: './task-update.component.html',
})
export class TaskUpdateComponent implements OnInit {
  isSaving = false;
  idtasks: IConsuntivoTask[] = [];
  idtasks: IPrevisioneTask[] = [];
  idtasks: IAssegnazioneTask[] = [];
  idtasks: IInvitoAttivita[] = [];
  praticas: IPratica[] = [];

  editForm = this.fb.group({
    id: [],
    idTask: [null, [Validators.required, Validators.max(8)]],
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
    idTaskId: [],
    idTaskId: [],
    praticaId: [],
  });

  constructor(
    protected taskService: TaskService,
    protected consuntivoTaskService: ConsuntivoTaskService,
    protected previsioneTaskService: PrevisioneTaskService,
    protected assegnazioneTaskService: AssegnazioneTaskService,
    protected invitoAttivitaService: InvitoAttivitaService,
    protected praticaService: PraticaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ task }) => {
      this.updateForm(task);

      this.consuntivoTaskService
        .query({ filter: 'idtaskref-is-null' })
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
        .query({ filter: 'idtaskref-is-null' })
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

      this.assegnazioneTaskService
        .query({ filter: 'idtaskref-is-null' })
        .pipe(
          map((res: HttpResponse<IAssegnazioneTask[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IAssegnazioneTask[]) => {
          if (!task.idTaskId) {
            this.idtasks = resBody;
          } else {
            this.assegnazioneTaskService
              .find(task.idTaskId)
              .pipe(
                map((subRes: HttpResponse<IAssegnazioneTask>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IAssegnazioneTask[]) => (this.idtasks = concatRes));
          }
        });

      this.invitoAttivitaService
        .query({ filter: 'idtaskref-is-null' })
        .pipe(
          map((res: HttpResponse<IInvitoAttivita[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IInvitoAttivita[]) => {
          if (!task.idTaskId) {
            this.idtasks = resBody;
          } else {
            this.invitoAttivitaService
              .find(task.idTaskId)
              .pipe(
                map((subRes: HttpResponse<IInvitoAttivita>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IInvitoAttivita[]) => (this.idtasks = concatRes));
          }
        });

      this.praticaService.query().subscribe((res: HttpResponse<IPratica[]>) => (this.praticas = res.body || []));
    });
  }

  updateForm(task: ITask): void {
    this.editForm.patchValue({
      id: task.id,
      idTask: task.idTask,
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
      idTaskId: task.idTaskId,
      idTaskId: task.idTaskId,
      praticaId: task.praticaId,
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
      idTask: this.editForm.get(['idTask'])!.value,
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
      idTaskId: this.editForm.get(['idTaskId'])!.value,
      idTaskId: this.editForm.get(['idTaskId'])!.value,
      praticaId: this.editForm.get(['praticaId'])!.value,
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
