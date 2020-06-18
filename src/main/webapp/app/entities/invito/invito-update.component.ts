import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IInvito, Invito } from 'app/shared/model/invito.model';
import { InvitoService } from './invito.service';
import { IStudioProfessionale } from 'app/shared/model/studio-professionale.model';
import { StudioProfessionaleService } from 'app/entities/studio-professionale/studio-professionale.service';
import { IAssegnazioneTask } from 'app/shared/model/assegnazione-task.model';
import { AssegnazioneTaskService } from 'app/entities/assegnazione-task/assegnazione-task.service';

type SelectableEntity = IStudioProfessionale | IAssegnazioneTask;

@Component({
  selector: 'jhi-invito-update',
  templateUrl: './invito-update.component.html',
})
export class InvitoUpdateComponent implements OnInit {
  isSaving = false;
  idstudioprofessionales: IStudioProfessionale[] = [];
  assegnazionetasks: IAssegnazioneTask[] = [];

  editForm = this.fb.group({
    id: [],
    idStudioProfessionaleRef: [null, [Validators.max(8)]],
    dataInvito: [],
    idUserInvitante: [],
    nomeUserInvitante: [],
    dataScadenzaInvito: [],
    testoInvito: [],
    idPraticaRef: [],
    idTaskRef: [],
    luogoFisico: [],
    indicazioniLuogo: [],
    dataInizio: [],
    oraInizio: [],
    dataFine: [],
    oraFine: [],
    urlStanzaVirtuale: [],
    discriminator: [],
    idStudioProfessionaleId: [],
    assegnazioneTaskId: [],
  });

  constructor(
    protected invitoService: InvitoService,
    protected studioProfessionaleService: StudioProfessionaleService,
    protected assegnazioneTaskService: AssegnazioneTaskService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ invito }) => {
      this.updateForm(invito);

      this.studioProfessionaleService
        .query({ filter: 'invito-is-null' })
        .pipe(
          map((res: HttpResponse<IStudioProfessionale[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IStudioProfessionale[]) => {
          if (!invito.idStudioProfessionaleId) {
            this.idstudioprofessionales = resBody;
          } else {
            this.studioProfessionaleService
              .find(invito.idStudioProfessionaleId)
              .pipe(
                map((subRes: HttpResponse<IStudioProfessionale>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IStudioProfessionale[]) => (this.idstudioprofessionales = concatRes));
          }
        });

      this.assegnazioneTaskService.query().subscribe((res: HttpResponse<IAssegnazioneTask[]>) => (this.assegnazionetasks = res.body || []));
    });
  }

  updateForm(invito: IInvito): void {
    this.editForm.patchValue({
      id: invito.id,
      idStudioProfessionaleRef: invito.idStudioProfessionaleRef,
      dataInvito: invito.dataInvito,
      idUserInvitante: invito.idUserInvitante,
      nomeUserInvitante: invito.nomeUserInvitante,
      dataScadenzaInvito: invito.dataScadenzaInvito,
      testoInvito: invito.testoInvito,
      idPraticaRef: invito.idPraticaRef,
      idTaskRef: invito.idTaskRef,
      luogoFisico: invito.luogoFisico,
      indicazioniLuogo: invito.indicazioniLuogo,
      dataInizio: invito.dataInizio,
      oraInizio: invito.oraInizio,
      dataFine: invito.dataFine,
      oraFine: invito.oraFine,
      urlStanzaVirtuale: invito.urlStanzaVirtuale,
      discriminator: invito.discriminator,
      idStudioProfessionaleId: invito.idStudioProfessionaleId,
      assegnazioneTaskId: invito.assegnazioneTaskId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const invito = this.createFromForm();
    if (invito.id !== undefined) {
      this.subscribeToSaveResponse(this.invitoService.update(invito));
    } else {
      this.subscribeToSaveResponse(this.invitoService.create(invito));
    }
  }

  private createFromForm(): IInvito {
    return {
      ...new Invito(),
      id: this.editForm.get(['id'])!.value,
      idStudioProfessionaleRef: this.editForm.get(['idStudioProfessionaleRef'])!.value,
      dataInvito: this.editForm.get(['dataInvito'])!.value,
      idUserInvitante: this.editForm.get(['idUserInvitante'])!.value,
      nomeUserInvitante: this.editForm.get(['nomeUserInvitante'])!.value,
      dataScadenzaInvito: this.editForm.get(['dataScadenzaInvito'])!.value,
      testoInvito: this.editForm.get(['testoInvito'])!.value,
      idPraticaRef: this.editForm.get(['idPraticaRef'])!.value,
      idTaskRef: this.editForm.get(['idTaskRef'])!.value,
      luogoFisico: this.editForm.get(['luogoFisico'])!.value,
      indicazioniLuogo: this.editForm.get(['indicazioniLuogo'])!.value,
      dataInizio: this.editForm.get(['dataInizio'])!.value,
      oraInizio: this.editForm.get(['oraInizio'])!.value,
      dataFine: this.editForm.get(['dataFine'])!.value,
      oraFine: this.editForm.get(['oraFine'])!.value,
      urlStanzaVirtuale: this.editForm.get(['urlStanzaVirtuale'])!.value,
      discriminator: this.editForm.get(['discriminator'])!.value,
      idStudioProfessionaleId: this.editForm.get(['idStudioProfessionaleId'])!.value,
      assegnazioneTaskId: this.editForm.get(['assegnazioneTaskId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IInvito>>): void {
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
