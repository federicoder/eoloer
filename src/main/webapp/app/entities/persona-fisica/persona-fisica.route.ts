import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPersonaFisica, PersonaFisica } from 'app/shared/model/persona-fisica.model';
import { PersonaFisicaService } from './persona-fisica.service';
import { PersonaFisicaComponent } from './persona-fisica.component';
import { PersonaFisicaDetailComponent } from './persona-fisica-detail.component';
import { PersonaFisicaUpdateComponent } from './persona-fisica-update.component';

@Injectable({ providedIn: 'root' })
export class PersonaFisicaResolve implements Resolve<IPersonaFisica> {
  constructor(private service: PersonaFisicaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPersonaFisica> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((personaFisica: HttpResponse<PersonaFisica>) => {
          if (personaFisica.body) {
            return of(personaFisica.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new PersonaFisica());
  }
}

export const personaFisicaRoute: Routes = [
  {
    path: '',
    component: PersonaFisicaComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.personaFisica.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: PersonaFisicaDetailComponent,
    resolve: {
      personaFisica: PersonaFisicaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.personaFisica.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: PersonaFisicaUpdateComponent,
    resolve: {
      personaFisica: PersonaFisicaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.personaFisica.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: PersonaFisicaUpdateComponent,
    resolve: {
      personaFisica: PersonaFisicaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.personaFisica.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
