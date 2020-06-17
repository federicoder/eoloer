import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IRappresentanzaPratica } from 'app/shared/model/rappresentanza-pratica.model';
import { RappresentanzaPraticaService } from './rappresentanza-pratica.service';
import { RappresentanzaPraticaDeleteDialogComponent } from './rappresentanza-pratica-delete-dialog.component';

@Component({
  selector: 'jhi-rappresentanza-pratica',
  templateUrl: './rappresentanza-pratica.component.html',
})
export class RappresentanzaPraticaComponent implements OnInit, OnDestroy {
  rappresentanzaPraticas?: IRappresentanzaPratica[];
  eventSubscriber?: Subscription;
  currentSearch: string;

  constructor(
    protected rappresentanzaPraticaService: RappresentanzaPraticaService,
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
      this.rappresentanzaPraticaService
        .search({
          query: this.currentSearch,
        })
        .subscribe((res: HttpResponse<IRappresentanzaPratica[]>) => (this.rappresentanzaPraticas = res.body || []));
      return;
    }

    this.rappresentanzaPraticaService
      .query()
      .subscribe((res: HttpResponse<IRappresentanzaPratica[]>) => (this.rappresentanzaPraticas = res.body || []));
  }

  search(query: string): void {
    this.currentSearch = query;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInRappresentanzaPraticas();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IRappresentanzaPratica): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInRappresentanzaPraticas(): void {
    this.eventSubscriber = this.eventManager.subscribe('rappresentanzaPraticaListModification', () => this.loadAll());
  }

  delete(rappresentanzaPratica: IRappresentanzaPratica): void {
    const modalRef = this.modalService.open(RappresentanzaPraticaDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.rappresentanzaPratica = rappresentanzaPratica;
  }
}
