import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IEmailPersona, EmailPersona } from 'app/shared/model/email-persona.model';
import { EmailPersonaService } from './email-persona.service';
import { IPersona } from 'app/shared/model/persona.model';
import { PersonaService } from 'app/entities/persona/persona.service';

@Component({
  selector: 'jhi-email-persona-update',
  templateUrl: './email-persona-update.component.html',
})
export class EmailPersonaUpdateComponent implements OnInit {
  isSaving = false;
  personas: IPersona[] = [];

  editForm = this.fb.group({
    id: [],
    idPersona: [null, [Validators.required]],
    etichetta: [],
    numero: [],
    personaId: [],
  });

  constructor(
    protected emailPersonaService: EmailPersonaService,
    protected personaService: PersonaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ emailPersona }) => {
      this.updateForm(emailPersona);

      this.personaService.query().subscribe((res: HttpResponse<IPersona[]>) => (this.personas = res.body || []));
    });
  }

  updateForm(emailPersona: IEmailPersona): void {
    this.editForm.patchValue({
      id: emailPersona.id,
      idPersona: emailPersona.idPersona,
      etichetta: emailPersona.etichetta,
      numero: emailPersona.numero,
      personaId: emailPersona.personaId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const emailPersona = this.createFromForm();
    if (emailPersona.id !== undefined) {
      this.subscribeToSaveResponse(this.emailPersonaService.update(emailPersona));
    } else {
      this.subscribeToSaveResponse(this.emailPersonaService.create(emailPersona));
    }
  }

  private createFromForm(): IEmailPersona {
    return {
      ...new EmailPersona(),
      id: this.editForm.get(['id'])!.value,
      idPersona: this.editForm.get(['idPersona'])!.value,
      etichetta: this.editForm.get(['etichetta'])!.value,
      numero: this.editForm.get(['numero'])!.value,
      personaId: this.editForm.get(['personaId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEmailPersona>>): void {
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
