import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITelefonoPersona, TelefonoPersona } from 'app/shared/model/telefono-persona.model';
import { TelefonoPersonaService } from './telefono-persona.service';
import { TelefonoPersonaComponent } from './telefono-persona.component';
import { TelefonoPersonaDetailComponent } from './telefono-persona-detail.component';
import { TelefonoPersonaUpdateComponent } from './telefono-persona-update.component';

@Injectable({ providedIn: 'root' })
export class TelefonoPersonaResolve implements Resolve<ITelefonoPersona> {
  constructor(private service: TelefonoPersonaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITelefonoPersona> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((telefonoPersona: HttpResponse<TelefonoPersona>) => {
          if (telefonoPersona.body) {
            return of(telefonoPersona.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TelefonoPersona());
  }
}

export const telefonoPersonaRoute: Routes = [
  {
    path: '',
    component: TelefonoPersonaComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.telefonoPersona.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TelefonoPersonaDetailComponent,
    resolve: {
      telefonoPersona: TelefonoPersonaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.telefonoPersona.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TelefonoPersonaUpdateComponent,
    resolve: {
      telefonoPersona: TelefonoPersonaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.telefonoPersona.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TelefonoPersonaUpdateComponent,
    resolve: {
      telefonoPersona: TelefonoPersonaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.telefonoPersona.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
