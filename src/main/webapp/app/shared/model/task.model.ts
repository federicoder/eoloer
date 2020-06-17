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
  idId?: number;
  idId?: number;
  idId?: number;
  idId?: number;
  idPraticaRefId?: number;
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
    public idId?: number,
    public idId?: number,
    public idId?: number,
    public idId?: number,
    public idPraticaRefId?: number
  ) {}
}
