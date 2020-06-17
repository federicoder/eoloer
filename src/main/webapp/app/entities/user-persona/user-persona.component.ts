import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IUserPersona } from 'app/shared/model/user-persona.model';
import { UserPersonaService } from './user-persona.service';
import { UserPersonaDeleteDialogComponent } from './user-persona-delete-dialog.component';

@Component({
  selector: 'jhi-user-persona',
  templateUrl: './user-persona.component.html',
})
export class UserPersonaComponent implements OnInit, OnDestroy {
  userPersonas?: IUserPersona[];
  eventSubscriber?: Subscription;
  currentSearch: string;

  constructor(
    protected userPersonaService: UserPersonaService,
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
      this.userPersonaService
        .search({
          query: this.currentSearch,
        })
        .subscribe((res: HttpResponse<IUserPersona[]>) => (this.userPersonas = res.body || []));
      return;
    }

    this.userPersonaService.query().subscribe((res: HttpResponse<IUserPersona[]>) => (this.userPersonas = res.body || []));
  }

  search(query: string): void {
    this.currentSearch = query;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInUserPersonas();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IUserPersona): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInUserPersonas(): void {
    this.eventSubscriber = this.eventManager.subscribe('userPersonaListModification', () => this.loadAll());
  }

  delete(userPersona: IUserPersona): void {
    const modalRef = this.modalService.open(UserPersonaDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.userPersona = userPersona;
  }
}
