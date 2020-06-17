export interface ITemplateTask {
  id?: number;
  ordineEsecuzione?: number;
  nome?: number;
  note?: number;
  pubPriv?: number;
  idTemplatePraticaRef?: number;
  ids?: ITemplateTask[];
  idTemplatePraticaRefId?: number;
  idId?: number;
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
    public idTemplatePraticaRefId?: number,
    public idId?: number,
    public templateTaskId?: number
  ) {}
}
