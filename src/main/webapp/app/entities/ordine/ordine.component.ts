import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IOrdine } from 'app/shared/model/ordine.model';
import { OrdineService } from './ordine.service';
import { OrdineDeleteDialogComponent } from './ordine-delete-dialog.component';

@Component({
  selector: 'jhi-ordine',
  templateUrl: './ordine.component.html',
})
export class OrdineComponent implements OnInit, OnDestroy {
  ordines?: IOrdine[];
  eventSubscriber?: Subscription;
  currentSearch: string;

  constructor(
    protected ordineService: OrdineService,
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
      this.ordineService
        .search({
          query: this.currentSearch,
        })
        .subscribe((res: HttpResponse<IOrdine[]>) => (this.ordines = res.body || []));
      return;
    }

    this.ordineService.query().subscribe((res: HttpResponse<IOrdine[]>) => (this.ordines = res.body || []));
  }

  search(query: string): void {
    this.currentSearch = query;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInOrdines();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IOrdine): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInOrdines(): void {
    this.eventSubscriber = this.eventManager.subscribe('ordineListModification', () => this.loadAll());
  }

  delete(ordine: IOrdine): void {
    const modalRef = this.modalService.open(OrdineDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.ordine = ordine;
  }
}
