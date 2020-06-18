import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IAllegatoTask, AllegatoTask } from 'app/shared/model/allegato-task.model';
import { AllegatoTaskService } from './allegato-task.service';
import { ITipoAllegato } from 'app/shared/model/tipo-allegato.model';
import { TipoAllegatoService } from 'app/entities/tipo-allegato/tipo-allegato.service';
import { ITask } from 'app/shared/model/task.model';
import { TaskService } from 'app/entities/task/task.service';

type SelectableEntity = ITipoAllegato | ITask | IAllegatoTask;

@Component({
  selector: 'jhi-allegato-task-update',
  templateUrl: './allegato-task-update.component.html',
})
export class AllegatoTaskUpdateComponent implements OnInit {
  isSaving = false;
  tipoallegatoes: ITipoAllegato[] = [];
  tasks: ITask[] = [];
  allegatotasks: IAllegatoTask[] = [];

  editForm = this.fb.group({
    id: [],
    idTipoAllegatoRef: [null, [Validators.max(8)]],
    idTaskRef: [null, [Validators.max(8)]],
    formato: [],
    note: [],
    stato: [],
    pubblico: [],
    version: [],
    idAllegatoMaster: [null, [Validators.required]],
    idTipoAllegatoId: [],
    idTaskId: [],
    allegatoTaskId: [],
  });

  constructor(
    protected allegatoTaskService: AllegatoTaskService,
    protected tipoAllegatoService: TipoAllegatoService,
    protected taskService: TaskService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ allegatoTask }) => {
      this.updateForm(allegatoTask);

      this.tipoAllegatoService.query().subscribe((res: HttpResponse<ITipoAllegato[]>) => (this.tipoallegatoes = res.body || []));

      this.taskService.query().subscribe((res: HttpResponse<ITask[]>) => (this.tasks = res.body || []));

      this.allegatoTaskService.query().subscribe((res: HttpResponse<IAllegatoTask[]>) => (this.allegatotasks = res.body || []));
    });
  }

  updateForm(allegatoTask: IAllegatoTask): void {
    this.editForm.patchValue({
      id: allegatoTask.id,
      idTipoAllegatoRef: allegatoTask.idTipoAllegatoRef,
      idTaskRef: allegatoTask.idTaskRef,
      formato: allegatoTask.formato,
      note: allegatoTask.note,
      stato: allegatoTask.stato,
      pubblico: allegatoTask.pubblico,
      version: allegatoTask.version,
      idAllegatoMaster: allegatoTask.idAllegatoMaster,
      idTipoAllegatoId: allegatoTask.idTipoAllegatoId,
      idTaskId: allegatoTask.idTaskId,
      allegatoTaskId: allegatoTask.allegatoTaskId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const allegatoTask = this.createFromForm();
    if (allegatoTask.id !== undefined) {
      this.subscribeToSaveResponse(this.allegatoTaskService.update(allegatoTask));
    } else {
      this.subscribeToSaveResponse(this.allegatoTaskService.create(allegatoTask));
    }
  }

  private createFromForm(): IAllegatoTask {
    return {
      ...new AllegatoTask(),
      id: this.editForm.get(['id'])!.value,
      idTipoAllegatoRef: this.editForm.get(['idTipoAllegatoRef'])!.value,
      idTaskRef: this.editForm.get(['idTaskRef'])!.value,
      formato: this.editForm.get(['formato'])!.value,
      note: this.editForm.get(['note'])!.value,
      stato: this.editForm.get(['stato'])!.value,
      pubblico: this.editForm.get(['pubblico'])!.value,
      version: this.editForm.get(['version'])!.value,
      idAllegatoMaster: this.editForm.get(['idAllegatoMaster'])!.value,
      idTipoAllegatoId: this.editForm.get(['idTipoAllegatoId'])!.value,
      idTaskId: this.editForm.get(['idTaskId'])!.value,
      allegatoTaskId: this.editForm.get(['allegatoTaskId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAllegatoTask>>): void {
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
