import { ITemplateTask } from 'app/shared/model/template-task.model';

export interface ITemplatePratica {
  id?: number;
  idTemplate?: number;
  nomeTemplate?: number;
  elencoTagAmbito?: number;
  idTemplates?: ITemplateTask[];
}

export class TemplatePratica implements ITemplatePratica {
  constructor(
    public id?: number,
    public idTemplate?: number,
    public nomeTemplate?: number,
    public elencoTagAmbito?: number,
    public idTemplates?: ITemplateTask[]
  ) {}
}
