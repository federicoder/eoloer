import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EoloprjSharedModule } from 'app/shared/shared.module';
import { AllegatoTemplateTaskComponent } from './allegato-template-task.component';
import { AllegatoTemplateTaskDetailComponent } from './allegato-template-task-detail.component';
import { AllegatoTemplateTaskUpdateComponent } from './allegato-template-task-update.component';
import { AllegatoTemplateTaskDeleteDialogComponent } from './allegato-template-task-delete-dialog.component';
import { allegatoTemplateTaskRoute } from './allegato-template-task.route';

@NgModule({
  imports: [EoloprjSharedModule, RouterModule.forChild(allegatoTemplateTaskRoute)],
  declarations: [
    AllegatoTemplateTaskComponent,
    AllegatoTemplateTaskDetailComponent,
    AllegatoTemplateTaskUpdateComponent,
    AllegatoTemplateTaskDeleteDialogComponent,
  ],
  entryComponents: [AllegatoTemplateTaskDeleteDialogComponent],
})
export class EoloprjAllegatoTemplateTaskModule {}
