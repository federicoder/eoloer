import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITipoAllegato, TipoAllegato } from 'app/shared/model/tipo-allegato.model';
import { TipoAllegatoService } from './tipo-allegato.service';
import { TipoAllegatoComponent } from './tipo-allegato.component';
import { TipoAllegatoDetailComponent } from './tipo-allegato-detail.component';
import { TipoAllegatoUpdateComponent } from './tipo-allegato-update.component';

@Injectable({ providedIn: 'root' })
export class TipoAllegatoResolve implements Resolve<ITipoAllegato> {
  constructor(private service: TipoAllegatoService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITipoAllegato> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tipoAllegato: HttpResponse<TipoAllegato>) => {
          if (tipoAllegato.body) {
            return of(tipoAllegato.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TipoAllegato());
  }
}

export const tipoAllegatoRoute: Routes = [
  {
    path: '',
    component: TipoAllegatoComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.tipoAllegato.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TipoAllegatoDetailComponent,
    resolve: {
      tipoAllegato: TipoAllegatoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.tipoAllegato.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TipoAllegatoUpdateComponent,
    resolve: {
      tipoAllegato: TipoAllegatoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.tipoAllegato.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TipoAllegatoUpdateComponent,
    resolve: {
      tipoAllegato: TipoAllegatoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.tipoAllegato.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
