import { IAllegatoTask } from 'app/shared/model/allegato-task.model';
import { INotaTask } from 'app/shared/model/nota-task.model';

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
  ids?: IAllegatoTask[];
  ids?: INotaTask[];
  praticaId?: number;
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
    public ids?: IAllegatoTask[],
    public ids?: INotaTask[],
    public praticaId?: number
  ) {}
}
