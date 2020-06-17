import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICondivisionePratica } from 'app/shared/model/condivisione-pratica.model';
import { CondivisionePraticaService } from './condivisione-pratica.service';
import { CondivisionePraticaDeleteDialogComponent } from './condivisione-pratica-delete-dialog.component';

@Component({
  selector: 'jhi-condivisione-pratica',
  templateUrl: './condivisione-pratica.component.html',
})
export class CondivisionePraticaComponent implements OnInit, OnDestroy {
  condivisionePraticas?: ICondivisionePratica[];
  eventSubscriber?: Subscription;
  currentSearch: string;

  constructor(
    protected condivisionePraticaService: CondivisionePraticaService,
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
      this.condivisionePraticaService
        .search({
          query: this.currentSearch,
        })
        .subscribe((res: HttpResponse<ICondivisionePratica[]>) => (this.condivisionePraticas = res.body || []));
      return;
    }

    this.condivisionePraticaService
      .query()
      .subscribe((res: HttpResponse<ICondivisionePratica[]>) => (this.condivisionePraticas = res.body || []));
  }

  search(query: string): void {
    this.currentSearch = query;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInCondivisionePraticas();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ICondivisionePratica): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInCondivisionePraticas(): void {
    this.eventSubscriber = this.eventManager.subscribe('condivisionePraticaListModification', () => this.loadAll());
  }

  delete(condivisionePratica: ICondivisionePratica): void {
    const modalRef = this.modalService.open(CondivisionePraticaDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.condivisionePratica = condivisionePratica;
  }
}
