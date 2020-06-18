import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { INotaPratica, NotaPratica } from 'app/shared/model/nota-pratica.model';
import { NotaPraticaService } from './nota-pratica.service';
import { IPratica } from 'app/shared/model/pratica.model';
import { PraticaService } from 'app/entities/pratica/pratica.service';

@Component({
  selector: 'jhi-nota-pratica-update',
  templateUrl: './nota-pratica-update.component.html',
})
export class NotaPraticaUpdateComponent implements OnInit {
  isSaving = false;
  praticas: IPratica[] = [];

  editForm = this.fb.group({
    id: [],
    idPraticaRef: [null, [Validators.max(8)]],
    data: [],
    nota: [],
    version: [],
    idPraticaId: [],
  });

  constructor(
    protected notaPraticaService: NotaPraticaService,
    protected praticaService: PraticaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ notaPratica }) => {
      this.updateForm(notaPratica);

      this.praticaService.query().subscribe((res: HttpResponse<IPratica[]>) => (this.praticas = res.body || []));
    });
  }

  updateForm(notaPratica: INotaPratica): void {
    this.editForm.patchValue({
      id: notaPratica.id,
      idPraticaRef: notaPratica.idPraticaRef,
      data: notaPratica.data,
      nota: notaPratica.nota,
      version: notaPratica.version,
      idPraticaId: notaPratica.idPraticaId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const notaPratica = this.createFromForm();
    if (notaPratica.id !== undefined) {
      this.subscribeToSaveResponse(this.notaPraticaService.update(notaPratica));
    } else {
      this.subscribeToSaveResponse(this.notaPraticaService.create(notaPratica));
    }
  }

  private createFromForm(): INotaPratica {
    return {
      ...new NotaPratica(),
      id: this.editForm.get(['id'])!.value,
      idPraticaRef: this.editForm.get(['idPraticaRef'])!.value,
      data: this.editForm.get(['data'])!.value,
      nota: this.editForm.get(['nota'])!.value,
      version: this.editForm.get(['version'])!.value,
      idPraticaId: this.editForm.get(['idPraticaId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<INotaPratica>>): void {
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

  trackById(index: number, item: IPratica): any {
    return item.id;
  }
}
