import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EoloprjSharedModule } from 'app/shared/shared.module';
import { OrganizzazioneComponent } from './organizzazione.component';
import { OrganizzazioneDetailComponent } from './organizzazione-detail.component';
import { OrganizzazioneUpdateComponent } from './organizzazione-update.component';
import { OrganizzazioneDeleteDialogComponent } from './organizzazione-delete-dialog.component';
import { organizzazioneRoute } from './organizzazione.route';

@NgModule({
  imports: [EoloprjSharedModule, RouterModule.forChild(organizzazioneRoute)],
  declarations: [
    OrganizzazioneComponent,
    OrganizzazioneDetailComponent,
    OrganizzazioneUpdateComponent,
    OrganizzazioneDeleteDialogComponent,
  ],
  entryComponents: [OrganizzazioneDeleteDialogComponent],
})
export class EoloprjOrganizzazioneModule {}
