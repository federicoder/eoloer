import { ILineaOrdine } from 'app/shared/model/linea-ordine.model';

export interface IOrdine {
  id?: number;
  idOrdine?: number;
  idStudioProfessionaleRef?: number;
  statoOrdine?: number;
  totImponibile?: number;
  totIva?: number;
  totOrdine?: number;
  idOrdines?: ILineaOrdine[];
  studioProfessionaleId?: number;
}

export class Ordine implements IOrdine {
  constructor(
    public id?: number,
    public idOrdine?: number,
    public idStudioProfessionaleRef?: number,
    public statoOrdine?: number,
    public totImponibile?: number,
    public totIva?: number,
    public totOrdine?: number,
    public idOrdines?: ILineaOrdine[],
    public studioProfessionaleId?: number
  ) {}
}
