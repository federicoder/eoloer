import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EoloprjSharedModule } from 'app/shared/shared.module';
import { UserPersonaComponent } from './user-persona.component';
import { UserPersonaDetailComponent } from './user-persona-detail.component';
import { UserPersonaUpdateComponent } from './user-persona-update.component';
import { UserPersonaDeleteDialogComponent } from './user-persona-delete-dialog.component';
import { userPersonaRoute } from './user-persona.route';

@NgModule({
  imports: [EoloprjSharedModule, RouterModule.forChild(userPersonaRoute)],
  declarations: [UserPersonaComponent, UserPersonaDetailComponent, UserPersonaUpdateComponent, UserPersonaDeleteDialogComponent],
  entryComponents: [UserPersonaDeleteDialogComponent],
})
export class EoloprjUserPersonaModule {}
