export interface INotaPratica {
  id?: number;
  idPraticaRef?: number;
  data?: string;
  nota?: string;
  version?: string;
  praticaId?: number;
}

export class NotaPratica implements INotaPratica {
  constructor(
    public id?: number,
    public idPraticaRef?: number,
    public data?: string,
    public nota?: string,
    public version?: string,
    public praticaId?: number
  ) {}
}
