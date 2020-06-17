export interface ILineaOrdine {
  id?: number;
  idOrdine?: number;
  idProdotto?: number;
  quantita?: number;
  importo?: number;
  codIva?: string;
  idProdottoId?: number;
  ordineId?: number;
}

export class LineaOrdine implements ILineaOrdine {
  constructor(
    public id?: number,
    public idOrdine?: number,
    public idProdotto?: number,
    public quantita?: number,
    public importo?: number,
    public codIva?: string,
    public idProdottoId?: number,
    public ordineId?: number
  ) {}
}
