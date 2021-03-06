import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IInvitoPratica, InvitoPratica } from 'app/shared/model/invito-pratica.model';
import { InvitoPraticaService } from './invito-pratica.service';
import { IInvito } from 'app/shared/model/invito.model';
import { InvitoService } from 'app/entities/invito/invito.service';
import { IPratica } from 'app/shared/model/pratica.model';
import { PraticaService } from 'app/entities/pratica/pratica.service';

type SelectableEntity = IInvito | IPratica;

@Component({
  selector: 'jhi-invito-pratica-update',
  templateUrl: './invito-pratica-update.component.html',
})
export class InvitoPraticaUpdateComponent implements OnInit {
  isSaving = false;
  idpraticas: IInvito[] = [];
  praticas: IPratica[] = [];

  editForm = this.fb.group({
    id: [],
    idPraticaRef: [],
    idPraticaId: [],
    idPraticaId: [],
  });

  constructor(
    protected invitoPraticaService: InvitoPraticaService,
    protected invitoService: InvitoService,
    protected praticaService: PraticaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ invitoPratica }) => {
      this.updateForm(invitoPratica);

      this.invitoService
        .query({ filter: 'invitopratica-is-null' })
        .pipe(
          map((res: HttpResponse<IInvito[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IInvito[]) => {
          if (!invitoPratica.idPraticaId) {
            this.idpraticas = resBody;
          } else {
            this.invitoService
              .find(invitoPratica.idPraticaId)
              .pipe(
                map((subRes: HttpResponse<IInvito>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IInvito[]) => (this.idpraticas = concatRes));
          }
        });

      this.praticaService.query().subscribe((res: HttpResponse<IPratica[]>) => (this.praticas = res.body || []));
    });
  }

  updateForm(invitoPratica: IInvitoPratica): void {
    this.editForm.patchValue({
      id: invitoPratica.id,
      idPraticaRef: invitoPratica.idPraticaRef,
      idPraticaId: invitoPratica.idPraticaId,
      idPraticaId: invitoPratica.idPraticaId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const invitoPratica = this.createFromForm();
    if (invitoPratica.id !== undefined) {
      this.subscribeToSaveResponse(this.invitoPraticaService.update(invitoPratica));
    } else {
      this.subscribeToSaveResponse(this.invitoPraticaService.create(invitoPratica));
    }
  }

  private createFromForm(): IInvitoPratica {
    return {
      ...new InvitoPratica(),
      id: this.editForm.get(['id'])!.value,
      idPraticaRef: this.editForm.get(['idPraticaRef'])!.value,
      idPraticaId: this.editForm.get(['idPraticaId'])!.value,
      idPraticaId: this.editForm.get(['idPraticaId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IInvitoPratica>>): void {
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
