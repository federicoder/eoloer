import { ILineaOrdine } from 'app/shared/model/linea-ordine.model';

export interface IOrdine {
  id?: number;
  idStudioProfessionale?: number;
  statoOrdine?: number;
  totImponibile?: number;
  totIva?: number;
  totOrdine?: number;
  ids?: ILineaOrdine[];
  studioProfessionaleId?: number;
}

export class Ordine implements IOrdine {
  constructor(
    public id?: number,
    public idStudioProfessionale?: number,
    public statoOrdine?: number,
    public totImponibile?: number,
    public totIva?: number,
    public totOrdine?: number,
    public ids?: ILineaOrdine[],
    public studioProfessionaleId?: number
  ) {}
}
