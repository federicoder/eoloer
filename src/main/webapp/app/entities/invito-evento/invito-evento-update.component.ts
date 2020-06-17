import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IInvitoEvento, InvitoEvento } from 'app/shared/model/invito-evento.model';
import { InvitoEventoService } from './invito-evento.service';
import { IInvito } from 'app/shared/model/invito.model';
import { InvitoService } from 'app/entities/invito/invito.service';
import { IPrevisioneEvento } from 'app/shared/model/previsione-evento.model';
import { PrevisioneEventoService } from 'app/entities/previsione-evento/previsione-evento.service';

type SelectableEntity = IInvito | IPrevisioneEvento;

@Component({
  selector: 'jhi-invito-evento-update',
  templateUrl: './invito-evento-update.component.html',
})
export class InvitoEventoUpdateComponent implements OnInit {
  isSaving = false;
  idattivitas: IInvito[] = [];
  previsioneeventos: IPrevisioneEvento[] = [];

  editForm = this.fb.group({
    id: [],
    idAttivita: [],
    luogoFisico: [],
    indicazioniLuogo: [],
    dataInizio: [],
    oraInizio: [],
    dataFine: [],
    oraFine: [],
    urlStanzaVirtuale: [],
    idAttivitaId: [],
    previsioneEventoId: [],
  });

  constructor(
    protected invitoEventoService: InvitoEventoService,
    protected invitoService: InvitoService,
    protected previsioneEventoService: PrevisioneEventoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ invitoEvento }) => {
      this.updateForm(invitoEvento);

      this.invitoService
        .query({ filter: 'id-is-null' })
        .pipe(
          map((res: HttpResponse<IInvito[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IInvito[]) => {
          if (!invitoEvento.idAttivitaId) {
            this.idattivitas = resBody;
          } else {
            this.invitoService
              .find(invitoEvento.idAttivitaId)
              .pipe(
                map((subRes: HttpResponse<IInvito>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IInvito[]) => (this.idattivitas = concatRes));
          }
        });

      this.previsioneEventoService.query().subscribe((res: HttpResponse<IPrevisioneEvento[]>) => (this.previsioneeventos = res.body || []));
    });
  }

  updateForm(invitoEvento: IInvitoEvento): void {
    this.editForm.patchValue({
      id: invitoEvento.id,
      idAttivita: invitoEvento.idAttivita,
      luogoFisico: invitoEvento.luogoFisico,
      indicazioniLuogo: invitoEvento.indicazioniLuogo,
      dataInizio: invitoEvento.dataInizio,
      oraInizio: invitoEvento.oraInizio,
      dataFine: invitoEvento.dataFine,
      oraFine: invitoEvento.oraFine,
      urlStanzaVirtuale: invitoEvento.urlStanzaVirtuale,
      idAttivitaId: invitoEvento.idAttivitaId,
      previsioneEventoId: invitoEvento.previsioneEventoId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const invitoEvento = this.createFromForm();
    if (invitoEvento.id !== undefined) {
      this.subscribeToSaveResponse(this.invitoEventoService.update(invitoEvento));
    } else {
      this.subscribeToSaveResponse(this.invitoEventoService.create(invitoEvento));
    }
  }

  private createFromForm(): IInvitoEvento {
    return {
      ...new InvitoEvento(),
      id: this.editForm.get(['id'])!.value,
      idAttivita: this.editForm.get(['idAttivita'])!.value,
      luogoFisico: this.editForm.get(['luogoFisico'])!.value,
      indicazioniLuogo: this.editForm.get(['indicazioniLuogo'])!.value,
      dataInizio: this.editForm.get(['dataInizio'])!.value,
      oraInizio: this.editForm.get(['oraInizio'])!.value,
      dataFine: this.editForm.get(['dataFine'])!.value,
      oraFine: this.editForm.get(['oraFine'])!.value,
      urlStanzaVirtuale: this.editForm.get(['urlStanzaVirtuale'])!.value,
      idAttivitaId: this.editForm.get(['idAttivitaId'])!.value,
      previsioneEventoId: this.editForm.get(['previsioneEventoId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IInvitoEvento>>): void {
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
