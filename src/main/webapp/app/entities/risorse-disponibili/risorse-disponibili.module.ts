import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EoloprjSharedModule } from 'app/shared/shared.module';
import { RisorseDisponibiliComponent } from './risorse-disponibili.component';
import { RisorseDisponibiliDetailComponent } from './risorse-disponibili-detail.component';
import { RisorseDisponibiliUpdateComponent } from './risorse-disponibili-update.component';
import { RisorseDisponibiliDeleteDialogComponent } from './risorse-disponibili-delete-dialog.component';
import { risorseDisponibiliRoute } from './risorse-disponibili.route';

@NgModule({
  imports: [EoloprjSharedModule, RouterModule.forChild(risorseDisponibiliRoute)],
  declarations: [
    RisorseDisponibiliComponent,
    RisorseDisponibiliDetailComponent,
    RisorseDisponibiliUpdateComponent,
    RisorseDisponibiliDeleteDialogComponent,
  ],
  entryComponents: [RisorseDisponibiliDeleteDialogComponent],
})
export class EoloprjRisorseDisponibiliModule {}
