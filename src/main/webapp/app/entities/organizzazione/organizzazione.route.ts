import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IOrganizzazione, Organizzazione } from 'app/shared/model/organizzazione.model';
import { OrganizzazioneService } from './organizzazione.service';
import { OrganizzazioneComponent } from './organizzazione.component';
import { OrganizzazioneDetailComponent } from './organizzazione-detail.component';
import { OrganizzazioneUpdateComponent } from './organizzazione-update.component';

@Injectable({ providedIn: 'root' })
export class OrganizzazioneResolve implements Resolve<IOrganizzazione> {
  constructor(private service: OrganizzazioneService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IOrganizzazione> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((organizzazione: HttpResponse<Organizzazione>) => {
          if (organizzazione.body) {
            return of(organizzazione.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Organizzazione());
  }
}

export const organizzazioneRoute: Routes = [
  {
    path: '',
    component: OrganizzazioneComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.organizzazione.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: OrganizzazioneDetailComponent,
    resolve: {
      organizzazione: OrganizzazioneResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.organizzazione.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: OrganizzazioneUpdateComponent,
    resolve: {
      organizzazione: OrganizzazioneResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.organizzazione.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: OrganizzazioneUpdateComponent,
    resolve: {
      organizzazione: OrganizzazioneResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.organizzazione.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
