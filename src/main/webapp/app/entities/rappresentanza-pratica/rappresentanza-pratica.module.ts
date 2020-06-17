import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EoloprjSharedModule } from 'app/shared/shared.module';
import { RappresentanzaPraticaComponent } from './rappresentanza-pratica.component';
import { RappresentanzaPraticaDetailComponent } from './rappresentanza-pratica-detail.component';
import { RappresentanzaPraticaUpdateComponent } from './rappresentanza-pratica-update.component';
import { RappresentanzaPraticaDeleteDialogComponent } from './rappresentanza-pratica-delete-dialog.component';
import { rappresentanzaPraticaRoute } from './rappresentanza-pratica.route';

@NgModule({
  imports: [EoloprjSharedModule, RouterModule.forChild(rappresentanzaPraticaRoute)],
  declarations: [
    RappresentanzaPraticaComponent,
    RappresentanzaPraticaDetailComponent,
    RappresentanzaPraticaUpdateComponent,
    RappresentanzaPraticaDeleteDialogComponent,
  ],
  entryComponents: [RappresentanzaPraticaDeleteDialogComponent],
})
export class EoloprjRappresentanzaPraticaModule {}
