import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { INotaTask, NotaTask } from 'app/shared/model/nota-task.model';
import { NotaTaskService } from './nota-task.service';
import { NotaTaskComponent } from './nota-task.component';
import { NotaTaskDetailComponent } from './nota-task-detail.component';
import { NotaTaskUpdateComponent } from './nota-task-update.component';

@Injectable({ providedIn: 'root' })
export class NotaTaskResolve implements Resolve<INotaTask> {
  constructor(private service: NotaTaskService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<INotaTask> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((notaTask: HttpResponse<NotaTask>) => {
          if (notaTask.body) {
            return of(notaTask.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new NotaTask());
  }
}

export const notaTaskRoute: Routes = [
  {
    path: '',
    component: NotaTaskComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.notaTask.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: NotaTaskDetailComponent,
    resolve: {
      notaTask: NotaTaskResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.notaTask.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: NotaTaskUpdateComponent,
    resolve: {
      notaTask: NotaTaskResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.notaTask.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: NotaTaskUpdateComponent,
    resolve: {
      notaTask: NotaTaskResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.notaTask.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
