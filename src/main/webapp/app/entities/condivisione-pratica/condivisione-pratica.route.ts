import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICondivisionePratica, CondivisionePratica } from 'app/shared/model/condivisione-pratica.model';
import { CondivisionePraticaService } from './condivisione-pratica.service';
import { CondivisionePraticaComponent } from './condivisione-pratica.component';
import { CondivisionePraticaDetailComponent } from './condivisione-pratica-detail.component';
import { CondivisionePraticaUpdateComponent } from './condivisione-pratica-update.component';

@Injectable({ providedIn: 'root' })
export class CondivisionePraticaResolve implements Resolve<ICondivisionePratica> {
  constructor(private service: CondivisionePraticaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICondivisionePratica> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((condivisionePratica: HttpResponse<CondivisionePratica>) => {
          if (condivisionePratica.body) {
            return of(condivisionePratica.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CondivisionePratica());
  }
}

export const condivisionePraticaRoute: Routes = [
  {
    path: '',
    component: CondivisionePraticaComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.condivisionePratica.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CondivisionePraticaDetailComponent,
    resolve: {
      condivisionePratica: CondivisionePraticaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.condivisionePratica.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CondivisionePraticaUpdateComponent,
    resolve: {
      condivisionePratica: CondivisionePraticaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.condivisionePratica.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CondivisionePraticaUpdateComponent,
    resolve: {
      condivisionePratica: CondivisionePraticaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.condivisionePratica.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
