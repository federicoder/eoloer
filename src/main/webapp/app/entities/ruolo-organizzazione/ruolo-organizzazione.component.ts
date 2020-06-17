import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IRuoloOrganizzazione } from 'app/shared/model/ruolo-organizzazione.model';
import { RuoloOrganizzazioneService } from './ruolo-organizzazione.service';
import { RuoloOrganizzazioneDeleteDialogComponent } from './ruolo-organizzazione-delete-dialog.component';

@Component({
  selector: 'jhi-ruolo-organizzazione',
  templateUrl: './ruolo-organizzazione.component.html',
})
export class RuoloOrganizzazioneComponent implements OnInit, OnDestroy {
  ruoloOrganizzaziones?: IRuoloOrganizzazione[];
  eventSubscriber?: Subscription;
  currentSearch: string;

  constructor(
    protected ruoloOrganizzazioneService: RuoloOrganizzazioneService,
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
      this.ruoloOrganizzazioneService
        .search({
          query: this.currentSearch,
        })
        .subscribe((res: HttpResponse<IRuoloOrganizzazione[]>) => (this.ruoloOrganizzaziones = res.body || []));
      return;
    }

    this.ruoloOrganizzazioneService
      .query()
      .subscribe((res: HttpResponse<IRuoloOrganizzazione[]>) => (this.ruoloOrganizzaziones = res.body || []));
  }

  search(query: string): void {
    this.currentSearch = query;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInRuoloOrganizzaziones();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IRuoloOrganizzazione): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInRuoloOrganizzaziones(): void {
    this.eventSubscriber = this.eventManager.subscribe('ruoloOrganizzazioneListModification', () => this.loadAll());
  }

  delete(ruoloOrganizzazione: IRuoloOrganizzazione): void {
    const modalRef = this.modalService.open(RuoloOrganizzazioneDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.ruoloOrganizzazione = ruoloOrganizzazione;
  }
}
