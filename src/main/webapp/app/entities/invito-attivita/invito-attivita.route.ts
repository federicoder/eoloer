import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IInvitoAttivita, InvitoAttivita } from 'app/shared/model/invito-attivita.model';
import { InvitoAttivitaService } from './invito-attivita.service';
import { InvitoAttivitaComponent } from './invito-attivita.component';
import { InvitoAttivitaDetailComponent } from './invito-attivita-detail.component';
import { InvitoAttivitaUpdateComponent } from './invito-attivita-update.component';

@Injectable({ providedIn: 'root' })
export class InvitoAttivitaResolve implements Resolve<IInvitoAttivita> {
  constructor(private service: InvitoAttivitaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IInvitoAttivita> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((invitoAttivita: HttpResponse<InvitoAttivita>) => {
          if (invitoAttivita.body) {
            return of(invitoAttivita.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new InvitoAttivita());
  }
}

export const invitoAttivitaRoute: Routes = [
  {
    path: '',
    component: InvitoAttivitaComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.invitoAttivita.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: InvitoAttivitaDetailComponent,
    resolve: {
      invitoAttivita: InvitoAttivitaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.invitoAttivita.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: InvitoAttivitaUpdateComponent,
    resolve: {
      invitoAttivita: InvitoAttivitaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.invitoAttivita.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: InvitoAttivitaUpdateComponent,
    resolve: {
      invitoAttivita: InvitoAttivitaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.invitoAttivita.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
