import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IConsuntivoTask, ConsuntivoTask } from 'app/shared/model/consuntivo-task.model';
import { ConsuntivoTaskService } from './consuntivo-task.service';
import { ConsuntivoTaskComponent } from './consuntivo-task.component';
import { ConsuntivoTaskDetailComponent } from './consuntivo-task-detail.component';
import { ConsuntivoTaskUpdateComponent } from './consuntivo-task-update.component';

@Injectable({ providedIn: 'root' })
export class ConsuntivoTaskResolve implements Resolve<IConsuntivoTask> {
  constructor(private service: ConsuntivoTaskService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IConsuntivoTask> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((consuntivoTask: HttpResponse<ConsuntivoTask>) => {
          if (consuntivoTask.body) {
            return of(consuntivoTask.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ConsuntivoTask());
  }
}

export const consuntivoTaskRoute: Routes = [
  {
    path: '',
    component: ConsuntivoTaskComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.consuntivoTask.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ConsuntivoTaskDetailComponent,
    resolve: {
      consuntivoTask: ConsuntivoTaskResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.consuntivoTask.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ConsuntivoTaskUpdateComponent,
    resolve: {
      consuntivoTask: ConsuntivoTaskResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.consuntivoTask.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ConsuntivoTaskUpdateComponent,
    resolve: {
      consuntivoTask: ConsuntivoTaskResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.consuntivoTask.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
