import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EoloprjSharedModule } from 'app/shared/shared.module';
import { TagPersonaComponent } from './tag-persona.component';
import { TagPersonaDetailComponent } from './tag-persona-detail.component';
import { TagPersonaUpdateComponent } from './tag-persona-update.component';
import { TagPersonaDeleteDialogComponent } from './tag-persona-delete-dialog.component';
import { tagPersonaRoute } from './tag-persona.route';

@NgModule({
  imports: [EoloprjSharedModule, RouterModule.forChild(tagPersonaRoute)],
  declarations: [TagPersonaComponent, TagPersonaDetailComponent, TagPersonaUpdateComponent, TagPersonaDeleteDialogComponent],
  entryComponents: [TagPersonaDeleteDialogComponent],
})
export class EoloprjTagPersonaModule {}
