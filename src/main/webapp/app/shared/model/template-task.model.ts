export interface ITemplateTask {
  id?: number;
  ordineEsecuzione?: number;
  nome?: number;
  note?: number;
  pubPriv?: number;
  idTemplatePraticaRef?: number;
  idTemplateTasks?: ITemplateTask[];
  idTemplatePraticaId?: number;
  idTemplateTaskId?: number;
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
    public idTemplateTasks?: ITemplateTask[],
    public idTemplatePraticaId?: number,
    public idTemplateTaskId?: number,
    public templateTaskId?: number
  ) {}
}
