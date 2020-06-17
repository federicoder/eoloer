import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IRappresentanzaPratica, RappresentanzaPratica } from 'app/shared/model/rappresentanza-pratica.model';
import { RappresentanzaPraticaService } from './rappresentanza-pratica.service';
import { IPersona } from 'app/shared/model/persona.model';
import { PersonaService } from 'app/entities/persona/persona.service';

@Component({
  selector: 'jhi-rappresentanza-pratica-update',
  templateUrl: './rappresentanza-pratica-update.component.html',
})
export class RappresentanzaPraticaUpdateComponent implements OnInit {
  isSaving = false;
  personas: IPersona[] = [];

  editForm = this.fb.group({
    id: [],
    idRuoloPersona: [null, [Validators.required]],
    idPersonaRef: [null, [Validators.required]],
    ruoli: [],
    idPersonaRefId: [],
  });

  constructor(
    protected rappresentanzaPraticaService: RappresentanzaPraticaService,
    protected personaService: PersonaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ rappresentanzaPratica }) => {
      this.updateForm(rappresentanzaPratica);

      this.personaService.query().subscribe((res: HttpResponse<IPersona[]>) => (this.personas = res.body || []));
    });
  }

  updateForm(rappresentanzaPratica: IRappresentanzaPratica): void {
    this.editForm.patchValue({
      id: rappresentanzaPratica.id,
      idRuoloPersona: rappresentanzaPratica.idRuoloPersona,
      idPersonaRef: rappresentanzaPratica.idPersonaRef,
      ruoli: rappresentanzaPratica.ruoli,
      idPersonaRefId: rappresentanzaPratica.idPersonaRefId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const rappresentanzaPratica = this.createFromForm();
    if (rappresentanzaPratica.id !== undefined) {
      this.subscribeToSaveResponse(this.rappresentanzaPraticaService.update(rappresentanzaPratica));
    } else {
      this.subscribeToSaveResponse(this.rappresentanzaPraticaService.create(rappresentanzaPratica));
    }
  }

  private createFromForm(): IRappresentanzaPratica {
    return {
      ...new RappresentanzaPratica(),
      id: this.editForm.get(['id'])!.value,
      idRuoloPersona: this.editForm.get(['idRuoloPersona'])!.value,
      idPersonaRef: this.editForm.get(['idPersonaRef'])!.value,
      ruoli: this.editForm.get(['ruoli'])!.value,
      idPersonaRefId: this.editForm.get(['idPersonaRefId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRappresentanzaPratica>>): void {
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

  trackById(index: number, item: IPersona): any {
    return item.id;
  }
}
