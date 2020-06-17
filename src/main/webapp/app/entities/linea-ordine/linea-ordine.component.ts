import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ILineaOrdine } from 'app/shared/model/linea-ordine.model';
import { LineaOrdineService } from './linea-ordine.service';
import { LineaOrdineDeleteDialogComponent } from './linea-ordine-delete-dialog.component';

@Component({
  selector: 'jhi-linea-ordine',
  templateUrl: './linea-ordine.component.html',
})
export class LineaOrdineComponent implements OnInit, OnDestroy {
  lineaOrdines?: ILineaOrdine[];
  eventSubscriber?: Subscription;
  currentSearch: string;

  constructor(
    protected lineaOrdineService: LineaOrdineService,
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
      this.lineaOrdineService
        .search({
          query: this.currentSearch,
        })
        .subscribe((res: HttpResponse<ILineaOrdine[]>) => (this.lineaOrdines = res.body || []));
      return;
    }

    this.lineaOrdineService.query().subscribe((res: HttpResponse<ILineaOrdine[]>) => (this.lineaOrdines = res.body || []));
  }

  search(query: string): void {
    this.currentSearch = query;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInLineaOrdines();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ILineaOrdine): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInLineaOrdines(): void {
    this.eventSubscriber = this.eventManager.subscribe('lineaOrdineListModification', () => this.loadAll());
  }

  delete(lineaOrdine: ILineaOrdine): void {
    const modalRef = this.modalService.open(LineaOrdineDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.lineaOrdine = lineaOrdine;
  }
}
