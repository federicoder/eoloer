import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EoloprjSharedModule } from 'app/shared/shared.module';
import { TelefonoPersonaComponent } from './telefono-persona.component';
import { TelefonoPersonaDetailComponent } from './telefono-persona-detail.component';
import { TelefonoPersonaUpdateComponent } from './telefono-persona-update.component';
import { TelefonoPersonaDeleteDialogComponent } from './telefono-persona-delete-dialog.component';
import { telefonoPersonaRoute } from './telefono-persona.route';

@NgModule({
  imports: [EoloprjSharedModule, RouterModule.forChild(telefonoPersonaRoute)],
  declarations: [
    TelefonoPersonaComponent,
    TelefonoPersonaDetailComponent,
    TelefonoPersonaUpdateComponent,
    TelefonoPersonaDeleteDialogComponent,
  ],
  entryComponents: [TelefonoPersonaDeleteDialogComponent],
})
export class EoloprjTelefonoPersonaModule {}
