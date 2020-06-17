export interface IAllegatoTemplateTask {
  id?: number;
  idTemplateTask?: number;
  tipoAllegato?: number;
  formato?: number;
  idFile?: number;
  pubPriv?: number;
  templateTaskId?: number;
  tipoAllegatoId?: number;
}

export class AllegatoTemplateTask implements IAllegatoTemplateTask {
  constructor(
    public id?: number,
    public idTemplateTask?: number,
    public tipoAllegato?: number,
    public formato?: number,
    public idFile?: number,
    public pubPriv?: number,
    public templateTaskId?: number,
    public tipoAllegatoId?: number
  ) {}
}
