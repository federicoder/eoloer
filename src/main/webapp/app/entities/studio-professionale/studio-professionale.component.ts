import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IStudioProfessionale } from 'app/shared/model/studio-professionale.model';
import { StudioProfessionaleService } from './studio-professionale.service';
import { StudioProfessionaleDeleteDialogComponent } from './studio-professionale-delete-dialog.component';

@Component({
  selector: 'jhi-studio-professionale',
  templateUrl: './studio-professionale.component.html',
})
export class StudioProfessionaleComponent implements OnInit, OnDestroy {
  studioProfessionales?: IStudioProfessionale[];
  eventSubscriber?: Subscription;
  currentSearch: string;

  constructor(
    protected studioProfessionaleService: StudioProfessionaleService,
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
      this.studioProfessionaleService
        .search({
          query: this.currentSearch,
        })
        .subscribe((res: HttpResponse<IStudioProfessionale[]>) => (this.studioProfessionales = res.body || []));
      return;
    }

    this.studioProfessionaleService
      .query()
      .subscribe((res: HttpResponse<IStudioProfessionale[]>) => (this.studioProfessionales = res.body || []));
  }

  search(query: string): void {
    this.currentSearch = query;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInStudioProfessionales();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IStudioProfessionale): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInStudioProfessionales(): void {
    this.eventSubscriber = this.eventManager.subscribe('studioProfessionaleListModification', () => this.loadAll());
  }

  delete(studioProfessionale: IStudioProfessionale): void {
    const modalRef = this.modalService.open(StudioProfessionaleDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.studioProfessionale = studioProfessionale;
  }
}
