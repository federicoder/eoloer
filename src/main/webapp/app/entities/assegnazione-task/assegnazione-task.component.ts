import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IAssegnazioneTask } from 'app/shared/model/assegnazione-task.model';
import { AssegnazioneTaskService } from './assegnazione-task.service';
import { AssegnazioneTaskDeleteDialogComponent } from './assegnazione-task-delete-dialog.component';

@Component({
  selector: 'jhi-assegnazione-task',
  templateUrl: './assegnazione-task.component.html',
})
export class AssegnazioneTaskComponent implements OnInit, OnDestroy {
  assegnazioneTasks?: IAssegnazioneTask[];
  eventSubscriber?: Subscription;
  currentSearch: string;

  constructor(
    protected assegnazioneTaskService: AssegnazioneTaskService,
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
      this.assegnazioneTaskService
        .search({
          query: this.currentSearch,
        })
        .subscribe((res: HttpResponse<IAssegnazioneTask[]>) => (this.assegnazioneTasks = res.body || []));
      return;
    }

    this.assegnazioneTaskService.query().subscribe((res: HttpResponse<IAssegnazioneTask[]>) => (this.assegnazioneTasks = res.body || []));
  }

  search(query: string): void {
    this.currentSearch = query;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInAssegnazioneTasks();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IAssegnazioneTask): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInAssegnazioneTasks(): void {
    this.eventSubscriber = this.eventManager.subscribe('assegnazioneTaskListModification', () => this.loadAll());
  }

  delete(assegnazioneTask: IAssegnazioneTask): void {
    const modalRef = this.modalService.open(AssegnazioneTaskDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.assegnazioneTask = assegnazioneTask;
  }
}
