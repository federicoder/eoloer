import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EoloprjSharedModule } from 'app/shared/shared.module';
import { ProdottoComponent } from './prodotto.component';
import { ProdottoDetailComponent } from './prodotto-detail.component';
import { ProdottoUpdateComponent } from './prodotto-update.component';
import { ProdottoDeleteDialogComponent } from './prodotto-delete-dialog.component';
import { prodottoRoute } from './prodotto.route';

@NgModule({
  imports: [EoloprjSharedModule, RouterModule.forChild(prodottoRoute)],
  declarations: [ProdottoComponent, ProdottoDetailComponent, ProdottoUpdateComponent, ProdottoDeleteDialogComponent],
  entryComponents: [ProdottoDeleteDialogComponent],
})
export class EoloprjProdottoModule {}
