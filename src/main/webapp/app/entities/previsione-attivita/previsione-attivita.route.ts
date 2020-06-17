import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPrevisioneAttivita, PrevisioneAttivita } from 'app/shared/model/previsione-attivita.model';
import { PrevisioneAttivitaService } from './previsione-attivita.service';
import { PrevisioneAttivitaComponent } from './previsione-attivita.component';
import { PrevisioneAttivitaDetailComponent } from './previsione-attivita-detail.component';
import { PrevisioneAttivitaUpdateComponent } from './previsione-attivita-update.component';

@Injectable({ providedIn: 'root' })
export class PrevisioneAttivitaResolve implements Resolve<IPrevisioneAttivita> {
  constructor(private service: PrevisioneAttivitaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPrevisioneAttivita> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((previsioneAttivita: HttpResponse<PrevisioneAttivita>) => {
          if (previsioneAttivita.body) {
            return of(previsioneAttivita.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new PrevisioneAttivita());
  }
}

export const previsioneAttivitaRoute: Routes = [
  {
    path: '',
    component: PrevisioneAttivitaComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.previsioneAttivita.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: PrevisioneAttivitaDetailComponent,
    resolve: {
      previsioneAttivita: PrevisioneAttivitaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.previsioneAttivita.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: PrevisioneAttivitaUpdateComponent,
    resolve: {
      previsioneAttivita: PrevisioneAttivitaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.previsioneAttivita.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: PrevisioneAttivitaUpdateComponent,
    resolve: {
      previsioneAttivita: PrevisioneAttivitaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.previsioneAttivita.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
