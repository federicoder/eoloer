import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IProdotto, Prodotto } from 'app/shared/model/prodotto.model';
import { ProdottoService } from './prodotto.service';

@Component({
  selector: 'jhi-prodotto-update',
  templateUrl: './prodotto-update.component.html',
})
export class ProdottoUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nuovaLicenza: [],
    rinnovoLicenza: [],
    storage: [],
  });

  constructor(protected prodottoService: ProdottoService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ prodotto }) => {
      this.updateForm(prodotto);
    });
  }

  updateForm(prodotto: IProdotto): void {
    this.editForm.patchValue({
      id: prodotto.id,
      nuovaLicenza: prodotto.nuovaLicenza,
      rinnovoLicenza: prodotto.rinnovoLicenza,
      storage: prodotto.storage,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const prodotto = this.createFromForm();
    if (prodotto.id !== undefined) {
      this.subscribeToSaveResponse(this.prodottoService.update(prodotto));
    } else {
      this.subscribeToSaveResponse(this.prodottoService.create(prodotto));
    }
  }

  private createFromForm(): IProdotto {
    return {
      ...new Prodotto(),
      id: this.editForm.get(['id'])!.value,
      nuovaLicenza: this.editForm.get(['nuovaLicenza'])!.value,
      rinnovoLicenza: this.editForm.get(['rinnovoLicenza'])!.value,
      storage: this.editForm.get(['storage'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProdotto>>): void {
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
}
