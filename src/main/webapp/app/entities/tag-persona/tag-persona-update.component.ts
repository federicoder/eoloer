import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITagPersona, TagPersona } from 'app/shared/model/tag-persona.model';
import { TagPersonaService } from './tag-persona.service';
import { IPersona } from 'app/shared/model/persona.model';
import { PersonaService } from 'app/entities/persona/persona.service';

@Component({
  selector: 'jhi-tag-persona-update',
  templateUrl: './tag-persona-update.component.html',
})
export class TagPersonaUpdateComponent implements OnInit {
  isSaving = false;
  personas: IPersona[] = [];

  editForm = this.fb.group({
    id: [],
    idPersonaRef: [null, [Validators.required]],
    tag: [],
    idPersonaRefId: [],
  });

  constructor(
    protected tagPersonaService: TagPersonaService,
    protected personaService: PersonaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tagPersona }) => {
      this.updateForm(tagPersona);

      this.personaService.query().subscribe((res: HttpResponse<IPersona[]>) => (this.personas = res.body || []));
    });
  }

  updateForm(tagPersona: ITagPersona): void {
    this.editForm.patchValue({
      id: tagPersona.id,
      idPersonaRef: tagPersona.idPersonaRef,
      tag: tagPersona.tag,
      idPersonaRefId: tagPersona.idPersonaRefId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tagPersona = this.createFromForm();
    if (tagPersona.id !== undefined) {
      this.subscribeToSaveResponse(this.tagPersonaService.update(tagPersona));
    } else {
      this.subscribeToSaveResponse(this.tagPersonaService.create(tagPersona));
    }
  }

  private createFromForm(): ITagPersona {
    return {
      ...new TagPersona(),
      id: this.editForm.get(['id'])!.value,
      idPersonaRef: this.editForm.get(['idPersonaRef'])!.value,
      tag: this.editForm.get(['tag'])!.value,
      idPersonaRefId: this.editForm.get(['idPersonaRefId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITagPersona>>): void {
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
