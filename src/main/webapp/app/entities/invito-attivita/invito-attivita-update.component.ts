import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IInvitoAttivita, InvitoAttivita } from 'app/shared/model/invito-attivita.model';
import { InvitoAttivitaService } from './invito-attivita.service';
import { IInvito } from 'app/shared/model/invito.model';
import { InvitoService } from 'app/entities/invito/invito.service';

@Component({
  selector: 'jhi-invito-attivita-update',
  templateUrl: './invito-attivita-update.component.html',
})
export class InvitoAttivitaUpdateComponent implements OnInit {
  isSaving = false;
  idattivitas: IInvito[] = [];

  editForm = this.fb.group({
    id: [],
    idAttivita: [],
    idAttivitaId: [],
  });

  constructor(
    protected invitoAttivitaService: InvitoAttivitaService,
    protected invitoService: InvitoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ invitoAttivita }) => {
      this.updateForm(invitoAttivita);

      this.invitoService
        .query({ filter: 'id-is-null' })
        .pipe(
          map((res: HttpResponse<IInvito[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IInvito[]) => {
          if (!invitoAttivita.idAttivitaId) {
            this.idattivitas = resBody;
          } else {
            this.invitoService
              .find(invitoAttivita.idAttivitaId)
              .pipe(
                map((subRes: HttpResponse<IInvito>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IInvito[]) => (this.idattivitas = concatRes));
          }
        });
    });
  }

  updateForm(invitoAttivita: IInvitoAttivita): void {
    this.editForm.patchValue({
      id: invitoAttivita.id,
      idAttivita: invitoAttivita.idAttivita,
      idAttivitaId: invitoAttivita.idAttivitaId,
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
      idAttivita: this.editForm.get(['idAttivita'])!.value,
      idAttivitaId: this.editForm.get(['idAttivitaId'])!.value,
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

  trackById(index: number, item: IInvito): any {
    return item.id;
  }
}
