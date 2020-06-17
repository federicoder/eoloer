import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EoloprjSharedModule } from 'app/shared/shared.module';
import { NotaPraticaComponent } from './nota-pratica.component';
import { NotaPraticaDetailComponent } from './nota-pratica-detail.component';
import { NotaPraticaUpdateComponent } from './nota-pratica-update.component';
import { NotaPraticaDeleteDialogComponent } from './nota-pratica-delete-dialog.component';
import { notaPraticaRoute } from './nota-pratica.route';

@NgModule({
  imports: [EoloprjSharedModule, RouterModule.forChild(notaPraticaRoute)],
  declarations: [NotaPraticaComponent, NotaPraticaDetailComponent, NotaPraticaUpdateComponent, NotaPraticaDeleteDialogComponent],
  entryComponents: [NotaPraticaDeleteDialogComponent],
})
export class EoloprjNotaPraticaModule {}
