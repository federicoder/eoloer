import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IAssegnazioneTask, AssegnazioneTask } from 'app/shared/model/assegnazione-task.model';
import { AssegnazioneTaskService } from './assegnazione-task.service';
import { AssegnazioneTaskComponent } from './assegnazione-task.component';
import { AssegnazioneTaskDetailComponent } from './assegnazione-task-detail.component';
import { AssegnazioneTaskUpdateComponent } from './assegnazione-task-update.component';

@Injectable({ providedIn: 'root' })
export class AssegnazioneTaskResolve implements Resolve<IAssegnazioneTask> {
  constructor(private service: AssegnazioneTaskService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAssegnazioneTask> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((assegnazioneTask: HttpResponse<AssegnazioneTask>) => {
          if (assegnazioneTask.body) {
            return of(assegnazioneTask.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new AssegnazioneTask());
  }
}

export const assegnazioneTaskRoute: Routes = [
  {
    path: '',
    component: AssegnazioneTaskComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.assegnazioneTask.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AssegnazioneTaskDetailComponent,
    resolve: {
      assegnazioneTask: AssegnazioneTaskResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.assegnazioneTask.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AssegnazioneTaskUpdateComponent,
    resolve: {
      assegnazioneTask: AssegnazioneTaskResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.assegnazioneTask.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AssegnazioneTaskUpdateComponent,
    resolve: {
      assegnazioneTask: AssegnazioneTaskResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.assegnazioneTask.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
