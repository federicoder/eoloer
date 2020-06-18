import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IAssegnazioneTask, AssegnazioneTask } from 'app/shared/model/assegnazione-task.model';
import { AssegnazioneTaskService } from './assegnazione-task.service';
import { ITask } from 'app/shared/model/task.model';
import { TaskService } from 'app/entities/task/task.service';
import { IRappresentanzaPratica } from 'app/shared/model/rappresentanza-pratica.model';
import { RappresentanzaPraticaService } from 'app/entities/rappresentanza-pratica/rappresentanza-pratica.service';
import { IUserPersona } from 'app/shared/model/user-persona.model';
import { UserPersonaService } from 'app/entities/user-persona/user-persona.service';

type SelectableEntity = ITask | IRappresentanzaPratica | IUserPersona;

@Component({
  selector: 'jhi-assegnazione-task-update',
  templateUrl: './assegnazione-task-update.component.html',
})
export class AssegnazioneTaskUpdateComponent implements OnInit {
  isSaving = false;
  idtasks: ITask[] = [];
  idruolopersonas: IRappresentanzaPratica[] = [];
  userpersonas: IUserPersona[] = [];

  editForm = this.fb.group({
    id: [],
    idTaskRef: [null, [Validators.max(8)]],
    idUserAmmesso: [null, [Validators.max(8)]],
    ruolo: [],
    idUserConcedente: [],
    statoAssegnazione: [],
    idTaskId: [],
    idRuoloPersonaId: [],
    idPersonaId: [],
  });

  constructor(
    protected assegnazioneTaskService: AssegnazioneTaskService,
    protected taskService: TaskService,
    protected rappresentanzaPraticaService: RappresentanzaPraticaService,
    protected userPersonaService: UserPersonaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ assegnazioneTask }) => {
      this.updateForm(assegnazioneTask);

      this.taskService
        .query({ filter: 'assegnazionetask-is-null' })
        .pipe(
          map((res: HttpResponse<ITask[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ITask[]) => {
          if (!assegnazioneTask.idTaskId) {
            this.idtasks = resBody;
          } else {
            this.taskService
              .find(assegnazioneTask.idTaskId)
              .pipe(
                map((subRes: HttpResponse<ITask>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ITask[]) => (this.idtasks = concatRes));
          }
        });

      this.rappresentanzaPraticaService
        .query({ filter: 'assegnazionetask-is-null' })
        .pipe(
          map((res: HttpResponse<IRappresentanzaPratica[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IRappresentanzaPratica[]) => {
          if (!assegnazioneTask.idRuoloPersonaId) {
            this.idruolopersonas = resBody;
          } else {
            this.rappresentanzaPraticaService
              .find(assegnazioneTask.idRuoloPersonaId)
              .pipe(
                map((subRes: HttpResponse<IRappresentanzaPratica>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IRappresentanzaPratica[]) => (this.idruolopersonas = concatRes));
          }
        });

      this.userPersonaService.query().subscribe((res: HttpResponse<IUserPersona[]>) => (this.userpersonas = res.body || []));
    });
  }

  updateForm(assegnazioneTask: IAssegnazioneTask): void {
    this.editForm.patchValue({
      id: assegnazioneTask.id,
      idTaskRef: assegnazioneTask.idTaskRef,
      idUserAmmesso: assegnazioneTask.idUserAmmesso,
      ruolo: assegnazioneTask.ruolo,
      idUserConcedente: assegnazioneTask.idUserConcedente,
      statoAssegnazione: assegnazioneTask.statoAssegnazione,
      idTaskId: assegnazioneTask.idTaskId,
      idRuoloPersonaId: assegnazioneTask.idRuoloPersonaId,
      idPersonaId: assegnazioneTask.idPersonaId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const assegnazioneTask = this.createFromForm();
    if (assegnazioneTask.id !== undefined) {
      this.subscribeToSaveResponse(this.assegnazioneTaskService.update(assegnazioneTask));
    } else {
      this.subscribeToSaveResponse(this.assegnazioneTaskService.create(assegnazioneTask));
    }
  }

  private createFromForm(): IAssegnazioneTask {
    return {
      ...new AssegnazioneTask(),
      id: this.editForm.get(['id'])!.value,
      idTaskRef: this.editForm.get(['idTaskRef'])!.value,
      idUserAmmesso: this.editForm.get(['idUserAmmesso'])!.value,
      ruolo: this.editForm.get(['ruolo'])!.value,
      idUserConcedente: this.editForm.get(['idUserConcedente'])!.value,
      statoAssegnazione: this.editForm.get(['statoAssegnazione'])!.value,
      idTaskId: this.editForm.get(['idTaskId'])!.value,
      idRuoloPersonaId: this.editForm.get(['idRuoloPersonaId'])!.value,
      idPersonaId: this.editForm.get(['idPersonaId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAssegnazioneTask>>): void {
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
