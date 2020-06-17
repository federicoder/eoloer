import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { INotePersona, NotePersona } from 'app/shared/model/note-persona.model';
import { NotePersonaService } from './note-persona.service';
import { NotePersonaComponent } from './note-persona.component';
import { NotePersonaDetailComponent } from './note-persona-detail.component';
import { NotePersonaUpdateComponent } from './note-persona-update.component';

@Injectable({ providedIn: 'root' })
export class NotePersonaResolve implements Resolve<INotePersona> {
  constructor(private service: NotePersonaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<INotePersona> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((notePersona: HttpResponse<NotePersona>) => {
          if (notePersona.body) {
            return of(notePersona.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new NotePersona());
  }
}

export const notePersonaRoute: Routes = [
  {
    path: '',
    component: NotePersonaComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.notePersona.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: NotePersonaDetailComponent,
    resolve: {
      notePersona: NotePersonaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.notePersona.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: NotePersonaUpdateComponent,
    resolve: {
      notePersona: NotePersonaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.notePersona.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: NotePersonaUpdateComponent,
    resolve: {
      notePersona: NotePersonaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.notePersona.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
