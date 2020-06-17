export interface IConsuntivoTask {
  id?: number;
  idTask?: number;
  dataInizio?: string;
  dataFine?: string;
  timeLine?: number;
  version?: string;
  idTaskId?: number;
}

export class ConsuntivoTask implements IConsuntivoTask {
  constructor(
    public id?: number,
    public idTask?: number,
    public dataInizio?: string,
    public dataFine?: string,
    public timeLine?: number,
    public version?: string,
    public idTaskId?: number
  ) {}
}
