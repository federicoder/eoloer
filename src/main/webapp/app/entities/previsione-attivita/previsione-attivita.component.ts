import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IPrevisioneAttivita } from 'app/shared/model/previsione-attivita.model';
import { PrevisioneAttivitaService } from './previsione-attivita.service';
import { PrevisioneAttivitaDeleteDialogComponent } from './previsione-attivita-delete-dialog.component';

@Component({
  selector: 'jhi-previsione-attivita',
  templateUrl: './previsione-attivita.component.html',
})
export class PrevisioneAttivitaComponent implements OnInit, OnDestroy {
  previsioneAttivitas?: IPrevisioneAttivita[];
  eventSubscriber?: Subscription;
  currentSearch: string;

  constructor(
    protected previsioneAttivitaService: PrevisioneAttivitaService,
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
      this.previsioneAttivitaService
        .search({
          query: this.currentSearch,
        })
        .subscribe((res: HttpResponse<IPrevisioneAttivita[]>) => (this.previsioneAttivitas = res.body || []));
      return;
    }

    this.previsioneAttivitaService
      .query()
      .subscribe((res: HttpResponse<IPrevisioneAttivita[]>) => (this.previsioneAttivitas = res.body || []));
  }

  search(query: string): void {
    this.currentSearch = query;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInPrevisioneAttivitas();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IPrevisioneAttivita): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInPrevisioneAttivitas(): void {
    this.eventSubscriber = this.eventManager.subscribe('previsioneAttivitaListModification', () => this.loadAll());
  }

  delete(previsioneAttivita: IPrevisioneAttivita): void {
    const modalRef = this.modalService.open(PrevisioneAttivitaDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.previsioneAttivita = previsioneAttivita;
  }
}
