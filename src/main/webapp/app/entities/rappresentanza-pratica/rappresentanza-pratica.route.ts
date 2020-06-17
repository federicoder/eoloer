import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IRappresentanzaPratica, RappresentanzaPratica } from 'app/shared/model/rappresentanza-pratica.model';
import { RappresentanzaPraticaService } from './rappresentanza-pratica.service';
import { RappresentanzaPraticaComponent } from './rappresentanza-pratica.component';
import { RappresentanzaPraticaDetailComponent } from './rappresentanza-pratica-detail.component';
import { RappresentanzaPraticaUpdateComponent } from './rappresentanza-pratica-update.component';

@Injectable({ providedIn: 'root' })
export class RappresentanzaPraticaResolve implements Resolve<IRappresentanzaPratica> {
  constructor(private service: RappresentanzaPraticaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IRappresentanzaPratica> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((rappresentanzaPratica: HttpResponse<RappresentanzaPratica>) => {
          if (rappresentanzaPratica.body) {
            return of(rappresentanzaPratica.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new RappresentanzaPratica());
  }
}

export const rappresentanzaPraticaRoute: Routes = [
  {
    path: '',
    component: RappresentanzaPraticaComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.rappresentanzaPratica.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: RappresentanzaPraticaDetailComponent,
    resolve: {
      rappresentanzaPratica: RappresentanzaPraticaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.rappresentanzaPratica.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: RappresentanzaPraticaUpdateComponent,
    resolve: {
      rappresentanzaPratica: RappresentanzaPraticaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.rappresentanzaPratica.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: RappresentanzaPraticaUpdateComponent,
    resolve: {
      rappresentanzaPratica: RappresentanzaPraticaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.rappresentanzaPratica.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
