import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITemplatePratica, TemplatePratica } from 'app/shared/model/template-pratica.model';
import { TemplatePraticaService } from './template-pratica.service';
import { TemplatePraticaComponent } from './template-pratica.component';
import { TemplatePraticaDetailComponent } from './template-pratica-detail.component';
import { TemplatePraticaUpdateComponent } from './template-pratica-update.component';

@Injectable({ providedIn: 'root' })
export class TemplatePraticaResolve implements Resolve<ITemplatePratica> {
  constructor(private service: TemplatePraticaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITemplatePratica> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((templatePratica: HttpResponse<TemplatePratica>) => {
          if (templatePratica.body) {
            return of(templatePratica.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TemplatePratica());
  }
}

export const templatePraticaRoute: Routes = [
  {
    path: '',
    component: TemplatePraticaComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.templatePratica.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TemplatePraticaDetailComponent,
    resolve: {
      templatePratica: TemplatePraticaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.templatePratica.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TemplatePraticaUpdateComponent,
    resolve: {
      templatePratica: TemplatePraticaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.templatePratica.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TemplatePraticaUpdateComponent,
    resolve: {
      templatePratica: TemplatePraticaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.templatePratica.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
