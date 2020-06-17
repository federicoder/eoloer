import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IInvitato } from 'app/shared/model/invitato.model';
import { InvitatoService } from './invitato.service';
import { InvitatoDeleteDialogComponent } from './invitato-delete-dialog.component';

@Component({
  selector: 'jhi-invitato',
  templateUrl: './invitato.component.html',
})
export class InvitatoComponent implements OnInit, OnDestroy {
  invitatoes?: IInvitato[];
  eventSubscriber?: Subscription;
  currentSearch: string;

  constructor(
    protected invitatoService: InvitatoService,
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
      this.invitatoService
        .search({
          query: this.currentSearch,
        })
        .subscribe((res: HttpResponse<IInvitato[]>) => (this.invitatoes = res.body || []));
      return;
    }

    this.invitatoService.query().subscribe((res: HttpResponse<IInvitato[]>) => (this.invitatoes = res.body || []));
  }

  search(query: string): void {
    this.currentSearch = query;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInInvitatoes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IInvitato): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInInvitatoes(): void {
    this.eventSubscriber = this.eventManager.subscribe('invitatoListModification', () => this.loadAll());
  }

  delete(invitato: IInvitato): void {
    const modalRef = this.modalService.open(InvitatoDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.invitato = invitato;
  }
}
