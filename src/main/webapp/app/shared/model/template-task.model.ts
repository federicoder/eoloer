import { IAllegatoTemplateTask } from 'app/shared/model/allegato-template-task.model';

export interface ITemplateTask {
  id?: number;
  ordineEsecuzione?: number;
  nome?: number;
  note?: number;
  pubPriv?: number;
  idTemplatePraticaRef?: number;
  ids?: ITemplateTask[];
  ids?: IAllegatoTemplateTask[];
  templatePraticaId?: number;
  templateTaskId?: number;
}

export class TemplateTask implements ITemplateTask {
  constructor(
    public id?: number,
    public ordineEsecuzione?: number,
    public nome?: number,
    public note?: number,
    public pubPriv?: number,
    public idTemplatePraticaRef?: number,
    public ids?: ITemplateTask[],
    public ids?: IAllegatoTemplateTask[],
    public templatePraticaId?: number,
    public templateTaskId?: number
  ) {}
}
