export interface IPrevisioneAttivita {
  id?: number;
  idTask?: number;
  dataPianificata?: string;
  oraPianificata?: string;
  dataScadenza?: string;
  version?: string;
  idTaskId?: number;
}

export class PrevisioneAttivita implements IPrevisioneAttivita {
  constructor(
    public id?: number,
    public idTask?: number,
    public dataPianificata?: string,
    public oraPianificata?: string,
    public dataScadenza?: string,
    public version?: string,
    public idTaskId?: number
  ) {}
}
