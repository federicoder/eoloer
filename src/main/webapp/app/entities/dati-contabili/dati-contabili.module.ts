import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EoloprjSharedModule } from 'app/shared/shared.module';
import { DatiContabiliComponent } from './dati-contabili.component';
import { DatiContabiliDetailComponent } from './dati-contabili-detail.component';
import { DatiContabiliUpdateComponent } from './dati-contabili-update.component';
import { DatiContabiliDeleteDialogComponent } from './dati-contabili-delete-dialog.component';
import { datiContabiliRoute } from './dati-contabili.route';

@NgModule({
  imports: [EoloprjSharedModule, RouterModule.forChild(datiContabiliRoute)],
  declarations: [DatiContabiliComponent, DatiContabiliDetailComponent, DatiContabiliUpdateComponent, DatiContabiliDeleteDialogComponent],
  entryComponents: [DatiContabiliDeleteDialogComponent],
})
export class EoloprjDatiContabiliModule {}
