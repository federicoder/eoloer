import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EoloprjSharedModule } from 'app/shared/shared.module';
import { LineaOrdineComponent } from './linea-ordine.component';
import { LineaOrdineDetailComponent } from './linea-ordine-detail.component';
import { LineaOrdineUpdateComponent } from './linea-ordine-update.component';
import { LineaOrdineDeleteDialogComponent } from './linea-ordine-delete-dialog.component';
import { lineaOrdineRoute } from './linea-ordine.route';

@NgModule({
  imports: [EoloprjSharedModule, RouterModule.forChild(lineaOrdineRoute)],
  declarations: [LineaOrdineComponent, LineaOrdineDetailComponent, LineaOrdineUpdateComponent, LineaOrdineDeleteDialogComponent],
  entryComponents: [LineaOrdineDeleteDialogComponent],
})
export class EoloprjLineaOrdineModule {}
