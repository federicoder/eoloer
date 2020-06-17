import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EoloprjSharedModule } from 'app/shared/shared.module';
import { PrevisioneAttivitaComponent } from './previsione-attivita.component';
import { PrevisioneAttivitaDetailComponent } from './previsione-attivita-detail.component';
import { PrevisioneAttivitaUpdateComponent } from './previsione-attivita-update.component';
import { PrevisioneAttivitaDeleteDialogComponent } from './previsione-attivita-delete-dialog.component';
import { previsioneAttivitaRoute } from './previsione-attivita.route';

@NgModule({
  imports: [EoloprjSharedModule, RouterModule.forChild(previsioneAttivitaRoute)],
  declarations: [
    PrevisioneAttivitaComponent,
    PrevisioneAttivitaDetailComponent,
    PrevisioneAttivitaUpdateComponent,
    PrevisioneAttivitaDeleteDialogComponent,
  ],
  entryComponents: [PrevisioneAttivitaDeleteDialogComponent],
})
export class EoloprjPrevisioneAttivitaModule {}
