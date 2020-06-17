import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IProdotto } from 'app/shared/model/prodotto.model';
import { ProdottoService } from './prodotto.service';
import { ProdottoDeleteDialogComponent } from './prodotto-delete-dialog.component';

@Component({
  selector: 'jhi-prodotto',
  templateUrl: './prodotto.component.html',
})
export class ProdottoComponent implements OnInit, OnDestroy {
  prodottos?: IProdotto[];
  eventSubscriber?: Subscription;
  currentSearch: string;

  constructor(
    protected prodottoService: ProdottoService,
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
      this.prodottoService
        .search({
          query: this.currentSearch,
        })
        .subscribe((res: HttpResponse<IProdotto[]>) => (this.prodottos = res.body || []));
      return;
    }

    this.prodottoService.query().subscribe((res: HttpResponse<IProdotto[]>) => (this.prodottos = res.body || []));
  }

  search(query: string): void {
    this.currentSearch = query;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInProdottos();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IProdotto): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInProdottos(): void {
    this.eventSubscriber = this.eventManager.subscribe('prodottoListModification', () => this.loadAll());
  }

  delete(prodotto: IProdotto): void {
    const modalRef = this.modalService.open(ProdottoDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.prodotto = prodotto;
  }
}
