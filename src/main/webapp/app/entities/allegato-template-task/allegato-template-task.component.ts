import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IAllegatoTemplateTask } from 'app/shared/model/allegato-template-task.model';
import { AllegatoTemplateTaskService } from './allegato-template-task.service';
import { AllegatoTemplateTaskDeleteDialogComponent } from './allegato-template-task-delete-dialog.component';

@Component({
  selector: 'jhi-allegato-template-task',
  templateUrl: './allegato-template-task.component.html',
})
export class AllegatoTemplateTaskComponent implements OnInit, OnDestroy {
  allegatoTemplateTasks?: IAllegatoTemplateTask[];
  eventSubscriber?: Subscription;
  currentSearch: string;

  constructor(
    protected allegatoTemplateTaskService: AllegatoTemplateTaskService,
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
      this.allegatoTemplateTaskService
        .search({
          query: this.currentSearch,
        })
        .subscribe((res: HttpResponse<IAllegatoTemplateTask[]>) => (this.allegatoTemplateTasks = res.body || []));
      return;
    }

    this.allegatoTemplateTaskService
      .query()
      .subscribe((res: HttpResponse<IAllegatoTemplateTask[]>) => (this.allegatoTemplateTasks = res.body || []));
  }

  search(query: string): void {
    this.currentSearch = query;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInAllegatoTemplateTasks();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IAllegatoTemplateTask): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInAllegatoTemplateTasks(): void {
    this.eventSubscriber = this.eventManager.subscribe('allegatoTemplateTaskListModification', () => this.loadAll());
  }

  delete(allegatoTemplateTask: IAllegatoTemplateTask): void {
    const modalRef = this.modalService.open(AllegatoTemplateTaskDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.allegatoTemplateTask = allegatoTemplateTask;
  }
}
