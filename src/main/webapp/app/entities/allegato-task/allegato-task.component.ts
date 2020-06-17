import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IAllegatoTask } from 'app/shared/model/allegato-task.model';
import { AllegatoTaskService } from './allegato-task.service';
import { AllegatoTaskDeleteDialogComponent } from './allegato-task-delete-dialog.component';

@Component({
  selector: 'jhi-allegato-task',
  templateUrl: './allegato-task.component.html',
})
export class AllegatoTaskComponent implements OnInit, OnDestroy {
  allegatoTasks?: IAllegatoTask[];
  eventSubscriber?: Subscription;
  currentSearch: string;

  constructor(
    protected allegatoTaskService: AllegatoTaskService,
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
      this.allegatoTaskService
        .search({
          query: this.currentSearch,
        })
        .subscribe((res: HttpResponse<IAllegatoTask[]>) => (this.allegatoTasks = res.body || []));
      return;
    }

    this.allegatoTaskService.query().subscribe((res: HttpResponse<IAllegatoTask[]>) => (this.allegatoTasks = res.body || []));
  }

  search(query: string): void {
    this.currentSearch = query;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInAllegatoTasks();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IAllegatoTask): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInAllegatoTasks(): void {
    this.eventSubscriber = this.eventManager.subscribe('allegatoTaskListModification', () => this.loadAll());
  }

  delete(allegatoTask: IAllegatoTask): void {
    const modalRef = this.modalService.open(AllegatoTaskDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.allegatoTask = allegatoTask;
  }
}
