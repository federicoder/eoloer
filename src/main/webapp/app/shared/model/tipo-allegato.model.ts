export interface ITipoAllegato {
  id?: number;
  nome?: string;
  formatiAmmessi?: string;
  maxDimensioneAmmessa?: string;
  version?: string;
}

export class TipoAllegato implements ITipoAllegato {
  constructor(
    public id?: number,
    public nome?: string,
    public formatiAmmessi?: string,
    public maxDimensioneAmmessa?: string,
    public version?: string
  ) {}
}
