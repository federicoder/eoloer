import { ITemplateTask } from 'app/shared/model/template-task.model';

export interface ITemplatePratica {
  id?: number;
  idTemplatePratica?: number;
  nomeTemplate?: number;
  elencoTagAmbito?: number;
  idTemplatePraticas?: ITemplateTask[];
}

export class TemplatePratica implements ITemplatePratica {
  constructor(
    public id?: number,
    public idTemplatePratica?: number,
    public nomeTemplate?: number,
    public elencoTagAmbito?: number,
    public idTemplatePraticas?: ITemplateTask[]
  ) {}
}
