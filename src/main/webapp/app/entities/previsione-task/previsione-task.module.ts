import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EoloprjSharedModule } from 'app/shared/shared.module';
import { PrevisioneTaskComponent } from './previsione-task.component';
import { PrevisioneTaskDetailComponent } from './previsione-task-detail.component';
import { PrevisioneTaskUpdateComponent } from './previsione-task-update.component';
import { PrevisioneTaskDeleteDialogComponent } from './previsione-task-delete-dialog.component';
import { previsioneTaskRoute } from './previsione-task.route';

@NgModule({
  imports: [EoloprjSharedModule, RouterModule.forChild(previsioneTaskRoute)],
  declarations: [
    PrevisioneTaskComponent,
    PrevisioneTaskDetailComponent,
    PrevisioneTaskUpdateComponent,
    PrevisioneTaskDeleteDialogComponent,
  ],
  entryComponents: [PrevisioneTaskDeleteDialogComponent],
})
export class EoloprjPrevisioneTaskModule {}
