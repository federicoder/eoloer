import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IRuoloOrganizzazione, RuoloOrganizzazione } from 'app/shared/model/ruolo-organizzazione.model';
import { RuoloOrganizzazioneService } from './ruolo-organizzazione.service';
import { IOrganizzazione } from 'app/shared/model/organizzazione.model';
import { OrganizzazioneService } from 'app/entities/organizzazione/organizzazione.service';
import { IPersonaFisica } from 'app/shared/model/persona-fisica.model';
import { PersonaFisicaService } from 'app/entities/persona-fisica/persona-fisica.service';

type SelectableEntity = IOrganizzazione | IPersonaFisica;

@Component({
  selector: 'jhi-ruolo-organizzazione-update',
  templateUrl: './ruolo-organizzazione-update.component.html',
})
export class RuoloOrganizzazioneUpdateComponent implements OnInit {
  isSaving = false;
  idorganizzaziones: IOrganizzazione[] = [];
  idpersonafisicas: IPersonaFisica[] = [];

  editForm = this.fb.group({
    id: [],
    ruoloInOrg: [],
    idOrganizzazioneId: [],
    idPersonaFisicaId: [],
  });

  constructor(
    protected ruoloOrganizzazioneService: RuoloOrganizzazioneService,
    protected organizzazioneService: OrganizzazioneService,
    protected personaFisicaService: PersonaFisicaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ruoloOrganizzazione }) => {
      this.updateForm(ruoloOrganizzazione);

      this.organizzazioneService
        .query({ filter: 'ruoloorganizzazione-is-null' })
        .pipe(
          map((res: HttpResponse<IOrganizzazione[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IOrganizzazione[]) => {
          if (!ruoloOrganizzazione.idOrganizzazioneId) {
            this.idorganizzaziones = resBody;
          } else {
            this.organizzazioneService
              .find(ruoloOrganizzazione.idOrganizzazioneId)
              .pipe(
                map((subRes: HttpResponse<IOrganizzazione>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IOrganizzazione[]) => (this.idorganizzaziones = concatRes));
          }
        });

      this.personaFisicaService
        .query({ filter: 'ruoloorganizzazione-is-null' })
        .pipe(
          map((res: HttpResponse<IPersonaFisica[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IPersonaFisica[]) => {
          if (!ruoloOrganizzazione.idPersonaFisicaId) {
            this.idpersonafisicas = resBody;
          } else {
            this.personaFisicaService
              .find(ruoloOrganizzazione.idPersonaFisicaId)
              .pipe(
                map((subRes: HttpResponse<IPersonaFisica>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IPersonaFisica[]) => (this.idpersonafisicas = concatRes));
          }
        });
    });
  }

  updateForm(ruoloOrganizzazione: IRuoloOrganizzazione): void {
    this.editForm.patchValue({
      id: ruoloOrganizzazione.id,
      ruoloInOrg: ruoloOrganizzazione.ruoloInOrg,
      idOrganizzazioneId: ruoloOrganizzazione.idOrganizzazioneId,
      idPersonaFisicaId: ruoloOrganizzazione.idPersonaFisicaId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const ruoloOrganizzazione = this.createFromForm();
    if (ruoloOrganizzazione.id !== undefined) {
      this.subscribeToSaveResponse(this.ruoloOrganizzazioneService.update(ruoloOrganizzazione));
    } else {
      this.subscribeToSaveResponse(this.ruoloOrganizzazioneService.create(ruoloOrganizzazione));
    }
  }

  private createFromForm(): IRuoloOrganizzazione {
    return {
      ...new RuoloOrganizzazione(),
      id: this.editForm.get(['id'])!.value,
      ruoloInOrg: this.editForm.get(['ruoloInOrg'])!.value,
      idOrganizzazioneId: this.editForm.get(['idOrganizzazioneId'])!.value,
      idPersonaFisicaId: this.editForm.get(['idPersonaFisicaId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRuoloOrganizzazione>>): void {
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
