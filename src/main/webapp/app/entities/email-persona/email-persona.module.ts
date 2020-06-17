import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EoloprjSharedModule } from 'app/shared/shared.module';
import { EmailPersonaComponent } from './email-persona.component';
import { EmailPersonaDetailComponent } from './email-persona-detail.component';
import { EmailPersonaUpdateComponent } from './email-persona-update.component';
import { EmailPersonaDeleteDialogComponent } from './email-persona-delete-dialog.component';
import { emailPersonaRoute } from './email-persona.route';

@NgModule({
  imports: [EoloprjSharedModule, RouterModule.forChild(emailPersonaRoute)],
  declarations: [EmailPersonaComponent, EmailPersonaDetailComponent, EmailPersonaUpdateComponent, EmailPersonaDeleteDialogComponent],
  entryComponents: [EmailPersonaDeleteDialogComponent],
})
export class EoloprjEmailPersonaModule {}
