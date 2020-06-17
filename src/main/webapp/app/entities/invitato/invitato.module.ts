import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EoloprjSharedModule } from 'app/shared/shared.module';
import { InvitatoComponent } from './invitato.component';
import { InvitatoDetailComponent } from './invitato-detail.component';
import { InvitatoUpdateComponent } from './invitato-update.component';
import { InvitatoDeleteDialogComponent } from './invitato-delete-dialog.component';
import { invitatoRoute } from './invitato.route';

@NgModule({
  imports: [EoloprjSharedModule, RouterModule.forChild(invitatoRoute)],
  declarations: [InvitatoComponent, InvitatoDetailComponent, InvitatoUpdateComponent, InvitatoDeleteDialogComponent],
  entryComponents: [InvitatoDeleteDialogComponent],
})
export class EoloprjInvitatoModule {}
