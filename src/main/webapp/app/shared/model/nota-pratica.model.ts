export interface INotaPratica {
  id?: number;
  idPratica?: number;
  data?: string;
  nota?: string;
  version?: string;
  praticaId?: number;
}

export class NotaPratica implements INotaPratica {
  constructor(
    public id?: number,
    public idPratica?: number,
    public data?: string,
    public nota?: string,
    public version?: string,
    public praticaId?: number
  ) {}
}
