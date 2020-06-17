import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EoloprjSharedModule } from 'app/shared/shared.module';
import { ConsuntivoTaskComponent } from './consuntivo-task.component';
import { ConsuntivoTaskDetailComponent } from './consuntivo-task-detail.component';
import { ConsuntivoTaskUpdateComponent } from './consuntivo-task-update.component';
import { ConsuntivoTaskDeleteDialogComponent } from './consuntivo-task-delete-dialog.component';
import { consuntivoTaskRoute } from './consuntivo-task.route';

@NgModule({
  imports: [EoloprjSharedModule, RouterModule.forChild(consuntivoTaskRoute)],
  declarations: [
    ConsuntivoTaskComponent,
    ConsuntivoTaskDetailComponent,
    ConsuntivoTaskUpdateComponent,
    ConsuntivoTaskDeleteDialogComponent,
  ],
  entryComponents: [ConsuntivoTaskDeleteDialogComponent],
})
export class EoloprjConsuntivoTaskModule {}
