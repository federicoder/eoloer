import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EoloprjSharedModule } from 'app/shared/shared.module';
import { RuoloOrganizzazioneComponent } from './ruolo-organizzazione.component';
import { RuoloOrganizzazioneDetailComponent } from './ruolo-organizzazione-detail.component';
import { RuoloOrganizzazioneUpdateComponent } from './ruolo-organizzazione-update.component';
import { RuoloOrganizzazioneDeleteDialogComponent } from './ruolo-organizzazione-delete-dialog.component';
import { ruoloOrganizzazioneRoute } from './ruolo-organizzazione.route';

@NgModule({
  imports: [EoloprjSharedModule, RouterModule.forChild(ruoloOrganizzazioneRoute)],
  declarations: [
    RuoloOrganizzazioneComponent,
    RuoloOrganizzazioneDetailComponent,
    RuoloOrganizzazioneUpdateComponent,
    RuoloOrganizzazioneDeleteDialogComponent,
  ],
  entryComponents: [RuoloOrganizzazioneDeleteDialogComponent],
})
export class EoloprjRuoloOrganizzazioneModule {}
