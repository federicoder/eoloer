import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ILineaOrdine, LineaOrdine } from 'app/shared/model/linea-ordine.model';
import { LineaOrdineService } from './linea-ordine.service';
import { LineaOrdineComponent } from './linea-ordine.component';
import { LineaOrdineDetailComponent } from './linea-ordine-detail.component';
import { LineaOrdineUpdateComponent } from './linea-ordine-update.component';

@Injectable({ providedIn: 'root' })
export class LineaOrdineResolve implements Resolve<ILineaOrdine> {
  constructor(private service: LineaOrdineService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ILineaOrdine> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((lineaOrdine: HttpResponse<LineaOrdine>) => {
          if (lineaOrdine.body) {
            return of(lineaOrdine.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new LineaOrdine());
  }
}

export const lineaOrdineRoute: Routes = [
  {
    path: '',
    component: LineaOrdineComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.lineaOrdine.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: LineaOrdineDetailComponent,
    resolve: {
      lineaOrdine: LineaOrdineResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.lineaOrdine.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: LineaOrdineUpdateComponent,
    resolve: {
      lineaOrdine: LineaOrdineResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.lineaOrdine.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: LineaOrdineUpdateComponent,
    resolve: {
      lineaOrdine: LineaOrdineResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.lineaOrdine.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
