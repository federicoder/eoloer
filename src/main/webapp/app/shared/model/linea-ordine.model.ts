export interface ILineaOrdine {
  id?: number;
  idOrdineRef?: number;
  idProdottoRef?: number;
  quantita?: number;
  importo?: number;
  codIva?: string;
  idProdottoId?: number;
  idOrdineId?: number;
}

export class LineaOrdine implements ILineaOrdine {
  constructor(
    public id?: number,
    public idOrdineRef?: number,
    public idProdottoRef?: number,
    public quantita?: number,
    public importo?: number,
    public codIva?: string,
    public idProdottoId?: number,
    public idOrdineId?: number
  ) {}
}
