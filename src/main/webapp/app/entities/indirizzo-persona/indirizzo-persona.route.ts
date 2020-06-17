import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IIndirizzoPersona, IndirizzoPersona } from 'app/shared/model/indirizzo-persona.model';
import { IndirizzoPersonaService } from './indirizzo-persona.service';
import { IndirizzoPersonaComponent } from './indirizzo-persona.component';
import { IndirizzoPersonaDetailComponent } from './indirizzo-persona-detail.component';
import { IndirizzoPersonaUpdateComponent } from './indirizzo-persona-update.component';

@Injectable({ providedIn: 'root' })
export class IndirizzoPersonaResolve implements Resolve<IIndirizzoPersona> {
  constructor(private service: IndirizzoPersonaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IIndirizzoPersona> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((indirizzoPersona: HttpResponse<IndirizzoPersona>) => {
          if (indirizzoPersona.body) {
            return of(indirizzoPersona.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new IndirizzoPersona());
  }
}

export const indirizzoPersonaRoute: Routes = [
  {
    path: '',
    component: IndirizzoPersonaComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.indirizzoPersona.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: IndirizzoPersonaDetailComponent,
    resolve: {
      indirizzoPersona: IndirizzoPersonaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.indirizzoPersona.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: IndirizzoPersonaUpdateComponent,
    resolve: {
      indirizzoPersona: IndirizzoPersonaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.indirizzoPersona.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: IndirizzoPersonaUpdateComponent,
    resolve: {
      indirizzoPersona: IndirizzoPersonaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.indirizzoPersona.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
