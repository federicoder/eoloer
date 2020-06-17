import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { INotaPratica, NotaPratica } from 'app/shared/model/nota-pratica.model';
import { NotaPraticaService } from './nota-pratica.service';
import { NotaPraticaComponent } from './nota-pratica.component';
import { NotaPraticaDetailComponent } from './nota-pratica-detail.component';
import { NotaPraticaUpdateComponent } from './nota-pratica-update.component';

@Injectable({ providedIn: 'root' })
export class NotaPraticaResolve implements Resolve<INotaPratica> {
  constructor(private service: NotaPraticaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<INotaPratica> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((notaPratica: HttpResponse<NotaPratica>) => {
          if (notaPratica.body) {
            return of(notaPratica.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new NotaPratica());
  }
}

export const notaPraticaRoute: Routes = [
  {
    path: '',
    component: NotaPraticaComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.notaPratica.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: NotaPraticaDetailComponent,
    resolve: {
      notaPratica: NotaPraticaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.notaPratica.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: NotaPraticaUpdateComponent,
    resolve: {
      notaPratica: NotaPraticaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.notaPratica.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: NotaPraticaUpdateComponent,
    resolve: {
      notaPratica: NotaPraticaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.notaPratica.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
