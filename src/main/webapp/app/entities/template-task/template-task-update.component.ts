import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITemplateTask, TemplateTask } from 'app/shared/model/template-task.model';
import { TemplateTaskService } from './template-task.service';
import { ITemplatePratica } from 'app/shared/model/template-pratica.model';
import { TemplatePraticaService } from 'app/entities/template-pratica/template-pratica.service';

type SelectableEntity = ITemplatePratica | ITemplateTask;

@Component({
  selector: 'jhi-template-task-update',
  templateUrl: './template-task-update.component.html',
})
export class TemplateTaskUpdateComponent implements OnInit {
  isSaving = false;
  templatepraticas: ITemplatePratica[] = [];
  templatetasks: ITemplateTask[] = [];

  editForm = this.fb.group({
    id: [],
    idTemplateTask: [null, [Validators.required, Validators.max(8)]],
    ordineEsecuzione: [],
    nome: [],
    note: [],
    pubPriv: [],
    idTemplatePraticaRef: [],
    templatePraticaId: [],
    templateTaskId: [],
  });

  constructor(
    protected templateTaskService: TemplateTaskService,
    protected templatePraticaService: TemplatePraticaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ templateTask }) => {
      this.updateForm(templateTask);

      this.templatePraticaService.query().subscribe((res: HttpResponse<ITemplatePratica[]>) => (this.templatepraticas = res.body || []));

      this.templateTaskService.query().subscribe((res: HttpResponse<ITemplateTask[]>) => (this.templatetasks = res.body || []));
    });
  }

  updateForm(templateTask: ITemplateTask): void {
    this.editForm.patchValue({
      id: templateTask.id,
      idTemplateTask: templateTask.idTemplateTask,
      ordineEsecuzione: templateTask.ordineEsecuzione,
      nome: templateTask.nome,
      note: templateTask.note,
      pubPriv: templateTask.pubPriv,
      idTemplatePraticaRef: templateTask.idTemplatePraticaRef,
      templatePraticaId: templateTask.templatePraticaId,
      templateTaskId: templateTask.templateTaskId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const templateTask = this.createFromForm();
    if (templateTask.id !== undefined) {
      this.subscribeToSaveResponse(this.templateTaskService.update(templateTask));
    } else {
      this.subscribeToSaveResponse(this.templateTaskService.create(templateTask));
    }
  }

  private createFromForm(): ITemplateTask {
    return {
      ...new TemplateTask(),
      id: this.editForm.get(['id'])!.value,
      idTemplateTask: this.editForm.get(['idTemplateTask'])!.value,
      ordineEsecuzione: this.editForm.get(['ordineEsecuzione'])!.value,
      nome: this.editForm.get(['nome'])!.value,
      note: this.editForm.get(['note'])!.value,
      pubPriv: this.editForm.get(['pubPriv'])!.value,
      idTemplatePraticaRef: this.editForm.get(['idTemplatePraticaRef'])!.value,
      templatePraticaId: this.editForm.get(['templatePraticaId'])!.value,
      templateTaskId: this.editForm.get(['templateTaskId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITemplateTask>>): void {
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
