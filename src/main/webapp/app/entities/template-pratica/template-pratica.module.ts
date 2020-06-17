import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EoloprjSharedModule } from 'app/shared/shared.module';
import { TemplatePraticaComponent } from './template-pratica.component';
import { TemplatePraticaDetailComponent } from './template-pratica-detail.component';
import { TemplatePraticaUpdateComponent } from './template-pratica-update.component';
import { TemplatePraticaDeleteDialogComponent } from './template-pratica-delete-dialog.component';
import { templatePraticaRoute } from './template-pratica.route';

@NgModule({
  imports: [EoloprjSharedModule, RouterModule.forChild(templatePraticaRoute)],
  declarations: [
    TemplatePraticaComponent,
    TemplatePraticaDetailComponent,
    TemplatePraticaUpdateComponent,
    TemplatePraticaDeleteDialogComponent,
  ],
  entryComponents: [TemplatePraticaDeleteDialogComponent],
})
export class EoloprjTemplatePraticaModule {}
