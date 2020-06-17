export interface IPrevisioneAttivita {
  id?: number;
  idTaskRef?: number;
  dataPianificata?: string;
  oraPianificata?: string;
  dataScadenza?: string;
  version?: string;
  idTaskRefId?: number;
}

export class PrevisioneAttivita implements IPrevisioneAttivita {
  constructor(
    public id?: number,
    public idTaskRef?: number,
    public dataPianificata?: string,
    public oraPianificata?: string,
    public dataScadenza?: string,
    public version?: string,
    public idTaskRefId?: number
  ) {}
}
