import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EoloprjSharedModule } from 'app/shared/shared.module';
import { PersonaFisicaComponent } from './persona-fisica.component';
import { PersonaFisicaDetailComponent } from './persona-fisica-detail.component';
import { PersonaFisicaUpdateComponent } from './persona-fisica-update.component';
import { PersonaFisicaDeleteDialogComponent } from './persona-fisica-delete-dialog.component';
import { personaFisicaRoute } from './persona-fisica.route';

@NgModule({
  imports: [EoloprjSharedModule, RouterModule.forChild(personaFisicaRoute)],
  declarations: [PersonaFisicaComponent, PersonaFisicaDetailComponent, PersonaFisicaUpdateComponent, PersonaFisicaDeleteDialogComponent],
  entryComponents: [PersonaFisicaDeleteDialogComponent],
})
export class EoloprjPersonaFisicaModule {}
