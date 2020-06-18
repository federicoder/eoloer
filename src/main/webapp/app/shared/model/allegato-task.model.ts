export interface IAllegatoTask {
  id?: number;
  idTipoAllegatoRef?: number;
  idTaskRef?: number;
  formato?: number;
  note?: string;
  stato?: number;
  pubblico?: number;
  version?: string;
  idAllegatoMaster?: number;
  idAllegatoTasks?: IAllegatoTask[];
  idTipoAllegatoId?: number;
  idTaskId?: number;
  allegatoTaskId?: number;
}

export class AllegatoTask implements IAllegatoTask {
  constructor(
    public id?: number,
    public idTipoAllegatoRef?: number,
    public idTaskRef?: number,
    public formato?: number,
    public note?: string,
    public stato?: number,
    public pubblico?: number,
    public version?: string,
    public idAllegatoMaster?: number,
    public idAllegatoTasks?: IAllegatoTask[],
    public idTipoAllegatoId?: number,
    public idTaskId?: number,
    public allegatoTaskId?: number
  ) {}
}
