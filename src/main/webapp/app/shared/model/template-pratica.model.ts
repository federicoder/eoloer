import { ITemplateTask } from 'app/shared/model/template-task.model';

export interface ITemplatePratica {
  id?: number;
  nomeTemplate?: number;
  elencoTagAmbito?: number;
  ids?: ITemplateTask[];
}

export class TemplatePratica implements ITemplatePratica {
  constructor(public id?: number, public nomeTemplate?: number, public elencoTagAmbito?: number, public ids?: ITemplateTask[]) {}
}
