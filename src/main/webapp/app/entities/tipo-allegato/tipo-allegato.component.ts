import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITipoAllegato } from 'app/shared/model/tipo-allegato.model';
import { TipoAllegatoService } from './tipo-allegato.service';
import { TipoAllegatoDeleteDialogComponent } from './tipo-allegato-delete-dialog.component';

@Component({
  selector: 'jhi-tipo-allegato',
  templateUrl: './tipo-allegato.component.html',
})
export class TipoAllegatoComponent implements OnInit, OnDestroy {
  tipoAllegatoes?: ITipoAllegato[];
  eventSubscriber?: Subscription;
  currentSearch: string;

  constructor(
    protected tipoAllegatoService: TipoAllegatoService,
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
      this.tipoAllegatoService
        .search({
          query: this.currentSearch,
        })
        .subscribe((res: HttpResponse<ITipoAllegato[]>) => (this.tipoAllegatoes = res.body || []));
      return;
    }

    this.tipoAllegatoService.query().subscribe((res: HttpResponse<ITipoAllegato[]>) => (this.tipoAllegatoes = res.body || []));
  }

  search(query: string): void {
    this.currentSearch = query;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTipoAllegatoes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITipoAllegato): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTipoAllegatoes(): void {
    this.eventSubscriber = this.eventManager.subscribe('tipoAllegatoListModification', () => this.loadAll());
  }

  delete(tipoAllegato: ITipoAllegato): void {
    const modalRef = this.modalService.open(TipoAllegatoDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tipoAllegato = tipoAllegato;
  }
}
