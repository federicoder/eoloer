import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { ICondivisionePratica, CondivisionePratica } from 'app/shared/model/condivisione-pratica.model';
import { CondivisionePraticaService } from './condivisione-pratica.service';
import { IRappresentanzaPratica } from 'app/shared/model/rappresentanza-pratica.model';
import { RappresentanzaPraticaService } from 'app/entities/rappresentanza-pratica/rappresentanza-pratica.service';
import { IPersona } from 'app/shared/model/persona.model';
import { PersonaService } from 'app/entities/persona/persona.service';
import { IUserPersona } from 'app/shared/model/user-persona.model';
import { UserPersonaService } from 'app/entities/user-persona/user-persona.service';
import { IPratica } from 'app/shared/model/pratica.model';
import { PraticaService } from 'app/entities/pratica/pratica.service';

type SelectableEntity = IRappresentanzaPratica | IPersona | IUserPersona | IPratica;

@Component({
  selector: 'jhi-condivisione-pratica-update',
  templateUrl: './condivisione-pratica-update.component.html',
})
export class CondivisionePraticaUpdateComponent implements OnInit {
  isSaving = false;
  idruolopersonas: IRappresentanzaPratica[] = [];
  idpersonas: IPersona[] = [];
  userpersonas: IUserPersona[] = [];
  praticas: IPratica[] = [];

  editForm = this.fb.group({
    id: [],
    idUserAmmesso: [null, [Validators.max(8)]],
    ruolo: [],
    idUserConcedente: [],
    statoInvito: [],
    idPraticaRef: [],
    idRuoloPersonaId: [],
    idPersonaId: [],
    idUserPersonaId: [],
    idPraticaId: [],
  });

  constructor(
    protected condivisionePraticaService: CondivisionePraticaService,
    protected rappresentanzaPraticaService: RappresentanzaPraticaService,
    protected personaService: PersonaService,
    protected userPersonaService: UserPersonaService,
    protected praticaService: PraticaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ condivisionePratica }) => {
      this.updateForm(condivisionePratica);

      this.rappresentanzaPraticaService
        .query({ filter: 'condivisionepratica-is-null' })
        .pipe(
          map((res: HttpResponse<IRappresentanzaPratica[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IRappresentanzaPratica[]) => {
          if (!condivisionePratica.idRuoloPersonaId) {
            this.idruolopersonas = resBody;
          } else {
            this.rappresentanzaPraticaService
              .find(condivisionePratica.idRuoloPersonaId)
              .pipe(
                map((subRes: HttpResponse<IRappresentanzaPratica>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IRappresentanzaPratica[]) => (this.idruolopersonas = concatRes));
          }
        });

      this.personaService
        .query({ filter: 'condivisionepratica-is-null' })
        .pipe(
          map((res: HttpResponse<IPersona[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IPersona[]) => {
          if (!condivisionePratica.idPersonaId) {
            this.idpersonas = resBody;
          } else {
            this.personaService
              .find(condivisionePratica.idPersonaId)
              .pipe(
                map((subRes: HttpResponse<IPersona>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IPersona[]) => (this.idpersonas = concatRes));
          }
        });

      this.userPersonaService.query().subscribe((res: HttpResponse<IUserPersona[]>) => (this.userpersonas = res.body || []));

      this.praticaService.query().subscribe((res: HttpResponse<IPratica[]>) => (this.praticas = res.body || []));
    });
  }

  updateForm(condivisionePratica: ICondivisionePratica): void {
    this.editForm.patchValue({
      id: condivisionePratica.id,
      idUserAmmesso: condivisionePratica.idUserAmmesso,
      ruolo: condivisionePratica.ruolo,
      idUserConcedente: condivisionePratica.idUserConcedente,
      statoInvito: condivisionePratica.statoInvito,
      idPraticaRef: condivisionePratica.idPraticaRef,
      idRuoloPersonaId: condivisionePratica.idRuoloPersonaId,
      idPersonaId: condivisionePratica.idPersonaId,
      idUserPersonaId: condivisionePratica.idUserPersonaId,
      idPraticaId: condivisionePratica.idPraticaId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const condivisionePratica = this.createFromForm();
    if (condivisionePratica.id !== undefined) {
      this.subscribeToSaveResponse(this.condivisionePraticaService.update(condivisionePratica));
    } else {
      this.subscribeToSaveResponse(this.condivisionePraticaService.create(condivisionePratica));
    }
  }

  private createFromForm(): ICondivisionePratica {
    return {
      ...new CondivisionePratica(),
      id: this.editForm.get(['id'])!.value,
      idUserAmmesso: this.editForm.get(['idUserAmmesso'])!.value,
      ruolo: this.editForm.get(['ruolo'])!.value,
      idUserConcedente: this.editForm.get(['idUserConcedente'])!.value,
      statoInvito: this.editForm.get(['statoInvito'])!.value,
      idPraticaRef: this.editForm.get(['idPraticaRef'])!.value,
      idRuoloPersonaId: this.editForm.get(['idRuoloPersonaId'])!.value,
      idPersonaId: this.editForm.get(['idPersonaId'])!.value,
      idUserPersonaId: this.editForm.get(['idUserPersonaId'])!.value,
      idPraticaId: this.editForm.get(['idPraticaId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICondivisionePratica>>): void {
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
