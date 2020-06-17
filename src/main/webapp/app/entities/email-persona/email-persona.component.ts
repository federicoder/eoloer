import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEmailPersona } from 'app/shared/model/email-persona.model';
import { EmailPersonaService } from './email-persona.service';
import { EmailPersonaDeleteDialogComponent } from './email-persona-delete-dialog.component';

@Component({
  selector: 'jhi-email-persona',
  templateUrl: './email-persona.component.html',
})
export class EmailPersonaComponent implements OnInit, OnDestroy {
  emailPersonas?: IEmailPersona[];
  eventSubscriber?: Subscription;
  currentSearch: string;

  constructor(
    protected emailPersonaService: EmailPersonaService,
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
      this.emailPersonaService
        .search({
          query: this.currentSearch,
        })
        .subscribe((res: HttpResponse<IEmailPersona[]>) => (this.emailPersonas = res.body || []));
      return;
    }

    this.emailPersonaService.query().subscribe((res: HttpResponse<IEmailPersona[]>) => (this.emailPersonas = res.body || []));
  }

  search(query: string): void {
    this.currentSearch = query;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInEmailPersonas();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IEmailPersona): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInEmailPersonas(): void {
    this.eventSubscriber = this.eventManager.subscribe('emailPersonaListModification', () => this.loadAll());
  }

  delete(emailPersona: IEmailPersona): void {
    const modalRef = this.modalService.open(EmailPersonaDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.emailPersona = emailPersona;
  }
}
