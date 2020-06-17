import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IInvitato, Invitato } from 'app/shared/model/invitato.model';
import { InvitatoService } from './invitato.service';
import { InvitatoComponent } from './invitato.component';
import { InvitatoDetailComponent } from './invitato-detail.component';
import { InvitatoUpdateComponent } from './invitato-update.component';

@Injectable({ providedIn: 'root' })
export class InvitatoResolve implements Resolve<IInvitato> {
  constructor(private service: InvitatoService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IInvitato> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((invitato: HttpResponse<Invitato>) => {
          if (invitato.body) {
            return of(invitato.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Invitato());
  }
}

export const invitatoRoute: Routes = [
  {
    path: '',
    component: InvitatoComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.invitato.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: InvitatoDetailComponent,
    resolve: {
      invitato: InvitatoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.invitato.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: InvitatoUpdateComponent,
    resolve: {
      invitato: InvitatoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.invitato.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: InvitatoUpdateComponent,
    resolve: {
      invitato: InvitatoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.invitato.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
