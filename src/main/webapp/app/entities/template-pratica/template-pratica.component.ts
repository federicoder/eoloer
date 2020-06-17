import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITemplatePratica } from 'app/shared/model/template-pratica.model';
import { TemplatePraticaService } from './template-pratica.service';
import { TemplatePraticaDeleteDialogComponent } from './template-pratica-delete-dialog.component';

@Component({
  selector: 'jhi-template-pratica',
  templateUrl: './template-pratica.component.html',
})
export class TemplatePraticaComponent implements OnInit, OnDestroy {
  templatePraticas?: ITemplatePratica[];
  eventSubscriber?: Subscription;
  currentSearch: string;

  constructor(
    protected templatePraticaService: TemplatePraticaService,
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
      this.templatePraticaService
        .search({
          query: this.currentSearch,
        })
        .subscribe((res: HttpResponse<ITemplatePratica[]>) => (this.templatePraticas = res.body || []));
      return;
    }

    this.templatePraticaService.query().subscribe((res: HttpResponse<ITemplatePratica[]>) => (this.templatePraticas = res.body || []));
  }

  search(query: string): void {
    this.currentSearch = query;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTemplatePraticas();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITemplatePratica): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTemplatePraticas(): void {
    this.eventSubscriber = this.eventManager.subscribe('templatePraticaListModification', () => this.loadAll());
  }

  delete(templatePratica: ITemplatePratica): void {
    const modalRef = this.modalService.open(TemplatePraticaDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.templatePratica = templatePratica;
  }
}
