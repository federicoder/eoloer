import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IRisorseDisponibili, RisorseDisponibili } from 'app/shared/model/risorse-disponibili.model';
import { RisorseDisponibiliService } from './risorse-disponibili.service';
import { RisorseDisponibiliComponent } from './risorse-disponibili.component';
import { RisorseDisponibiliDetailComponent } from './risorse-disponibili-detail.component';
import { RisorseDisponibiliUpdateComponent } from './risorse-disponibili-update.component';

@Injectable({ providedIn: 'root' })
export class RisorseDisponibiliResolve implements Resolve<IRisorseDisponibili> {
  constructor(private service: RisorseDisponibiliService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IRisorseDisponibili> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((risorseDisponibili: HttpResponse<RisorseDisponibili>) => {
          if (risorseDisponibili.body) {
            return of(risorseDisponibili.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new RisorseDisponibili());
  }
}

export const risorseDisponibiliRoute: Routes = [
  {
    path: '',
    component: RisorseDisponibiliComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.risorseDisponibili.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: RisorseDisponibiliDetailComponent,
    resolve: {
      risorseDisponibili: RisorseDisponibiliResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.risorseDisponibili.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: RisorseDisponibiliUpdateComponent,
    resolve: {
      risorseDisponibili: RisorseDisponibiliResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.risorseDisponibili.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: RisorseDisponibiliUpdateComponent,
    resolve: {
      risorseDisponibili: RisorseDisponibiliResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.risorseDisponibili.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
