import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEmailPersona, EmailPersona } from 'app/shared/model/email-persona.model';
import { EmailPersonaService } from './email-persona.service';
import { EmailPersonaComponent } from './email-persona.component';
import { EmailPersonaDetailComponent } from './email-persona-detail.component';
import { EmailPersonaUpdateComponent } from './email-persona-update.component';

@Injectable({ providedIn: 'root' })
export class EmailPersonaResolve implements Resolve<IEmailPersona> {
  constructor(private service: EmailPersonaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEmailPersona> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((emailPersona: HttpResponse<EmailPersona>) => {
          if (emailPersona.body) {
            return of(emailPersona.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EmailPersona());
  }
}

export const emailPersonaRoute: Routes = [
  {
    path: '',
    component: EmailPersonaComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.emailPersona.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EmailPersonaDetailComponent,
    resolve: {
      emailPersona: EmailPersonaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.emailPersona.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: EmailPersonaUpdateComponent,
    resolve: {
      emailPersona: EmailPersonaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.emailPersona.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: EmailPersonaUpdateComponent,
    resolve: {
      emailPersona: EmailPersonaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.emailPersona.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
