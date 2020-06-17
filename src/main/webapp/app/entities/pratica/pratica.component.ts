import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IPratica } from 'app/shared/model/pratica.model';
import { PraticaService } from './pratica.service';
import { PraticaDeleteDialogComponent } from './pratica-delete-dialog.component';

@Component({
  selector: 'jhi-pratica',
  templateUrl: './pratica.component.html',
})
export class PraticaComponent implements OnInit, OnDestroy {
  praticas?: IPratica[];
  eventSubscriber?: Subscription;
  currentSearch: string;

  constructor(
    protected praticaService: PraticaService,
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
      this.praticaService
        .search({
          query: this.currentSearch,
        })
        .subscribe((res: HttpResponse<IPratica[]>) => (this.praticas = res.body || []));
      return;
    }

    this.praticaService.query().subscribe((res: HttpResponse<IPratica[]>) => (this.praticas = res.body || []));
  }

  search(query: string): void {
    this.currentSearch = query;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInPraticas();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IPratica): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInPraticas(): void {
    this.eventSubscriber = this.eventManager.subscribe('praticaListModification', () => this.loadAll());
  }

  delete(pratica: IPratica): void {
    const modalRef = this.modalService.open(PraticaDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.pratica = pratica;
  }
}
