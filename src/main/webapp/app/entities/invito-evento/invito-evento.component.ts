import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IInvitoEvento } from 'app/shared/model/invito-evento.model';
import { InvitoEventoService } from './invito-evento.service';
import { InvitoEventoDeleteDialogComponent } from './invito-evento-delete-dialog.component';

@Component({
  selector: 'jhi-invito-evento',
  templateUrl: './invito-evento.component.html',
})
export class InvitoEventoComponent implements OnInit, OnDestroy {
  invitoEventos?: IInvitoEvento[];
  eventSubscriber?: Subscription;
  currentSearch: string;

  constructor(
    protected invitoEventoService: InvitoEventoService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected activatedRoute: ActivatedRoute
  ) {
    this.currentSearch =
      this.activatedRoute.snapshot && this.activatedRoute.snapshot.queryParams['search']
        ? this.activatedRoute.snapshot.queryParams['search']
        : '';
  }

  loadAll(): void {
    if (this.currentSearch) {
      this.invitoEventoService
        .search({
          query: this.currentSearch,
        })
        .subscribe((res: HttpResponse<IInvitoEvento[]>) => (this.invitoEventos = res.body || []));
      return;
    }

    this.invitoEventoService.query().subscribe((res: HttpResponse<IInvitoEvento[]>) => (this.invitoEventos = res.body || []));
  }

  search(query: string): void {
    this.currentSearch = query;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInInvitoEventos();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IInvitoEvento): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInInvitoEventos(): void {
    this.eventSubscriber = this.eventManager.subscribe('invitoEventoListModification', () => this.loadAll());
  }

  delete(invitoEvento: IInvitoEvento): void {
    const modalRef = this.modalService.open(InvitoEventoDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.invitoEvento = invitoEvento;
  }
}
