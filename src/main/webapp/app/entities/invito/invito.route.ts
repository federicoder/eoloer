import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IInvito, Invito } from 'app/shared/model/invito.model';
import { InvitoService } from './invito.service';
import { InvitoComponent } from './invito.component';
import { InvitoDetailComponent } from './invito-detail.component';
import { InvitoUpdateComponent } from './invito-update.component';

@Injectable({ providedIn: 'root' })
export class InvitoResolve implements Resolve<IInvito> {
  constructor(private service: InvitoService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IInvito> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((invito: HttpResponse<Invito>) => {
          if (invito.body) {
            return of(invito.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Invito());
  }
}

export const invitoRoute: Routes = [
  {
    path: '',
    component: InvitoComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.invito.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: InvitoDetailComponent,
    resolve: {
      invito: InvitoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.invito.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: InvitoUpdateComponent,
    resolve: {
      invito: InvitoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.invito.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: InvitoUpdateComponent,
    resolve: {
      invito: InvitoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.invito.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
