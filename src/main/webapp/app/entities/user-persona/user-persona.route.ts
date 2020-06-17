import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IUserPersona, UserPersona } from 'app/shared/model/user-persona.model';
import { UserPersonaService } from './user-persona.service';
import { UserPersonaComponent } from './user-persona.component';
import { UserPersonaDetailComponent } from './user-persona-detail.component';
import { UserPersonaUpdateComponent } from './user-persona-update.component';

@Injectable({ providedIn: 'root' })
export class UserPersonaResolve implements Resolve<IUserPersona> {
  constructor(private service: UserPersonaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IUserPersona> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((userPersona: HttpResponse<UserPersona>) => {
          if (userPersona.body) {
            return of(userPersona.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new UserPersona());
  }
}

export const userPersonaRoute: Routes = [
  {
    path: '',
    component: UserPersonaComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.userPersona.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: UserPersonaDetailComponent,
    resolve: {
      userPersona: UserPersonaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.userPersona.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: UserPersonaUpdateComponent,
    resolve: {
      userPersona: UserPersonaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.userPersona.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: UserPersonaUpdateComponent,
    resolve: {
      userPersona: UserPersonaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.userPersona.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
