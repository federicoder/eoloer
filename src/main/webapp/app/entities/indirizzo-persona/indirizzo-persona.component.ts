import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IIndirizzoPersona } from 'app/shared/model/indirizzo-persona.model';
import { IndirizzoPersonaService } from './indirizzo-persona.service';
import { IndirizzoPersonaDeleteDialogComponent } from './indirizzo-persona-delete-dialog.component';

@Component({
  selector: 'jhi-indirizzo-persona',
  templateUrl: './indirizzo-persona.component.html',
})
export class IndirizzoPersonaComponent implements OnInit, OnDestroy {
  indirizzoPersonas?: IIndirizzoPersona[];
  eventSubscriber?: Subscription;
  currentSearch: string;

  constructor(
    protected indirizzoPersonaService: IndirizzoPersonaService,
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
      this.indirizzoPersonaService
        .search({
          query: this.currentSearch,
        })
        .subscribe((res: HttpResponse<IIndirizzoPersona[]>) => (this.indirizzoPersonas = res.body || []));
      return;
    }

    this.indirizzoPersonaService.query().subscribe((res: HttpResponse<IIndirizzoPersona[]>) => (this.indirizzoPersonas = res.body || []));
  }

  search(query: string): void {
    this.currentSearch = query;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInIndirizzoPersonas();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IIndirizzoPersona): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInIndirizzoPersonas(): void {
    this.eventSubscriber = this.eventManager.subscribe('indirizzoPersonaListModification', () => this.loadAll());
  }

  delete(indirizzoPersona: IIndirizzoPersona): void {
    const modalRef = this.modalService.open(IndirizzoPersonaDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.indirizzoPersona = indirizzoPersona;
  }
}
