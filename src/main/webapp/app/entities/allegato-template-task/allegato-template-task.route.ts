import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IAllegatoTemplateTask, AllegatoTemplateTask } from 'app/shared/model/allegato-template-task.model';
import { AllegatoTemplateTaskService } from './allegato-template-task.service';
import { AllegatoTemplateTaskComponent } from './allegato-template-task.component';
import { AllegatoTemplateTaskDetailComponent } from './allegato-template-task-detail.component';
import { AllegatoTemplateTaskUpdateComponent } from './allegato-template-task-update.component';

@Injectable({ providedIn: 'root' })
export class AllegatoTemplateTaskResolve implements Resolve<IAllegatoTemplateTask> {
  constructor(private service: AllegatoTemplateTaskService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAllegatoTemplateTask> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((allegatoTemplateTask: HttpResponse<AllegatoTemplateTask>) => {
          if (allegatoTemplateTask.body) {
            return of(allegatoTemplateTask.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new AllegatoTemplateTask());
  }
}

export const allegatoTemplateTaskRoute: Routes = [
  {
    path: '',
    component: AllegatoTemplateTaskComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.allegatoTemplateTask.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AllegatoTemplateTaskDetailComponent,
    resolve: {
      allegatoTemplateTask: AllegatoTemplateTaskResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.allegatoTemplateTask.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AllegatoTemplateTaskUpdateComponent,
    resolve: {
      allegatoTemplateTask: AllegatoTemplateTaskResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.allegatoTemplateTask.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AllegatoTemplateTaskUpdateComponent,
    resolve: {
      allegatoTemplateTask: AllegatoTemplateTaskResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.allegatoTemplateTask.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
