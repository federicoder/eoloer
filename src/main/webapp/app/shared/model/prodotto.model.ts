export interface IProdotto {
  id?: number;
  nuovaLicenza?: number;
  rinnovoLicenza?: number;
  storage?: number;
  idId?: number;
}

export class Prodotto implements IProdotto {
  constructor(
    public id?: number,
    public nuovaLicenza?: number,
    public rinnovoLicenza?: number,
    public storage?: number,
    public idId?: number
  ) {}
}
