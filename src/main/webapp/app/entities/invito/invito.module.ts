import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EoloprjSharedModule } from 'app/shared/shared.module';
import { InvitoComponent } from './invito.component';
import { InvitoDetailComponent } from './invito-detail.component';
import { InvitoUpdateComponent } from './invito-update.component';
import { InvitoDeleteDialogComponent } from './invito-delete-dialog.component';
import { invitoRoute } from './invito.route';

@NgModule({
  imports: [EoloprjSharedModule, RouterModule.forChild(invitoRoute)],
  declarations: [InvitoComponent, InvitoDetailComponent, InvitoUpdateComponent, InvitoDeleteDialogComponent],
  entryComponents: [InvitoDeleteDialogComponent],
})
export class EoloprjInvitoModule {}
