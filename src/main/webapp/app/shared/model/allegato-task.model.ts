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
  ids?: IAllegatoTask[];
  tipoAllegatoId?: number;
  allegatoTaskId?: number;
  taskId?: number;
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
    public ids?: IAllegatoTask[],
    public tipoAllegatoId?: number,
    public allegatoTaskId?: number,
    public taskId?: number
  ) {}
}
