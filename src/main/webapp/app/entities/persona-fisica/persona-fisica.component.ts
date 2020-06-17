import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IPersonaFisica } from 'app/shared/model/persona-fisica.model';
import { PersonaFisicaService } from './persona-fisica.service';
import { PersonaFisicaDeleteDialogComponent } from './persona-fisica-delete-dialog.component';

@Component({
  selector: 'jhi-persona-fisica',
  templateUrl: './persona-fisica.component.html',
})
export class PersonaFisicaComponent implements OnInit, OnDestroy {
  personaFisicas?: IPersonaFisica[];
  eventSubscriber?: Subscription;
  currentSearch: string;

  constructor(
    protected personaFisicaService: PersonaFisicaService,
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
      this.personaFisicaService
        .search({
          query: this.currentSearch,
        })
        .subscribe((res: HttpResponse<IPersonaFisica[]>) => (this.personaFisicas = res.body || []));
      return;
    }

    this.personaFisicaService.query().subscribe((res: HttpResponse<IPersonaFisica[]>) => (this.personaFisicas = res.body || []));
  }

  search(query: string): void {
    this.currentSearch = query;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInPersonaFisicas();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IPersonaFisica): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInPersonaFisicas(): void {
    this.eventSubscriber = this.eventManager.subscribe('personaFisicaListModification', () => this.loadAll());
  }

  delete(personaFisica: IPersonaFisica): void {
    const modalRef = this.modalService.open(PersonaFisicaDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.personaFisica = personaFisica;
  }
}
