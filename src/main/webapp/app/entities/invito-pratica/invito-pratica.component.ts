import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IInvitoPratica } from 'app/shared/model/invito-pratica.model';
import { InvitoPraticaService } from './invito-pratica.service';
import { InvitoPraticaDeleteDialogComponent } from './invito-pratica-delete-dialog.component';

@Component({
  selector: 'jhi-invito-pratica',
  templateUrl: './invito-pratica.component.html',
})
export class InvitoPraticaComponent implements OnInit, OnDestroy {
  invitoPraticas?: IInvitoPratica[];
  eventSubscriber?: Subscription;
  currentSearch: string;

  constructor(
    protected invitoPraticaService: InvitoPraticaService,
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
      this.invitoPraticaService
        .search({
          query: this.currentSearch,
        })
        .subscribe((res: HttpResponse<IInvitoPratica[]>) => (this.invitoPraticas = res.body || []));
      return;
    }

    this.invitoPraticaService.query().subscribe((res: HttpResponse<IInvitoPratica[]>) => (this.invitoPraticas = res.body || []));
  }

  search(query: string): void {
    this.currentSearch = query;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInInvitoPraticas();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IInvitoPratica): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInInvitoPraticas(): void {
    this.eventSubscriber = this.eventManager.subscribe('invitoPraticaListModification', () => this.loadAll());
  }

  delete(invitoPratica: IInvitoPratica): void {
    const modalRef = this.modalService.open(InvitoPraticaDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.invitoPratica = invitoPratica;
  }
}
