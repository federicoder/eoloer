import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IInvito } from 'app/shared/model/invito.model';
import { InvitoService } from './invito.service';
import { InvitoDeleteDialogComponent } from './invito-delete-dialog.component';

@Component({
  selector: 'jhi-invito',
  templateUrl: './invito.component.html',
})
export class InvitoComponent implements OnInit, OnDestroy {
  invitos?: IInvito[];
  eventSubscriber?: Subscription;
  currentSearch: string;

  constructor(
    protected invitoService: InvitoService,
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
      this.invitoService
        .search({
          query: this.currentSearch,
        })
        .subscribe((res: HttpResponse<IInvito[]>) => (this.invitos = res.body || []));
      return;
    }

    this.invitoService.query().subscribe((res: HttpResponse<IInvito[]>) => (this.invitos = res.body || []));
  }

  search(query: string): void {
    this.currentSearch = query;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInInvitos();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IInvito): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInInvitos(): void {
    this.eventSubscriber = this.eventManager.subscribe('invitoListModification', () => this.loadAll());
  }

  delete(invito: IInvito): void {
    const modalRef = this.modalService.open(InvitoDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.invito = invito;
  }
}
