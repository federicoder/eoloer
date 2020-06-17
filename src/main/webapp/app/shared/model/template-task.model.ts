import { IAllegatoTemplateTask } from 'app/shared/model/allegato-template-task.model';

export interface ITemplateTask {
  id?: number;
  idTemplateTask?: number;
  ordineEsecuzione?: number;
  nome?: number;
  note?: number;
  pubPriv?: number;
  idTemplatePratica?: number;
  idTemplates?: ITemplateTask[];
  idTemplateTasks?: IAllegatoTemplateTask[];
  templatePraticaId?: number;
  templateTaskId?: number;
}

export class TemplateTask implements ITemplateTask {
  constructor(
    public id?: number,
    public idTemplateTask?: number,
    public ordineEsecuzione?: number,
    public nome?: number,
    public note?: number,
    public pubPriv?: number,
    public idTemplatePratica?: number,
    public idTemplates?: ITemplateTask[],
    public idTemplateTasks?: IAllegatoTemplateTask[],
    public templatePraticaId?: number,
    public templateTaskId?: number
  ) {}
}
