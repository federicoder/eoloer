import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IOrganizzazione } from 'app/shared/model/organizzazione.model';
import { OrganizzazioneService } from './organizzazione.service';
import { OrganizzazioneDeleteDialogComponent } from './organizzazione-delete-dialog.component';

@Component({
  selector: 'jhi-organizzazione',
  templateUrl: './organizzazione.component.html',
})
export class OrganizzazioneComponent implements OnInit, OnDestroy {
  organizzaziones?: IOrganizzazione[];
  eventSubscriber?: Subscription;
  currentSearch: string;

  constructor(
    protected organizzazioneService: OrganizzazioneService,
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
      this.organizzazioneService
        .search({
          query: this.currentSearch,
        })
        .subscribe((res: HttpResponse<IOrganizzazione[]>) => (this.organizzaziones = res.body || []));
      return;
    }

    this.organizzazioneService.query().subscribe((res: HttpResponse<IOrganizzazione[]>) => (this.organizzaziones = res.body || []));
  }

  search(query: string): void {
    this.currentSearch = query;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInOrganizzaziones();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IOrganizzazione): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInOrganizzaziones(): void {
    this.eventSubscriber = this.eventManager.subscribe('organizzazioneListModification', () => this.loadAll());
  }

  delete(organizzazione: IOrganizzazione): void {
    const modalRef = this.modalService.open(OrganizzazioneDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.organizzazione = organizzazione;
  }
}
