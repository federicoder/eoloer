export interface IConsuntivoTask {
  id?: number;
  idTaskRef?: number;
  dataInizio?: string;
  dataFine?: string;
  timeLine?: number;
  version?: string;
}

export class ConsuntivoTask implements IConsuntivoTask {
  constructor(
    public id?: number,
    public idTaskRef?: number,
    public dataInizio?: string,
    public dataFine?: string,
    public timeLine?: number,
    public version?: string
  ) {}
}
