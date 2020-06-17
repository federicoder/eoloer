export interface INotaTask {
  id?: number;
  idTask?: number;
  data?: string;
  nota?: string;
  version?: string;
  taskId?: number;
}

export class NotaTask implements INotaTask {
  constructor(
    public id?: number,
    public idTask?: number,
    public data?: string,
    public nota?: string,
    public version?: string,
    public taskId?: number
  ) {}
}
