import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITagPersona } from 'app/shared/model/tag-persona.model';
import { TagPersonaService } from './tag-persona.service';
import { TagPersonaDeleteDialogComponent } from './tag-persona-delete-dialog.component';

@Component({
  selector: 'jhi-tag-persona',
  templateUrl: './tag-persona.component.html',
})
export class TagPersonaComponent implements OnInit, OnDestroy {
  tagPersonas?: ITagPersona[];
  eventSubscriber?: Subscription;
  currentSearch: string;

  constructor(
    protected tagPersonaService: TagPersonaService,
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
      this.tagPersonaService
        .search({
          query: this.currentSearch,
        })
        .subscribe((res: HttpResponse<ITagPersona[]>) => (this.tagPersonas = res.body || []));
      return;
    }

    this.tagPersonaService.query().subscribe((res: HttpResponse<ITagPersona[]>) => (this.tagPersonas = res.body || []));
  }

  search(query: string): void {
    this.currentSearch = query;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTagPersonas();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITagPersona): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTagPersonas(): void {
    this.eventSubscriber = this.eventManager.subscribe('tagPersonaListModification', () => this.loadAll());
  }

  delete(tagPersona: ITagPersona): void {
    const modalRef = this.modalService.open(TagPersonaDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tagPersona = tagPersona;
  }
}
