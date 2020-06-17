import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IStudioProfessionale, StudioProfessionale } from 'app/shared/model/studio-professionale.model';
import { StudioProfessionaleService } from './studio-professionale.service';
import { StudioProfessionaleComponent } from './studio-professionale.component';
import { StudioProfessionaleDetailComponent } from './studio-professionale-detail.component';
import { StudioProfessionaleUpdateComponent } from './studio-professionale-update.component';

@Injectable({ providedIn: 'root' })
export class StudioProfessionaleResolve implements Resolve<IStudioProfessionale> {
  constructor(private service: StudioProfessionaleService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IStudioProfessionale> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((studioProfessionale: HttpResponse<StudioProfessionale>) => {
          if (studioProfessionale.body) {
            return of(studioProfessionale.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new StudioProfessionale());
  }
}

export const studioProfessionaleRoute: Routes = [
  {
    path: '',
    component: StudioProfessionaleComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.studioProfessionale.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: StudioProfessionaleDetailComponent,
    resolve: {
      studioProfessionale: StudioProfessionaleResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.studioProfessionale.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: StudioProfessionaleUpdateComponent,
    resolve: {
      studioProfessionale: StudioProfessionaleResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.studioProfessionale.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: StudioProfessionaleUpdateComponent,
    resolve: {
      studioProfessionale: StudioProfessionaleResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.studioProfessionale.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
