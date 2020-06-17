import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EoloprjSharedModule } from 'app/shared/shared.module';
import { CondivisionePraticaComponent } from './condivisione-pratica.component';
import { CondivisionePraticaDetailComponent } from './condivisione-pratica-detail.component';
import { CondivisionePraticaUpdateComponent } from './condivisione-pratica-update.component';
import { CondivisionePraticaDeleteDialogComponent } from './condivisione-pratica-delete-dialog.component';
import { condivisionePraticaRoute } from './condivisione-pratica.route';

@NgModule({
  imports: [EoloprjSharedModule, RouterModule.forChild(condivisionePraticaRoute)],
  declarations: [
    CondivisionePraticaComponent,
    CondivisionePraticaDetailComponent,
    CondivisionePraticaUpdateComponent,
    CondivisionePraticaDeleteDialogComponent,
  ],
  entryComponents: [CondivisionePraticaDeleteDialogComponent],
})
export class EoloprjCondivisionePraticaModule {}
