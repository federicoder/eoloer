import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IConsuntivoTask } from 'app/shared/model/consuntivo-task.model';
import { ConsuntivoTaskService } from './consuntivo-task.service';
import { ConsuntivoTaskDeleteDialogComponent } from './consuntivo-task-delete-dialog.component';

@Component({
  selector: 'jhi-consuntivo-task',
  templateUrl: './consuntivo-task.component.html',
})
export class ConsuntivoTaskComponent implements OnInit, OnDestroy {
  consuntivoTasks?: IConsuntivoTask[];
  eventSubscriber?: Subscription;
  currentSearch: string;

  constructor(
    protected consuntivoTaskService: ConsuntivoTaskService,
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
      this.consuntivoTaskService
        .search({
          query: this.currentSearch,
        })
        .subscribe((res: HttpResponse<IConsuntivoTask[]>) => (this.consuntivoTasks = res.body || []));
      return;
    }

    this.consuntivoTaskService.query().subscribe((res: HttpResponse<IConsuntivoTask[]>) => (this.consuntivoTasks = res.body || []));
  }

  search(query: string): void {
    this.currentSearch = query;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInConsuntivoTasks();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IConsuntivoTask): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInConsuntivoTasks(): void {
    this.eventSubscriber = this.eventManager.subscribe('consuntivoTaskListModification', () => this.loadAll());
  }

  delete(consuntivoTask: IConsuntivoTask): void {
    const modalRef = this.modalService.open(ConsuntivoTaskDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.consuntivoTask = consuntivoTask;
  }
}
