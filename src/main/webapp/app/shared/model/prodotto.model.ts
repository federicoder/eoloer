export interface IProdotto {
  id?: number;
  idProdotto?: number;
  nuovaLicenza?: number;
  rinnovoLicenza?: number;
  storage?: number;
  idProdottoId?: number;
}

export class Prodotto implements IProdotto {
  constructor(
    public id?: number,
    public idProdotto?: number,
    public nuovaLicenza?: number,
    public rinnovoLicenza?: number,
    public storage?: number,
    public idProdottoId?: number
  ) {}
}
