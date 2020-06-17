import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ILineaOrdine, LineaOrdine } from 'app/shared/model/linea-ordine.model';
import { LineaOrdineService } from './linea-ordine.service';
import { IOrdine } from 'app/shared/model/ordine.model';
import { OrdineService } from 'app/entities/ordine/ordine.service';

@Component({
  selector: 'jhi-linea-ordine-update',
  templateUrl: './linea-ordine-update.component.html',
})
export class LineaOrdineUpdateComponent implements OnInit {
  isSaving = false;
  ordines: IOrdine[] = [];

  editForm = this.fb.group({
    id: [],
    idOrdine: [null, [Validators.required]],
    idProdotto: [null, [Validators.required]],
    quantita: [],
    importo: [],
    codIva: [],
    ordineId: [],
  });

  constructor(
    protected lineaOrdineService: LineaOrdineService,
    protected ordineService: OrdineService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ lineaOrdine }) => {
      this.updateForm(lineaOrdine);

      this.ordineService.query().subscribe((res: HttpResponse<IOrdine[]>) => (this.ordines = res.body || []));
    });
  }

  updateForm(lineaOrdine: ILineaOrdine): void {
    this.editForm.patchValue({
      id: lineaOrdine.id,
      idOrdine: lineaOrdine.idOrdine,
      idProdotto: lineaOrdine.idProdotto,
      quantita: lineaOrdine.quantita,
      importo: lineaOrdine.importo,
      codIva: lineaOrdine.codIva,
      ordineId: lineaOrdine.ordineId,
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
      idOrdine: this.editForm.get(['idOrdine'])!.value,
      idProdotto: this.editForm.get(['idProdotto'])!.value,
      quantita: this.editForm.get(['quantita'])!.value,
      importo: this.editForm.get(['importo'])!.value,
      codIva: this.editForm.get(['codIva'])!.value,
      ordineId: this.editForm.get(['ordineId'])!.value,
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

  trackById(index: number, item: IOrdine): any {
    return item.id;
  }
}
