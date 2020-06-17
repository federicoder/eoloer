import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EoloprjSharedModule } from 'app/shared/shared.module';
import { IndirizzoPersonaComponent } from './indirizzo-persona.component';
import { IndirizzoPersonaDetailComponent } from './indirizzo-persona-detail.component';
import { IndirizzoPersonaUpdateComponent } from './indirizzo-persona-update.component';
import { IndirizzoPersonaDeleteDialogComponent } from './indirizzo-persona-delete-dialog.component';
import { indirizzoPersonaRoute } from './indirizzo-persona.route';

@NgModule({
  imports: [EoloprjSharedModule, RouterModule.forChild(indirizzoPersonaRoute)],
  declarations: [
    IndirizzoPersonaComponent,
    IndirizzoPersonaDetailComponent,
    IndirizzoPersonaUpdateComponent,
    IndirizzoPersonaDeleteDialogComponent,
  ],
  entryComponents: [IndirizzoPersonaDeleteDialogComponent],
})
export class EoloprjIndirizzoPersonaModule {}
