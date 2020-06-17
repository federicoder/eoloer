import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IProdotto, Prodotto } from 'app/shared/model/prodotto.model';
import { ProdottoService } from './prodotto.service';
import { ILineaOrdine } from 'app/shared/model/linea-ordine.model';
import { LineaOrdineService } from 'app/entities/linea-ordine/linea-ordine.service';

@Component({
  selector: 'jhi-prodotto-update',
  templateUrl: './prodotto-update.component.html',
})
export class ProdottoUpdateComponent implements OnInit {
  isSaving = false;
  ids: ILineaOrdine[] = [];

  editForm = this.fb.group({
    id: [],
    nuovaLicenza: [],
    rinnovoLicenza: [],
    storage: [],
    idId: [],
  });

  constructor(
    protected prodottoService: ProdottoService,
    protected lineaOrdineService: LineaOrdineService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ prodotto }) => {
      this.updateForm(prodotto);

      this.lineaOrdineService
        .query({ filter: 'idprodotto-is-null' })
        .pipe(
          map((res: HttpResponse<ILineaOrdine[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ILineaOrdine[]) => {
          if (!prodotto.idId) {
            this.ids = resBody;
          } else {
            this.lineaOrdineService
              .find(prodotto.idId)
              .pipe(
                map((subRes: HttpResponse<ILineaOrdine>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ILineaOrdine[]) => (this.ids = concatRes));
          }
        });
    });
  }

  updateForm(prodotto: IProdotto): void {
    this.editForm.patchValue({
      id: prodotto.id,
      nuovaLicenza: prodotto.nuovaLicenza,
      rinnovoLicenza: prodotto.rinnovoLicenza,
      storage: prodotto.storage,
      idId: prodotto.idId,
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
      idId: this.editForm.get(['idId'])!.value,
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

  trackById(index: number, item: ILineaOrdine): any {
    return item.id;
  }
}
