import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EoloprjSharedModule } from 'app/shared/shared.module';
import { InvitoPraticaComponent } from './invito-pratica.component';
import { InvitoPraticaDetailComponent } from './invito-pratica-detail.component';
import { InvitoPraticaUpdateComponent } from './invito-pratica-update.component';
import { InvitoPraticaDeleteDialogComponent } from './invito-pratica-delete-dialog.component';
import { invitoPraticaRoute } from './invito-pratica.route';

@NgModule({
  imports: [EoloprjSharedModule, RouterModule.forChild(invitoPraticaRoute)],
  declarations: [InvitoPraticaComponent, InvitoPraticaDetailComponent, InvitoPraticaUpdateComponent, InvitoPraticaDeleteDialogComponent],
  entryComponents: [InvitoPraticaDeleteDialogComponent],
})
export class EoloprjInvitoPraticaModule {}
