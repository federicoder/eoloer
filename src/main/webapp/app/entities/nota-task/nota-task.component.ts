import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { INotaTask } from 'app/shared/model/nota-task.model';
import { NotaTaskService } from './nota-task.service';
import { NotaTaskDeleteDialogComponent } from './nota-task-delete-dialog.component';

@Component({
  selector: 'jhi-nota-task',
  templateUrl: './nota-task.component.html',
})
export class NotaTaskComponent implements OnInit, OnDestroy {
  notaTasks?: INotaTask[];
  eventSubscriber?: Subscription;
  currentSearch: string;

  constructor(
    protected notaTaskService: NotaTaskService,
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
      this.notaTaskService
        .search({
          query: this.currentSearch,
        })
        .subscribe((res: HttpResponse<INotaTask[]>) => (this.notaTasks = res.body || []));
      return;
    }

    this.notaTaskService.query().subscribe((res: HttpResponse<INotaTask[]>) => (this.notaTasks = res.body || []));
  }

  search(query: string): void {
    this.currentSearch = query;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInNotaTasks();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: INotaTask): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInNotaTasks(): void {
    this.eventSubscriber = this.eventManager.subscribe('notaTaskListModification', () => this.loadAll());
  }

  delete(notaTask: INotaTask): void {
    const modalRef = this.modalService.open(NotaTaskDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.notaTask = notaTask;
  }
}
