import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IPrevisioneEvento } from 'app/shared/model/previsione-evento.model';
import { PrevisioneEventoService } from './previsione-evento.service';
import { PrevisioneEventoDeleteDialogComponent } from './previsione-evento-delete-dialog.component';

@Component({
  selector: 'jhi-previsione-evento',
  templateUrl: './previsione-evento.component.html',
})
export class PrevisioneEventoComponent implements OnInit, OnDestroy {
  previsioneEventos?: IPrevisioneEvento[];
  eventSubscriber?: Subscription;
  currentSearch: string;

  constructor(
    protected previsioneEventoService: PrevisioneEventoService,
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
      this.previsioneEventoService
        .search({
          query: this.currentSearch,
        })
        .subscribe((res: HttpResponse<IPrevisioneEvento[]>) => (this.previsioneEventos = res.body || []));
      return;
    }

    this.previsioneEventoService.query().subscribe((res: HttpResponse<IPrevisioneEvento[]>) => (this.previsioneEventos = res.body || []));
  }

  search(query: string): void {
    this.currentSearch = query;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInPrevisioneEventos();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IPrevisioneEvento): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInPrevisioneEventos(): void {
    this.eventSubscriber = this.eventManager.subscribe('previsioneEventoListModification', () => this.loadAll());
  }

  delete(previsioneEvento: IPrevisioneEvento): void {
    const modalRef = this.modalService.open(PrevisioneEventoDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.previsioneEvento = previsioneEvento;
  }
}
