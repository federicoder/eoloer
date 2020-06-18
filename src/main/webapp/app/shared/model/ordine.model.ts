export interface IOrdine {
  id?: number;
  idStudioProfessionaleRef?: number;
  statoOrdine?: number;
  totImponibile?: number;
  totIva?: number;
  totOrdine?: number;
  idStudioProfessionaleId?: number;
}

export class Ordine implements IOrdine {
  constructor(
    public id?: number,
    public idStudioProfessionaleRef?: number,
    public statoOrdine?: number,
    public totImponibile?: number,
    public totIva?: number,
    public totOrdine?: number,
    public idStudioProfessionaleId?: number
  ) {}
}
