import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPrevisioneTask, PrevisioneTask } from 'app/shared/model/previsione-task.model';
import { PrevisioneTaskService } from './previsione-task.service';

@Component({
  selector: 'jhi-previsione-task-update',
  templateUrl: './previsione-task-update.component.html',
})
export class PrevisioneTaskUpdateComponent implements OnInit {
  isSaving = false;
  previsionetasks: IPrevisioneTask[] = [];

  editForm = this.fb.group({
    id: [],
    idTaskRef: [null, [Validators.required, Validators.max(8)]],
    qntOrdine: [null, [Validators.max(8)]],
    prcPrevisione: [null, [Validators.max(1)]],
    checkList: [],
    idTaskMilestone: [],
    tipoTask: [],
    version: [],
    previsioneTaskId: [],
  });

  constructor(protected previsioneTaskService: PrevisioneTaskService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ previsioneTask }) => {
      this.updateForm(previsioneTask);

      this.previsioneTaskService.query().subscribe((res: HttpResponse<IPrevisioneTask[]>) => (this.previsionetasks = res.body || []));
    });
  }

  updateForm(previsioneTask: IPrevisioneTask): void {
    this.editForm.patchValue({
      id: previsioneTask.id,
      idTaskRef: previsioneTask.idTaskRef,
      qntOrdine: previsioneTask.qntOrdine,
      prcPrevisione: previsioneTask.prcPrevisione,
      checkList: previsioneTask.checkList,
      idTaskMilestone: previsioneTask.idTaskMilestone,
      tipoTask: previsioneTask.tipoTask,
      version: previsioneTask.version,
      previsioneTaskId: previsioneTask.previsioneTaskId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const previsioneTask = this.createFromForm();
    if (previsioneTask.id !== undefined) {
      this.subscribeToSaveResponse(this.previsioneTaskService.update(previsioneTask));
    } else {
      this.subscribeToSaveResponse(this.previsioneTaskService.create(previsioneTask));
    }
  }

  private createFromForm(): IPrevisioneTask {
    return {
      ...new PrevisioneTask(),
      id: this.editForm.get(['id'])!.value,
      idTaskRef: this.editForm.get(['idTaskRef'])!.value,
      qntOrdine: this.editForm.get(['qntOrdine'])!.value,
      prcPrevisione: this.editForm.get(['prcPrevisione'])!.value,
      checkList: this.editForm.get(['checkList'])!.value,
      idTaskMilestone: this.editForm.get(['idTaskMilestone'])!.value,
      tipoTask: this.editForm.get(['tipoTask'])!.value,
      version: this.editForm.get(['version'])!.value,
      previsioneTaskId: this.editForm.get(['previsioneTaskId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPrevisioneTask>>): void {
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
