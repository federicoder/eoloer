import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITemplateTask } from 'app/shared/model/template-task.model';
import { TemplateTaskService } from './template-task.service';
import { TemplateTaskDeleteDialogComponent } from './template-task-delete-dialog.component';

@Component({
  selector: 'jhi-template-task',
  templateUrl: './template-task.component.html',
})
export class TemplateTaskComponent implements OnInit, OnDestroy {
  templateTasks?: ITemplateTask[];
  eventSubscriber?: Subscription;
  currentSearch: string;

  constructor(
    protected templateTaskService: TemplateTaskService,
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
      this.templateTaskService
        .search({
          query: this.currentSearch,
        })
        .subscribe((res: HttpResponse<ITemplateTask[]>) => (this.templateTasks = res.body || []));
      return;
    }

    this.templateTaskService.query().subscribe((res: HttpResponse<ITemplateTask[]>) => (this.templateTasks = res.body || []));
  }

  search(query: string): void {
    this.currentSearch = query;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTemplateTasks();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITemplateTask): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTemplateTasks(): void {
    this.eventSubscriber = this.eventManager.subscribe('templateTaskListModification', () => this.loadAll());
  }

  delete(templateTask: ITemplateTask): void {
    const modalRef = this.modalService.open(TemplateTaskDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.templateTask = templateTask;
  }
}
