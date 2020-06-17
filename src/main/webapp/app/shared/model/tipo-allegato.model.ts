import { IAllegatoTemplateTask } from 'app/shared/model/allegato-template-task.model';
import { IAllegatoTask } from 'app/shared/model/allegato-task.model';

export interface ITipoAllegato {
  id?: number;
  nome?: string;
  formatiAmmessi?: string;
  maxDimensioneAmmessa?: string;
  version?: string;
  ids?: IAllegatoTemplateTask[];
  ids?: IAllegatoTask[];
}

export class TipoAllegato implements ITipoAllegato {
  constructor(
    public id?: number,
    public nome?: string,
    public formatiAmmessi?: string,
    public maxDimensioneAmmessa?: string,
    public version?: string,
    public ids?: IAllegatoTemplateTask[],
    public ids?: IAllegatoTask[]
  ) {}
}
