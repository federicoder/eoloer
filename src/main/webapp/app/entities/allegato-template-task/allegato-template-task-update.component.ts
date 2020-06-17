import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IAllegatoTemplateTask, AllegatoTemplateTask } from 'app/shared/model/allegato-template-task.model';
import { AllegatoTemplateTaskService } from './allegato-template-task.service';
import { ITemplateTask } from 'app/shared/model/template-task.model';
import { TemplateTaskService } from 'app/entities/template-task/template-task.service';
import { ITipoAllegato } from 'app/shared/model/tipo-allegato.model';
import { TipoAllegatoService } from 'app/entities/tipo-allegato/tipo-allegato.service';

type SelectableEntity = ITemplateTask | ITipoAllegato;

@Component({
  selector: 'jhi-allegato-template-task-update',
  templateUrl: './allegato-template-task-update.component.html',
})
export class AllegatoTemplateTaskUpdateComponent implements OnInit {
  isSaving = false;
  templatetasks: ITemplateTask[] = [];
  tipoallegatoes: ITipoAllegato[] = [];

  editForm = this.fb.group({
    id: [],
    idTemplateTask: [null, [Validators.required, Validators.max(8)]],
    tipoAllegato: [],
    formato: [],
    idFile: [],
    pubPriv: [],
    templateTaskId: [],
    tipoAllegatoId: [],
  });

  constructor(
    protected allegatoTemplateTaskService: AllegatoTemplateTaskService,
    protected templateTaskService: TemplateTaskService,
    protected tipoAllegatoService: TipoAllegatoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ allegatoTemplateTask }) => {
      this.updateForm(allegatoTemplateTask);

      this.templateTaskService.query().subscribe((res: HttpResponse<ITemplateTask[]>) => (this.templatetasks = res.body || []));

      this.tipoAllegatoService.query().subscribe((res: HttpResponse<ITipoAllegato[]>) => (this.tipoallegatoes = res.body || []));
    });
  }

  updateForm(allegatoTemplateTask: IAllegatoTemplateTask): void {
    this.editForm.patchValue({
      id: allegatoTemplateTask.id,
      idTemplateTask: allegatoTemplateTask.idTemplateTask,
      tipoAllegato: allegatoTemplateTask.tipoAllegato,
      formato: allegatoTemplateTask.formato,
      idFile: allegatoTemplateTask.idFile,
      pubPriv: allegatoTemplateTask.pubPriv,
      templateTaskId: allegatoTemplateTask.templateTaskId,
      tipoAllegatoId: allegatoTemplateTask.tipoAllegatoId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const allegatoTemplateTask = this.createFromForm();
    if (allegatoTemplateTask.id !== undefined) {
      this.subscribeToSaveResponse(this.allegatoTemplateTaskService.update(allegatoTemplateTask));
    } else {
      this.subscribeToSaveResponse(this.allegatoTemplateTaskService.create(allegatoTemplateTask));
    }
  }

  private createFromForm(): IAllegatoTemplateTask {
    return {
      ...new AllegatoTemplateTask(),
      id: this.editForm.get(['id'])!.value,
      idTemplateTask: this.editForm.get(['idTemplateTask'])!.value,
      tipoAllegato: this.editForm.get(['tipoAllegato'])!.value,
      formato: this.editForm.get(['formato'])!.value,
      idFile: this.editForm.get(['idFile'])!.value,
      pubPriv: this.editForm.get(['pubPriv'])!.value,
      templateTaskId: this.editForm.get(['templateTaskId'])!.value,
      tipoAllegatoId: this.editForm.get(['tipoAllegatoId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAllegatoTemplateTask>>): void {
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
