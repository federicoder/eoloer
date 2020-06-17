import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IRisorseDisponibili } from 'app/shared/model/risorse-disponibili.model';
import { RisorseDisponibiliService } from './risorse-disponibili.service';
import { RisorseDisponibiliDeleteDialogComponent } from './risorse-disponibili-delete-dialog.component';

@Component({
  selector: 'jhi-risorse-disponibili',
  templateUrl: './risorse-disponibili.component.html',
})
export class RisorseDisponibiliComponent implements OnInit, OnDestroy {
  risorseDisponibilis?: IRisorseDisponibili[];
  eventSubscriber?: Subscription;
  currentSearch: string;

  constructor(
    protected risorseDisponibiliService: RisorseDisponibiliService,
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
      this.risorseDisponibiliService
        .search({
          query: this.currentSearch,
        })
        .subscribe((res: HttpResponse<IRisorseDisponibili[]>) => (this.risorseDisponibilis = res.body || []));
      return;
    }

    this.risorseDisponibiliService
      .query()
      .subscribe((res: HttpResponse<IRisorseDisponibili[]>) => (this.risorseDisponibilis = res.body || []));
  }

  search(query: string): void {
    this.currentSearch = query;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInRisorseDisponibilis();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IRisorseDisponibili): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInRisorseDisponibilis(): void {
    this.eventSubscriber = this.eventManager.subscribe('risorseDisponibiliListModification', () => this.loadAll());
  }

  delete(risorseDisponibili: IRisorseDisponibili): void {
    const modalRef = this.modalService.open(RisorseDisponibiliDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.risorseDisponibili = risorseDisponibili;
  }
}
