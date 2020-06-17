import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EoloprjSharedModule } from 'app/shared/shared.module';
import { AllegatoTaskComponent } from './allegato-task.component';
import { AllegatoTaskDetailComponent } from './allegato-task-detail.component';
import { AllegatoTaskUpdateComponent } from './allegato-task-update.component';
import { AllegatoTaskDeleteDialogComponent } from './allegato-task-delete-dialog.component';
import { allegatoTaskRoute } from './allegato-task.route';

@NgModule({
  imports: [EoloprjSharedModule, RouterModule.forChild(allegatoTaskRoute)],
  declarations: [AllegatoTaskComponent, AllegatoTaskDetailComponent, AllegatoTaskUpdateComponent, AllegatoTaskDeleteDialogComponent],
  entryComponents: [AllegatoTaskDeleteDialogComponent],
})
export class EoloprjAllegatoTaskModule {}
