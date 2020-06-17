import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EoloprjSharedModule } from 'app/shared/shared.module';
import { PrevisioneEventoComponent } from './previsione-evento.component';
import { PrevisioneEventoDetailComponent } from './previsione-evento-detail.component';
import { PrevisioneEventoUpdateComponent } from './previsione-evento-update.component';
import { PrevisioneEventoDeleteDialogComponent } from './previsione-evento-delete-dialog.component';
import { previsioneEventoRoute } from './previsione-evento.route';

@NgModule({
  imports: [EoloprjSharedModule, RouterModule.forChild(previsioneEventoRoute)],
  declarations: [
    PrevisioneEventoComponent,
    PrevisioneEventoDetailComponent,
    PrevisioneEventoUpdateComponent,
    PrevisioneEventoDeleteDialogComponent,
  ],
  entryComponents: [PrevisioneEventoDeleteDialogComponent],
})
export class EoloprjPrevisioneEventoModule {}
