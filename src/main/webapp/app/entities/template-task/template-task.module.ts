import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EoloprjSharedModule } from 'app/shared/shared.module';
import { TemplateTaskComponent } from './template-task.component';
import { TemplateTaskDetailComponent } from './template-task-detail.component';
import { TemplateTaskUpdateComponent } from './template-task-update.component';
import { TemplateTaskDeleteDialogComponent } from './template-task-delete-dialog.component';
import { templateTaskRoute } from './template-task.route';

@NgModule({
  imports: [EoloprjSharedModule, RouterModule.forChild(templateTaskRoute)],
  declarations: [TemplateTaskComponent, TemplateTaskDetailComponent, TemplateTaskUpdateComponent, TemplateTaskDeleteDialogComponent],
  entryComponents: [TemplateTaskDeleteDialogComponent],
})
export class EoloprjTemplateTaskModule {}
