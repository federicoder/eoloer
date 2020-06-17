import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { INotaPratica } from 'app/shared/model/nota-pratica.model';
import { NotaPraticaService } from './nota-pratica.service';
import { NotaPraticaDeleteDialogComponent } from './nota-pratica-delete-dialog.component';

@Component({
  selector: 'jhi-nota-pratica',
  templateUrl: './nota-pratica.component.html',
})
export class NotaPraticaComponent implements OnInit, OnDestroy {
  notaPraticas?: INotaPratica[];
  eventSubscriber?: Subscription;
  currentSearch: string;

  constructor(
    protected notaPraticaService: NotaPraticaService,
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
      this.notaPraticaService
        .search({
          query: this.currentSearch,
        })
        .subscribe((res: HttpResponse<INotaPratica[]>) => (this.notaPraticas = res.body || []));
      return;
    }

    this.notaPraticaService.query().subscribe((res: HttpResponse<INotaPratica[]>) => (this.notaPraticas = res.body || []));
  }

  search(query: string): void {
    this.currentSearch = query;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInNotaPraticas();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: INotaPratica): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInNotaPraticas(): void {
    this.eventSubscriber = this.eventManager.subscribe('notaPraticaListModification', () => this.loadAll());
  }

  delete(notaPratica: INotaPratica): void {
    const modalRef = this.modalService.open(NotaPraticaDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.notaPratica = notaPratica;
  }
}
