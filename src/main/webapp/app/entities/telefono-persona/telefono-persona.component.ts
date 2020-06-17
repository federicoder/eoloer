import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITelefonoPersona } from 'app/shared/model/telefono-persona.model';
import { TelefonoPersonaService } from './telefono-persona.service';
import { TelefonoPersonaDeleteDialogComponent } from './telefono-persona-delete-dialog.component';

@Component({
  selector: 'jhi-telefono-persona',
  templateUrl: './telefono-persona.component.html',
})
export class TelefonoPersonaComponent implements OnInit, OnDestroy {
  telefonoPersonas?: ITelefonoPersona[];
  eventSubscriber?: Subscription;
  currentSearch: string;

  constructor(
    protected telefonoPersonaService: TelefonoPersonaService,
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
      this.telefonoPersonaService
        .search({
          query: this.currentSearch,
        })
        .subscribe((res: HttpResponse<ITelefonoPersona[]>) => (this.telefonoPersonas = res.body || []));
      return;
    }

    this.telefonoPersonaService.query().subscribe((res: HttpResponse<ITelefonoPersona[]>) => (this.telefonoPersonas = res.body || []));
  }

  search(query: string): void {
    this.currentSearch = query;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTelefonoPersonas();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITelefonoPersona): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTelefonoPersonas(): void {
    this.eventSubscriber = this.eventManager.subscribe('telefonoPersonaListModification', () => this.loadAll());
  }

  delete(telefonoPersona: ITelefonoPersona): void {
    const modalRef = this.modalService.open(TelefonoPersonaDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.telefonoPersona = telefonoPersona;
  }
}
