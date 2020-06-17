import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EoloprjSharedModule } from 'app/shared/shared.module';
import { NotaTaskComponent } from './nota-task.component';
import { NotaTaskDetailComponent } from './nota-task-detail.component';
import { NotaTaskUpdateComponent } from './nota-task-update.component';
import { NotaTaskDeleteDialogComponent } from './nota-task-delete-dialog.component';
import { notaTaskRoute } from './nota-task.route';

@NgModule({
  imports: [EoloprjSharedModule, RouterModule.forChild(notaTaskRoute)],
  declarations: [NotaTaskComponent, NotaTaskDetailComponent, NotaTaskUpdateComponent, NotaTaskDeleteDialogComponent],
  entryComponents: [NotaTaskDeleteDialogComponent],
})
export class EoloprjNotaTaskModule {}
