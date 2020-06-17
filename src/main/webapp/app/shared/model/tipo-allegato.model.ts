import { IAllegatoTemplateTask } from 'app/shared/model/allegato-template-task.model';
import { IAllegatoTask } from 'app/shared/model/allegato-task.model';

export interface ITipoAllegato {
  id?: number;
  idTipoAllegato?: number;
  nome?: string;
  formatiAmmessi?: string;
  maxDimensioneAmmessa?: string;
  version?: string;
  idTipoAllegatoes?: IAllegatoTemplateTask[];
  idTipoAllegatoes?: IAllegatoTask[];
}

export class TipoAllegato implements ITipoAllegato {
  constructor(
    public id?: number,
    public idTipoAllegato?: number,
    public nome?: string,
    public formatiAmmessi?: string,
    public maxDimensioneAmmessa?: string,
    public version?: string,
    public idTipoAllegatoes?: IAllegatoTemplateTask[],
    public idTipoAllegatoes?: IAllegatoTask[]
  ) {}
}
