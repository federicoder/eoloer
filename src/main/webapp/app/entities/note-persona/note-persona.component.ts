import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { INotePersona } from 'app/shared/model/note-persona.model';
import { NotePersonaService } from './note-persona.service';
import { NotePersonaDeleteDialogComponent } from './note-persona-delete-dialog.component';

@Component({
  selector: 'jhi-note-persona',
  templateUrl: './note-persona.component.html',
})
export class NotePersonaComponent implements OnInit, OnDestroy {
  notePersonas?: INotePersona[];
  eventSubscriber?: Subscription;
  currentSearch: string;

  constructor(
    protected notePersonaService: NotePersonaService,
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
      this.notePersonaService
        .search({
          query: this.currentSearch,
        })
        .subscribe((res: HttpResponse<INotePersona[]>) => (this.notePersonas = res.body || []));
      return;
    }

    this.notePersonaService.query().subscribe((res: HttpResponse<INotePersona[]>) => (this.notePersonas = res.body || []));
  }

  search(query: string): void {
    this.currentSearch = query;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInNotePersonas();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: INotePersona): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInNotePersonas(): void {
    this.eventSubscriber = this.eventManager.subscribe('notePersonaListModification', () => this.loadAll());
  }

  delete(notePersona: INotePersona): void {
    const modalRef = this.modalService.open(NotePersonaDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.notePersona = notePersona;
  }
}
