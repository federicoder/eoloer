import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPrevisioneEvento, PrevisioneEvento } from 'app/shared/model/previsione-evento.model';
import { PrevisioneEventoService } from './previsione-evento.service';
import { PrevisioneEventoComponent } from './previsione-evento.component';
import { PrevisioneEventoDetailComponent } from './previsione-evento-detail.component';
import { PrevisioneEventoUpdateComponent } from './previsione-evento-update.component';

@Injectable({ providedIn: 'root' })
export class PrevisioneEventoResolve implements Resolve<IPrevisioneEvento> {
  constructor(private service: PrevisioneEventoService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPrevisioneEvento> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((previsioneEvento: HttpResponse<PrevisioneEvento>) => {
          if (previsioneEvento.body) {
            return of(previsioneEvento.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new PrevisioneEvento());
  }
}

export const previsioneEventoRoute: Routes = [
  {
    path: '',
    component: PrevisioneEventoComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.previsioneEvento.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: PrevisioneEventoDetailComponent,
    resolve: {
      previsioneEvento: PrevisioneEventoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.previsioneEvento.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: PrevisioneEventoUpdateComponent,
    resolve: {
      previsioneEvento: PrevisioneEventoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.previsioneEvento.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: PrevisioneEventoUpdateComponent,
    resolve: {
      previsioneEvento: PrevisioneEventoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.previsioneEvento.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
