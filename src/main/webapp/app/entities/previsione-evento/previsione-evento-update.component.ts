import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IPrevisioneEvento, PrevisioneEvento } from 'app/shared/model/previsione-evento.model';
import { PrevisioneEventoService } from './previsione-evento.service';
import { IPrevisioneTask } from 'app/shared/model/previsione-task.model';
import { PrevisioneTaskService } from 'app/entities/previsione-task/previsione-task.service';

@Component({
  selector: 'jhi-previsione-evento-update',
  templateUrl: './previsione-evento-update.component.html',
})
export class PrevisioneEventoUpdateComponent implements OnInit {
  isSaving = false;
  idtaskrefs: IPrevisioneTask[] = [];

  editForm = this.fb.group({
    id: [],
    idTaskRef: [null, [Validators.required, Validators.max(8)]],
    dataInizio: [],
    dataFine: [],
    luogo: [],
    indicazioniLuogo: [],
    version: [],
    idTaskRefId: [],
  });

  constructor(
    protected previsioneEventoService: PrevisioneEventoService,
    protected previsioneTaskService: PrevisioneTaskService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ previsioneEvento }) => {
      this.updateForm(previsioneEvento);

      this.previsioneTaskService
        .query({ filter: 'idtaskref-is-null' })
        .pipe(
          map((res: HttpResponse<IPrevisioneTask[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IPrevisioneTask[]) => {
          if (!previsioneEvento.idTaskRefId) {
            this.idtaskrefs = resBody;
          } else {
            this.previsioneTaskService
              .find(previsioneEvento.idTaskRefId)
              .pipe(
                map((subRes: HttpResponse<IPrevisioneTask>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IPrevisioneTask[]) => (this.idtaskrefs = concatRes));
          }
        });
    });
  }

  updateForm(previsioneEvento: IPrevisioneEvento): void {
    this.editForm.patchValue({
      id: previsioneEvento.id,
      idTaskRef: previsioneEvento.idTaskRef,
      dataInizio: previsioneEvento.dataInizio,
      dataFine: previsioneEvento.dataFine,
      luogo: previsioneEvento.luogo,
      indicazioniLuogo: previsioneEvento.indicazioniLuogo,
      version: previsioneEvento.version,
      idTaskRefId: previsioneEvento.idTaskRefId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const previsioneEvento = this.createFromForm();
    if (previsioneEvento.id !== undefined) {
      this.subscribeToSaveResponse(this.previsioneEventoService.update(previsioneEvento));
    } else {
      this.subscribeToSaveResponse(this.previsioneEventoService.create(previsioneEvento));
    }
  }

  private createFromForm(): IPrevisioneEvento {
    return {
      ...new PrevisioneEvento(),
      id: this.editForm.get(['id'])!.value,
      idTaskRef: this.editForm.get(['idTaskRef'])!.value,
      dataInizio: this.editForm.get(['dataInizio'])!.value,
      dataFine: this.editForm.get(['dataFine'])!.value,
      luogo: this.editForm.get(['luogo'])!.value,
      indicazioniLuogo: this.editForm.get(['indicazioniLuogo'])!.value,
      version: this.editForm.get(['version'])!.value,
      idTaskRefId: this.editForm.get(['idTaskRefId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPrevisioneEvento>>): void {
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
