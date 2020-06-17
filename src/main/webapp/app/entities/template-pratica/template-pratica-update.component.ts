import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITemplatePratica, TemplatePratica } from 'app/shared/model/template-pratica.model';
import { TemplatePraticaService } from './template-pratica.service';

@Component({
  selector: 'jhi-template-pratica-update',
  templateUrl: './template-pratica-update.component.html',
})
export class TemplatePraticaUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    idTemplatePratica: [null, [Validators.required, Validators.max(8)]],
    nomeTemplate: [],
    elencoTagAmbito: [],
  });

  constructor(
    protected templatePraticaService: TemplatePraticaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ templatePratica }) => {
      this.updateForm(templatePratica);
    });
  }

  updateForm(templatePratica: ITemplatePratica): void {
    this.editForm.patchValue({
      id: templatePratica.id,
      idTemplatePratica: templatePratica.idTemplatePratica,
      nomeTemplate: templatePratica.nomeTemplate,
      elencoTagAmbito: templatePratica.elencoTagAmbito,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const templatePratica = this.createFromForm();
    if (templatePratica.id !== undefined) {
      this.subscribeToSaveResponse(this.templatePraticaService.update(templatePratica));
    } else {
      this.subscribeToSaveResponse(this.templatePraticaService.create(templatePratica));
    }
  }

  private createFromForm(): ITemplatePratica {
    return {
      ...new TemplatePratica(),
      id: this.editForm.get(['id'])!.value,
      idTemplatePratica: this.editForm.get(['idTemplatePratica'])!.value,
      nomeTemplate: this.editForm.get(['nomeTemplate'])!.value,
      elencoTagAmbito: this.editForm.get(['elencoTagAmbito'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITemplatePratica>>): void {
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
}
