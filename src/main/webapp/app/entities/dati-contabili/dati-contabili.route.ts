import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDatiContabili, DatiContabili } from 'app/shared/model/dati-contabili.model';
import { DatiContabiliService } from './dati-contabili.service';
import { DatiContabiliComponent } from './dati-contabili.component';
import { DatiContabiliDetailComponent } from './dati-contabili-detail.component';
import { DatiContabiliUpdateComponent } from './dati-contabili-update.component';

@Injectable({ providedIn: 'root' })
export class DatiContabiliResolve implements Resolve<IDatiContabili> {
  constructor(private service: DatiContabiliService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDatiContabili> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((datiContabili: HttpResponse<DatiContabili>) => {
          if (datiContabili.body) {
            return of(datiContabili.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new DatiContabili());
  }
}

export const datiContabiliRoute: Routes = [
  {
    path: '',
    component: DatiContabiliComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.datiContabili.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DatiContabiliDetailComponent,
    resolve: {
      datiContabili: DatiContabiliResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.datiContabili.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DatiContabiliUpdateComponent,
    resolve: {
      datiContabili: DatiContabiliResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.datiContabili.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DatiContabiliUpdateComponent,
    resolve: {
      datiContabili: DatiContabiliResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.datiContabili.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
