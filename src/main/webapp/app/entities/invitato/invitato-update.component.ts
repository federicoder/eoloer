import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IInvitato, Invitato } from 'app/shared/model/invitato.model';
import { InvitatoService } from './invitato.service';
import { IUserPersona } from 'app/shared/model/user-persona.model';
import { UserPersonaService } from 'app/entities/user-persona/user-persona.service';
import { IInvito } from 'app/shared/model/invito.model';
import { InvitoService } from 'app/entities/invito/invito.service';

type SelectableEntity = IUserPersona | IInvito;

@Component({
  selector: 'jhi-invitato-update',
  templateUrl: './invitato-update.component.html',
})
export class InvitatoUpdateComponent implements OnInit {
  isSaving = false;
  userpersonas: IUserPersona[] = [];
  invitos: IInvito[] = [];

  editForm = this.fb.group({
    id: [],
    idInvitoRef: [null, [Validators.max(8)]],
    tokenInvito: [],
    canalePrimarioInvito: [],
    canaleBackupInvito: [],
    statoInvito: [],
    idUserInvitato: [],
    idPersonaInvitata: [],
    nomeUserInvitato: [],
    dataRispostaInvito: [],
    ruoloInvitato: [],
    indInvitati: [],
    userPersonaId: [],
    invitoId: [],
  });

  constructor(
    protected invitatoService: InvitatoService,
    protected userPersonaService: UserPersonaService,
    protected invitoService: InvitoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ invitato }) => {
      this.updateForm(invitato);

      this.userPersonaService.query().subscribe((res: HttpResponse<IUserPersona[]>) => (this.userpersonas = res.body || []));

      this.invitoService.query().subscribe((res: HttpResponse<IInvito[]>) => (this.invitos = res.body || []));
    });
  }

  updateForm(invitato: IInvitato): void {
    this.editForm.patchValue({
      id: invitato.id,
      idInvitoRef: invitato.idInvitoRef,
      tokenInvito: invitato.tokenInvito,
      canalePrimarioInvito: invitato.canalePrimarioInvito,
      canaleBackupInvito: invitato.canaleBackupInvito,
      statoInvito: invitato.statoInvito,
      idUserInvitato: invitato.idUserInvitato,
      idPersonaInvitata: invitato.idPersonaInvitata,
      nomeUserInvitato: invitato.nomeUserInvitato,
      dataRispostaInvito: invitato.dataRispostaInvito,
      ruoloInvitato: invitato.ruoloInvitato,
      indInvitati: invitato.indInvitati,
      userPersonaId: invitato.userPersonaId,
      invitoId: invitato.invitoId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const invitato = this.createFromForm();
    if (invitato.id !== undefined) {
      this.subscribeToSaveResponse(this.invitatoService.update(invitato));
    } else {
      this.subscribeToSaveResponse(this.invitatoService.create(invitato));
    }
  }

  private createFromForm(): IInvitato {
    return {
      ...new Invitato(),
      id: this.editForm.get(['id'])!.value,
      idInvitoRef: this.editForm.get(['idInvitoRef'])!.value,
      tokenInvito: this.editForm.get(['tokenInvito'])!.value,
      canalePrimarioInvito: this.editForm.get(['canalePrimarioInvito'])!.value,
      canaleBackupInvito: this.editForm.get(['canaleBackupInvito'])!.value,
      statoInvito: this.editForm.get(['statoInvito'])!.value,
      idUserInvitato: this.editForm.get(['idUserInvitato'])!.value,
      idPersonaInvitata: this.editForm.get(['idPersonaInvitata'])!.value,
      nomeUserInvitato: this.editForm.get(['nomeUserInvitato'])!.value,
      dataRispostaInvito: this.editForm.get(['dataRispostaInvito'])!.value,
      ruoloInvitato: this.editForm.get(['ruoloInvitato'])!.value,
      indInvitati: this.editForm.get(['indInvitati'])!.value,
      userPersonaId: this.editForm.get(['userPersonaId'])!.value,
      invitoId: this.editForm.get(['invitoId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IInvitato>>): void {
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
