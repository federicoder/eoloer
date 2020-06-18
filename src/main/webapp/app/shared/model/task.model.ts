export interface ITask {
  id?: number;
  idPraticaRef?: number;
  nome?: string;
  stato?: number;
  prioritario?: number;
  pubblico?: number;
  version?: string;
  idCondivisionePraticaRef?: number;
  idAssegnazioneTaskRef?: number;
  idInvitoRef?: number;
  idTaskId?: number;
  idTaskId?: number;
  idPraticaId?: number;
}

export class Task implements ITask {
  constructor(
    public id?: number,
    public idPraticaRef?: number,
    public nome?: string,
    public stato?: number,
    public prioritario?: number,
    public pubblico?: number,
    public version?: string,
    public idCondivisionePraticaRef?: number,
    public idAssegnazioneTaskRef?: number,
    public idInvitoRef?: number,
    public idTaskId?: number,
    public idTaskId?: number,
    public idPraticaId?: number
  ) {}
}
