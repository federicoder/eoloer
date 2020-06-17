import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EoloprjSharedModule } from 'app/shared/shared.module';
import { AssegnazioneTaskComponent } from './assegnazione-task.component';
import { AssegnazioneTaskDetailComponent } from './assegnazione-task-detail.component';
import { AssegnazioneTaskUpdateComponent } from './assegnazione-task-update.component';
import { AssegnazioneTaskDeleteDialogComponent } from './assegnazione-task-delete-dialog.component';
import { assegnazioneTaskRoute } from './assegnazione-task.route';

@NgModule({
  imports: [EoloprjSharedModule, RouterModule.forChild(assegnazioneTaskRoute)],
  declarations: [
    AssegnazioneTaskComponent,
    AssegnazioneTaskDetailComponent,
    AssegnazioneTaskUpdateComponent,
    AssegnazioneTaskDeleteDialogComponent,
  ],
  entryComponents: [AssegnazioneTaskDeleteDialogComponent],
})
export class EoloprjAssegnazioneTaskModule {}
