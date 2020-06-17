import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IInvitoPratica, InvitoPratica } from 'app/shared/model/invito-pratica.model';
import { InvitoPraticaService } from './invito-pratica.service';
import { InvitoPraticaComponent } from './invito-pratica.component';
import { InvitoPraticaDetailComponent } from './invito-pratica-detail.component';
import { InvitoPraticaUpdateComponent } from './invito-pratica-update.component';

@Injectable({ providedIn: 'root' })
export class InvitoPraticaResolve implements Resolve<IInvitoPratica> {
  constructor(private service: InvitoPraticaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IInvitoPratica> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((invitoPratica: HttpResponse<InvitoPratica>) => {
          if (invitoPratica.body) {
            return of(invitoPratica.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new InvitoPratica());
  }
}

export const invitoPraticaRoute: Routes = [
  {
    path: '',
    component: InvitoPraticaComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.invitoPratica.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: InvitoPraticaDetailComponent,
    resolve: {
      invitoPratica: InvitoPraticaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.invitoPratica.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: InvitoPraticaUpdateComponent,
    resolve: {
      invitoPratica: InvitoPraticaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.invitoPratica.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: InvitoPraticaUpdateComponent,
    resolve: {
      invitoPratica: InvitoPraticaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.invitoPratica.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
