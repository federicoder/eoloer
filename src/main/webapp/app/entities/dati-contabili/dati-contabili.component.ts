import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IDatiContabili } from 'app/shared/model/dati-contabili.model';
import { DatiContabiliService } from './dati-contabili.service';
import { DatiContabiliDeleteDialogComponent } from './dati-contabili-delete-dialog.component';

@Component({
  selector: 'jhi-dati-contabili',
  templateUrl: './dati-contabili.component.html',
})
export class DatiContabiliComponent implements OnInit, OnDestroy {
  datiContabilis?: IDatiContabili[];
  eventSubscriber?: Subscription;
  currentSearch: string;

  constructor(
    protected datiContabiliService: DatiContabiliService,
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
      this.datiContabiliService
        .search({
          query: this.currentSearch,
        })
        .subscribe((res: HttpResponse<IDatiContabili[]>) => (this.datiContabilis = res.body || []));
      return;
    }

    this.datiContabiliService.query().subscribe((res: HttpResponse<IDatiContabili[]>) => (this.datiContabilis = res.body || []));
  }

  search(query: string): void {
    this.currentSearch = query;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInDatiContabilis();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IDatiContabili): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInDatiContabilis(): void {
    this.eventSubscriber = this.eventManager.subscribe('datiContabiliListModification', () => this.loadAll());
  }

  delete(datiContabili: IDatiContabili): void {
    const modalRef = this.modalService.open(DatiContabiliDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.datiContabili = datiContabili;
  }
}
