import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IRuoloOrganizzazione, RuoloOrganizzazione } from 'app/shared/model/ruolo-organizzazione.model';
import { RuoloOrganizzazioneService } from './ruolo-organizzazione.service';
import { RuoloOrganizzazioneComponent } from './ruolo-organizzazione.component';
import { RuoloOrganizzazioneDetailComponent } from './ruolo-organizzazione-detail.component';
import { RuoloOrganizzazioneUpdateComponent } from './ruolo-organizzazione-update.component';

@Injectable({ providedIn: 'root' })
export class RuoloOrganizzazioneResolve implements Resolve<IRuoloOrganizzazione> {
  constructor(private service: RuoloOrganizzazioneService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IRuoloOrganizzazione> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((ruoloOrganizzazione: HttpResponse<RuoloOrganizzazione>) => {
          if (ruoloOrganizzazione.body) {
            return of(ruoloOrganizzazione.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new RuoloOrganizzazione());
  }
}

export const ruoloOrganizzazioneRoute: Routes = [
  {
    path: '',
    component: RuoloOrganizzazioneComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.ruoloOrganizzazione.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: RuoloOrganizzazioneDetailComponent,
    resolve: {
      ruoloOrganizzazione: RuoloOrganizzazioneResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.ruoloOrganizzazione.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: RuoloOrganizzazioneUpdateComponent,
    resolve: {
      ruoloOrganizzazione: RuoloOrganizzazioneResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.ruoloOrganizzazione.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: RuoloOrganizzazioneUpdateComponent,
    resolve: {
      ruoloOrganizzazione: RuoloOrganizzazioneResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.ruoloOrganizzazione.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
