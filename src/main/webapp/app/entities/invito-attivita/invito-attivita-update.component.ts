import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IInvitoAttivita, InvitoAttivita } from 'app/shared/model/invito-attivita.model';
import { InvitoAttivitaService } from './invito-attivita.service';
import { ITask } from 'app/shared/model/task.model';
import { TaskService } from 'app/entities/task/task.service';
import { IInvito } from 'app/shared/model/invito.model';
import { InvitoService } from 'app/entities/invito/invito.service';

type SelectableEntity = ITask | IInvito;

@Component({
  selector: 'jhi-invito-attivita-update',
  templateUrl: './invito-attivita-update.component.html',
})
export class InvitoAttivitaUpdateComponent implements OnInit {
  isSaving = false;
  idtasks: ITask[] = [];
  idtasks: IInvito[] = [];

  editForm = this.fb.group({
    id: [],
    idTaskRef: [],
    idTaskId: [],
    idTaskId: [],
  });

  constructor(
    protected invitoAttivitaService: InvitoAttivitaService,
    protected taskService: TaskService,
    protected invitoService: InvitoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ invitoAttivita }) => {
      this.updateForm(invitoAttivita);

      this.taskService
        .query({ filter: 'invitoattivita-is-null' })
        .pipe(
          map((res: HttpResponse<ITask[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ITask[]) => {
          if (!invitoAttivita.idTaskId) {
            this.idtasks = resBody;
          } else {
            this.taskService
              .find(invitoAttivita.idTaskId)
              .pipe(
                map((subRes: HttpResponse<ITask>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ITask[]) => (this.idtasks = concatRes));
          }
        });

      this.invitoService
        .query({ filter: 'invitoattivita-is-null' })
        .pipe(
          map((res: HttpResponse<IInvito[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IInvito[]) => {
          if (!invitoAttivita.idTaskId) {
            this.idtasks = resBody;
          } else {
            this.invitoService
              .find(invitoAttivita.idTaskId)
              .pipe(
                map((subRes: HttpResponse<IInvito>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IInvito[]) => (this.idtasks = concatRes));
          }
        });
    });
  }

  updateForm(invitoAttivita: IInvitoAttivita): void {
    this.editForm.patchValue({
      id: invitoAttivita.id,
      idTaskRef: invitoAttivita.idTaskRef,
      idTaskId: invitoAttivita.idTaskId,
      idTaskId: invitoAttivita.idTaskId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const invitoAttivita = this.createFromForm();
    if (invitoAttivita.id !== undefined) {
      this.subscribeToSaveResponse(this.invitoAttivitaService.update(invitoAttivita));
    } else {
      this.subscribeToSaveResponse(this.invitoAttivitaService.create(invitoAttivita));
    }
  }

  private createFromForm(): IInvitoAttivita {
    return {
      ...new InvitoAttivita(),
      id: this.editForm.get(['id'])!.value,
      idTaskRef: this.editForm.get(['idTaskRef'])!.value,
      idTaskId: this.editForm.get(['idTaskId'])!.value,
      idTaskId: this.editForm.get(['idTaskId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IInvitoAttivita>>): void {
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
