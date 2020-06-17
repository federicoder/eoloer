import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EoloprjSharedModule } from 'app/shared/shared.module';
import { OrdineComponent } from './ordine.component';
import { OrdineDetailComponent } from './ordine-detail.component';
import { OrdineUpdateComponent } from './ordine-update.component';
import { OrdineDeleteDialogComponent } from './ordine-delete-dialog.component';
import { ordineRoute } from './ordine.route';

@NgModule({
  imports: [EoloprjSharedModule, RouterModule.forChild(ordineRoute)],
  declarations: [OrdineComponent, OrdineDetailComponent, OrdineUpdateComponent, OrdineDeleteDialogComponent],
  entryComponents: [OrdineDeleteDialogComponent],
})
export class EoloprjOrdineModule {}
