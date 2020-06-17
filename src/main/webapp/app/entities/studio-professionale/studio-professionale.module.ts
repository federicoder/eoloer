import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EoloprjSharedModule } from 'app/shared/shared.module';
import { StudioProfessionaleComponent } from './studio-professionale.component';
import { StudioProfessionaleDetailComponent } from './studio-professionale-detail.component';
import { StudioProfessionaleUpdateComponent } from './studio-professionale-update.component';
import { StudioProfessionaleDeleteDialogComponent } from './studio-professionale-delete-dialog.component';
import { studioProfessionaleRoute } from './studio-professionale.route';

@NgModule({
  imports: [EoloprjSharedModule, RouterModule.forChild(studioProfessionaleRoute)],
  declarations: [
    StudioProfessionaleComponent,
    StudioProfessionaleDetailComponent,
    StudioProfessionaleUpdateComponent,
    StudioProfessionaleDeleteDialogComponent,
  ],
  entryComponents: [StudioProfessionaleDeleteDialogComponent],
})
export class EoloprjStudioProfessionaleModule {}
