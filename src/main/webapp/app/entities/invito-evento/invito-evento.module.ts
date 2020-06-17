import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EoloprjSharedModule } from 'app/shared/shared.module';
import { InvitoEventoComponent } from './invito-evento.component';
import { InvitoEventoDetailComponent } from './invito-evento-detail.component';
import { InvitoEventoUpdateComponent } from './invito-evento-update.component';
import { InvitoEventoDeleteDialogComponent } from './invito-evento-delete-dialog.component';
import { invitoEventoRoute } from './invito-evento.route';

@NgModule({
  imports: [EoloprjSharedModule, RouterModule.forChild(invitoEventoRoute)],
  declarations: [InvitoEventoComponent, InvitoEventoDetailComponent, InvitoEventoUpdateComponent, InvitoEventoDeleteDialogComponent],
  entryComponents: [InvitoEventoDeleteDialogComponent],
})
export class EoloprjInvitoEventoModule {}
