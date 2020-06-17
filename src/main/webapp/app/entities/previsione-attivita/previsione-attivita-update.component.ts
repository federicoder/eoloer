import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IPrevisioneAttivita, PrevisioneAttivita } from 'app/shared/model/previsione-attivita.model';
import { PrevisioneAttivitaService } from './previsione-attivita.service';
import { IPrevisioneTask } from 'app/shared/model/previsione-task.model';
import { PrevisioneTaskService } from 'app/entities/previsione-task/previsione-task.service';

@Component({
  selector: 'jhi-previsione-attivita-update',
  templateUrl: './previsione-attivita-update.component.html',
})
export class PrevisioneAttivitaUpdateComponent implements OnInit {
  isSaving = false;
  idtasks: IPrevisioneTask[] = [];

  editForm = this.fb.group({
    id: [],
    idTask: [null, [Validators.required, Validators.max(8)]],
    dataPianificata: [],
    oraPianificata: [],
    dataScadenza: [],
    version: [],
    idTaskId: [],
  });

  constructor(
    protected previsioneAttivitaService: PrevisioneAttivitaService,
    protected previsioneTaskService: PrevisioneTaskService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ previsioneAttivita }) => {
      this.updateForm(previsioneAttivita);

      this.previsioneTaskService
        .query({ filter: 'idtask-is-null' })
        .pipe(
          map((res: HttpResponse<IPrevisioneTask[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IPrevisioneTask[]) => {
          if (!previsioneAttivita.idTaskId) {
            this.idtasks = resBody;
          } else {
            this.previsioneTaskService
              .find(previsioneAttivita.idTaskId)
              .pipe(
                map((subRes: HttpResponse<IPrevisioneTask>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IPrevisioneTask[]) => (this.idtasks = concatRes));
          }
        });
    });
  }

  updateForm(previsioneAttivita: IPrevisioneAttivita): void {
    this.editForm.patchValue({
      id: previsioneAttivita.id,
      idTask: previsioneAttivita.idTask,
      dataPianificata: previsioneAttivita.dataPianificata,
      oraPianificata: previsioneAttivita.oraPianificata,
      dataScadenza: previsioneAttivita.dataScadenza,
      version: previsioneAttivita.version,
      idTaskId: previsioneAttivita.idTaskId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const previsioneAttivita = this.createFromForm();
    if (previsioneAttivita.id !== undefined) {
      this.subscribeToSaveResponse(this.previsioneAttivitaService.update(previsioneAttivita));
    } else {
      this.subscribeToSaveResponse(this.previsioneAttivitaService.create(previsioneAttivita));
    }
  }

  private createFromForm(): IPrevisioneAttivita {
    return {
      ...new PrevisioneAttivita(),
      id: this.editForm.get(['id'])!.value,
      idTask: this.editForm.get(['idTask'])!.value,
      dataPianificata: this.editForm.get(['dataPianificata'])!.value,
      oraPianificata: this.editForm.get(['oraPianificata'])!.value,
      dataScadenza: this.editForm.get(['dataScadenza'])!.value,
      version: this.editForm.get(['version'])!.value,
      idTaskId: this.editForm.get(['idTaskId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPrevisioneAttivita>>): void {
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

  trackById(index: number, item: IPrevisioneTask): any {
    return item.id;
  }
}
