import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EoloprjSharedModule } from 'app/shared/shared.module';
import { NotePersonaComponent } from './note-persona.component';
import { NotePersonaDetailComponent } from './note-persona-detail.component';
import { NotePersonaUpdateComponent } from './note-persona-update.component';
import { NotePersonaDeleteDialogComponent } from './note-persona-delete-dialog.component';
import { notePersonaRoute } from './note-persona.route';

@NgModule({
  imports: [EoloprjSharedModule, RouterModule.forChild(notePersonaRoute)],
  declarations: [NotePersonaComponent, NotePersonaDetailComponent, NotePersonaUpdateComponent, NotePersonaDeleteDialogComponent],
  entryComponents: [NotePersonaDeleteDialogComponent],
})
export class EoloprjNotePersonaModule {}
