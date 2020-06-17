import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EoloprjSharedModule } from 'app/shared/shared.module';
import { TipoAllegatoComponent } from './tipo-allegato.component';
import { TipoAllegatoDetailComponent } from './tipo-allegato-detail.component';
import { TipoAllegatoUpdateComponent } from './tipo-allegato-update.component';
import { TipoAllegatoDeleteDialogComponent } from './tipo-allegato-delete-dialog.component';
import { tipoAllegatoRoute } from './tipo-allegato.route';

@NgModule({
  imports: [EoloprjSharedModule, RouterModule.forChild(tipoAllegatoRoute)],
  declarations: [TipoAllegatoComponent, TipoAllegatoDetailComponent, TipoAllegatoUpdateComponent, TipoAllegatoDeleteDialogComponent],
  entryComponents: [TipoAllegatoDeleteDialogComponent],
})
export class EoloprjTipoAllegatoModule {}
