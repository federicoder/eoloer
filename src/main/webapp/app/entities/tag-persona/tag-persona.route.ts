import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITagPersona, TagPersona } from 'app/shared/model/tag-persona.model';
import { TagPersonaService } from './tag-persona.service';
import { TagPersonaComponent } from './tag-persona.component';
import { TagPersonaDetailComponent } from './tag-persona-detail.component';
import { TagPersonaUpdateComponent } from './tag-persona-update.component';

@Injectable({ providedIn: 'root' })
export class TagPersonaResolve implements Resolve<ITagPersona> {
  constructor(private service: TagPersonaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITagPersona> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tagPersona: HttpResponse<TagPersona>) => {
          if (tagPersona.body) {
            return of(tagPersona.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TagPersona());
  }
}

export const tagPersonaRoute: Routes = [
  {
    path: '',
    component: TagPersonaComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.tagPersona.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TagPersonaDetailComponent,
    resolve: {
      tagPersona: TagPersonaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.tagPersona.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TagPersonaUpdateComponent,
    resolve: {
      tagPersona: TagPersonaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.tagPersona.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TagPersonaUpdateComponent,
    resolve: {
      tagPersona: TagPersonaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.tagPersona.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
