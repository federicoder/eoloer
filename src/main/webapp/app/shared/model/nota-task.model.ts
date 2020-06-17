export interface INotaTask {
  id?: number;
  idNotaTask?: number;
  idTaskRef?: number;
  data?: string;
  nota?: string;
  version?: string;
  idTaskRefId?: number;
}

export class NotaTask implements INotaTask {
  constructor(
    public id?: number,
    public idNotaTask?: number,
    public idTaskRef?: number,
    public data?: string,
    public nota?: string,
    public version?: string,
    public idTaskRefId?: number
  ) {}
}
