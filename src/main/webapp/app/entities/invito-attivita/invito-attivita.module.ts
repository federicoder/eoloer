import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EoloprjSharedModule } from 'app/shared/shared.module';
import { InvitoAttivitaComponent } from './invito-attivita.component';
import { InvitoAttivitaDetailComponent } from './invito-attivita-detail.component';
import { InvitoAttivitaUpdateComponent } from './invito-attivita-update.component';
import { InvitoAttivitaDeleteDialogComponent } from './invito-attivita-delete-dialog.component';
import { invitoAttivitaRoute } from './invito-attivita.route';

@NgModule({
  imports: [EoloprjSharedModule, RouterModule.forChild(invitoAttivitaRoute)],
  declarations: [
    InvitoAttivitaComponent,
    InvitoAttivitaDetailComponent,
    InvitoAttivitaUpdateComponent,
    InvitoAttivitaDeleteDialogComponent,
  ],
  entryComponents: [InvitoAttivitaDeleteDialogComponent],
})
export class EoloprjInvitoAttivitaModule {}
