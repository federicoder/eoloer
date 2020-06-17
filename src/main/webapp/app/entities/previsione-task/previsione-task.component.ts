import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IPrevisioneTask } from 'app/shared/model/previsione-task.model';
import { PrevisioneTaskService } from './previsione-task.service';
import { PrevisioneTaskDeleteDialogComponent } from './previsione-task-delete-dialog.component';

@Component({
  selector: 'jhi-previsione-task',
  templateUrl: './previsione-task.component.html',
})
export class PrevisioneTaskComponent implements OnInit, OnDestroy {
  previsioneTasks?: IPrevisioneTask[];
  eventSubscriber?: Subscription;
  currentSearch: string;

  constructor(
    protected previsioneTaskService: PrevisioneTaskService,
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
      this.previsioneTaskService
        .search({
          query: this.currentSearch,
        })
        .subscribe((res: HttpResponse<IPrevisioneTask[]>) => (this.previsioneTasks = res.body || []));
      return;
    }

    this.previsioneTaskService.query().subscribe((res: HttpResponse<IPrevisioneTask[]>) => (this.previsioneTasks = res.body || []));
  }

  search(query: string): void {
    this.currentSearch = query;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInPrevisioneTasks();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IPrevisioneTask): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInPrevisioneTasks(): void {
    this.eventSubscriber = this.eventManager.subscribe('previsioneTaskListModification', () => this.loadAll());
  }

  delete(previsioneTask: IPrevisioneTask): void {
    const modalRef = this.modalService.open(PrevisioneTaskDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.previsioneTask = previsioneTask;
  }
}
