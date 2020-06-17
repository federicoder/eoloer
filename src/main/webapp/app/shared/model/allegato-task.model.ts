export interface IAllegatoTask {
  id?: number;
  idAllegatoTask?: number;
  idTipo?: number;
  idTask?: number;
  formato?: number;
  note?: string;
  stato?: number;
  pubblico?: number;
  version?: string;
  idAllegatoMaster?: number;
  idAllegatoTasks?: IAllegatoTask[];
  tipoAllegatoId?: number;
  allegatoTaskId?: number;
  taskId?: number;
}

export class AllegatoTask implements IAllegatoTask {
  constructor(
    public id?: number,
    public idAllegatoTask?: number,
    public idTipo?: number,
    public idTask?: number,
    public formato?: number,
    public note?: string,
    public stato?: number,
    public pubblico?: number,
    public version?: string,
    public idAllegatoMaster?: number,
    public idAllegatoTasks?: IAllegatoTask[],
    public tipoAllegatoId?: number,
    public allegatoTaskId?: number,
    public taskId?: number
  ) {}
}
