import { IAllegatoTask } from 'app/shared/model/allegato-task.model';
import { INotaTask } from 'app/shared/model/nota-task.model';

export interface ITask {
  id?: number;
  idPratica?: number;
  nome?: string;
  stato?: number;
  prioritario?: number;
  pubblico?: number;
  version?: string;
  condivisionePraticaId?: number;
  assegnazioneTaskId?: number;
  invitoId?: number;
  idId?: number;
  idId?: number;
  idId?: number;
  idId?: number;
  ids?: IAllegatoTask[];
  ids?: INotaTask[];
  praticaId?: number;
}

export class Task implements ITask {
  constructor(
    public id?: number,
    public idPratica?: number,
    public nome?: string,
    public stato?: number,
    public prioritario?: number,
    public pubblico?: number,
    public version?: string,
    public condivisionePraticaId?: number,
    public assegnazioneTaskId?: number,
    public invitoId?: number,
    public idId?: number,
    public idId?: number,
    public idId?: number,
    public idId?: number,
    public ids?: IAllegatoTask[],
    public ids?: INotaTask[],
    public praticaId?: number
  ) {}
}
