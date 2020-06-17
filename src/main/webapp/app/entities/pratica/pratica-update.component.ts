import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPratica, Pratica } from 'app/shared/model/pratica.model';
import { PraticaService } from './pratica.service';
import { ITemplatePratica } from 'app/shared/model/template-pratica.model';
import { TemplatePraticaService } from 'app/entities/template-pratica/template-pratica.service';

@Component({
  selector: 'jhi-pratica-update',
  templateUrl: './pratica-update.component.html',
})
export class PraticaUpdateComponent implements OnInit {
  isSaving = false;
  templatepraticas: ITemplatePratica[] = [];

  editForm = this.fb.group({
    id: [],
    idStudioProfessionaleRef: [null, [Validators.required]],
    numero: [],
    nome: [],
    dataApertura: [],
    dataChiusura: [],
    dataScadenza: [],
    stato: [],
    motivoChiusura: [],
    idTitolare: [null, [Validators.max(8)]],
    prcAvanzato: [],
    version: [],
    valuta: [],
    idTemplatePraticaRef: [],
    idTemplatePraticaRefId: [],
  });

  constructor(
    protected praticaService: PraticaService,
    protected templatePraticaService: TemplatePraticaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ pratica }) => {
      this.updateForm(pratica);

      this.templatePraticaService.query().subscribe((res: HttpResponse<ITemplatePratica[]>) => (this.templatepraticas = res.body || []));
    });
  }

  updateForm(pratica: IPratica): void {
    this.editForm.patchValue({
      id: pratica.id,
      idStudioProfessionaleRef: pratica.idStudioProfessionaleRef,
      numero: pratica.numero,
      nome: pratica.nome,
      dataApertura: pratica.dataApertura,
      dataChiusura: pratica.dataChiusura,
      dataScadenza: pratica.dataScadenza,
      stato: pratica.stato,
      motivoChiusura: pratica.motivoChiusura,
      idTitolare: pratica.idTitolare,
      prcAvanzato: pratica.prcAvanzato,
      version: pratica.version,
      valuta: pratica.valuta,
      idTemplatePraticaRef: pratica.idTemplatePraticaRef,
      idTemplatePraticaRefId: pratica.idTemplatePraticaRefId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const pratica = this.createFromForm();
    if (pratica.id !== undefined) {
      this.subscribeToSaveResponse(this.praticaService.update(pratica));
    } else {
      this.subscribeToSaveResponse(this.praticaService.create(pratica));
    }
  }

  private createFromForm(): IPratica {
    return {
      ...new Pratica(),
      id: this.editForm.get(['id'])!.value,
      idStudioProfessionaleRef: this.editForm.get(['idStudioProfessionaleRef'])!.value,
      numero: this.editForm.get(['numero'])!.value,
      nome: this.editForm.get(['nome'])!.value,
      dataApertura: this.editForm.get(['dataApertura'])!.value,
      dataChiusura: this.editForm.get(['dataChiusura'])!.value,
      dataScadenza: this.editForm.get(['dataScadenza'])!.value,
      stato: this.editForm.get(['stato'])!.value,
      motivoChiusura: this.editForm.get(['motivoChiusura'])!.value,
      idTitolare: this.editForm.get(['idTitolare'])!.value,
      prcAvanzato: this.editForm.get(['prcAvanzato'])!.value,
      version: this.editForm.get(['version'])!.value,
      valuta: this.editForm.get(['valuta'])!.value,
      idTemplatePraticaRef: this.editForm.get(['idTemplatePraticaRef'])!.value,
      idTemplatePraticaRefId: this.editForm.get(['idTemplatePraticaRefId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPratica>>): void {
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

  trackById(index: number, item: ITemplatePratica): any {
    return item.id;
  }
}
