import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPrevisioneTask, PrevisioneTask } from 'app/shared/model/previsione-task.model';
import { PrevisioneTaskService } from './previsione-task.service';
import { PrevisioneTaskComponent } from './previsione-task.component';
import { PrevisioneTaskDetailComponent } from './previsione-task-detail.component';
import { PrevisioneTaskUpdateComponent } from './previsione-task-update.component';

@Injectable({ providedIn: 'root' })
export class PrevisioneTaskResolve implements Resolve<IPrevisioneTask> {
  constructor(private service: PrevisioneTaskService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPrevisioneTask> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((previsioneTask: HttpResponse<PrevisioneTask>) => {
          if (previsioneTask.body) {
            return of(previsioneTask.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new PrevisioneTask());
  }
}

export const previsioneTaskRoute: Routes = [
  {
    path: '',
    component: PrevisioneTaskComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.previsioneTask.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: PrevisioneTaskDetailComponent,
    resolve: {
      previsioneTask: PrevisioneTaskResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.previsioneTask.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: PrevisioneTaskUpdateComponent,
    resolve: {
      previsioneTask: PrevisioneTaskResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.previsioneTask.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: PrevisioneTaskUpdateComponent,
    resolve: {
      previsioneTask: PrevisioneTaskResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.previsioneTask.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
