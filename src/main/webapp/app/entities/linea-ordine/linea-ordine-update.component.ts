import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { ILineaOrdine, LineaOrdine } from 'app/shared/model/linea-ordine.model';
import { LineaOrdineService } from './linea-ordine.service';
import { IProdotto } from 'app/shared/model/prodotto.model';
import { ProdottoService } from 'app/entities/prodotto/prodotto.service';
import { IOrdine } from 'app/shared/model/ordine.model';
import { OrdineService } from 'app/entities/ordine/ordine.service';

type SelectableEntity = IProdotto | IOrdine;

@Component({
  selector: 'jhi-linea-ordine-update',
  templateUrl: './linea-ordine-update.component.html',
})
export class LineaOrdineUpdateComponent implements OnInit {
  isSaving = false;
  idprodottos: IProdotto[] = [];
  ordines: IOrdine[] = [];

  editForm = this.fb.group({
    id: [],
    idOrdineRef: [null, [Validators.required]],
    idProdottoRef: [null, [Validators.required]],
    quantita: [],
    importo: [],
    codIva: [],
    idProdottoId: [],
    idOrdineId: [],
  });

  constructor(
    protected lineaOrdineService: LineaOrdineService,
    protected prodottoService: ProdottoService,
    protected ordineService: OrdineService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ lineaOrdine }) => {
      this.updateForm(lineaOrdine);

      this.prodottoService
        .query({ filter: 'lineaordine-is-null' })
        .pipe(
          map((res: HttpResponse<IProdotto[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IProdotto[]) => {
          if (!lineaOrdine.idProdottoId) {
            this.idprodottos = resBody;
          } else {
            this.prodottoService
              .find(lineaOrdine.idProdottoId)
              .pipe(
                map((subRes: HttpResponse<IProdotto>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IProdotto[]) => (this.idprodottos = concatRes));
          }
        });

      this.ordineService.query().subscribe((res: HttpResponse<IOrdine[]>) => (this.ordines = res.body || []));
    });
  }

  updateForm(lineaOrdine: ILineaOrdine): void {
    this.editForm.patchValue({
      id: lineaOrdine.id,
      idOrdineRef: lineaOrdine.idOrdineRef,
      idProdottoRef: lineaOrdine.idProdottoRef,
      quantita: lineaOrdine.quantita,
      importo: lineaOrdine.importo,
      codIva: lineaOrdine.codIva,
      idProdottoId: lineaOrdine.idProdottoId,
      idOrdineId: lineaOrdine.idOrdineId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const lineaOrdine = this.createFromForm();
    if (lineaOrdine.id !== undefined) {
      this.subscribeToSaveResponse(this.lineaOrdineService.update(lineaOrdine));
    } else {
      this.subscribeToSaveResponse(this.lineaOrdineService.create(lineaOrdine));
    }
  }

  private createFromForm(): ILineaOrdine {
    return {
      ...new LineaOrdine(),
      id: this.editForm.get(['id'])!.value,
      idOrdineRef: this.editForm.get(['idOrdineRef'])!.value,
      idProdottoRef: this.editForm.get(['idProdottoRef'])!.value,
      quantita: this.editForm.get(['quantita'])!.value,
      importo: this.editForm.get(['importo'])!.value,
      codIva: this.editForm.get(['codIva'])!.value,
      idProdottoId: this.editForm.get(['idProdottoId'])!.value,
      idOrdineId: this.editForm.get(['idOrdineId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILineaOrdine>>): void {
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
