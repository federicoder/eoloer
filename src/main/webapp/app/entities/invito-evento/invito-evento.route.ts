import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IInvitoEvento, InvitoEvento } from 'app/shared/model/invito-evento.model';
import { InvitoEventoService } from './invito-evento.service';
import { InvitoEventoComponent } from './invito-evento.component';
import { InvitoEventoDetailComponent } from './invito-evento-detail.component';
import { InvitoEventoUpdateComponent } from './invito-evento-update.component';

@Injectable({ providedIn: 'root' })
export class InvitoEventoResolve implements Resolve<IInvitoEvento> {
  constructor(private service: InvitoEventoService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IInvitoEvento> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((invitoEvento: HttpResponse<InvitoEvento>) => {
          if (invitoEvento.body) {
            return of(invitoEvento.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new InvitoEvento());
  }
}

export const invitoEventoRoute: Routes = [
  {
    path: '',
    component: InvitoEventoComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.invitoEvento.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: InvitoEventoDetailComponent,
    resolve: {
      invitoEvento: InvitoEventoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.invitoEvento.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: InvitoEventoUpdateComponent,
    resolve: {
      invitoEvento: InvitoEventoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.invitoEvento.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: InvitoEventoUpdateComponent,
    resolve: {
      invitoEvento: InvitoEventoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.invitoEvento.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
