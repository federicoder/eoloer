import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IAllegatoTask, AllegatoTask } from 'app/shared/model/allegato-task.model';
import { AllegatoTaskService } from './allegato-task.service';
import { AllegatoTaskComponent } from './allegato-task.component';
import { AllegatoTaskDetailComponent } from './allegato-task-detail.component';
import { AllegatoTaskUpdateComponent } from './allegato-task-update.component';

@Injectable({ providedIn: 'root' })
export class AllegatoTaskResolve implements Resolve<IAllegatoTask> {
  constructor(private service: AllegatoTaskService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAllegatoTask> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((allegatoTask: HttpResponse<AllegatoTask>) => {
          if (allegatoTask.body) {
            return of(allegatoTask.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new AllegatoTask());
  }
}

export const allegatoTaskRoute: Routes = [
  {
    path: '',
    component: AllegatoTaskComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.allegatoTask.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AllegatoTaskDetailComponent,
    resolve: {
      allegatoTask: AllegatoTaskResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.allegatoTask.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AllegatoTaskUpdateComponent,
    resolve: {
      allegatoTask: AllegatoTaskResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.allegatoTask.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AllegatoTaskUpdateComponent,
    resolve: {
      allegatoTask: AllegatoTaskResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eoloprjApp.allegatoTask.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
