export interface IAllegatoTemplateTask {
  id?: number;
  idTemplateTaskRef?: number;
  idTipoAllegatoRef?: number;
  formato?: number;
  idFileRef?: number;
  pubPriv?: number;
  idTemplateTaskRefId?: number;
  tipoAllegatoId?: number;
}

export class AllegatoTemplateTask implements IAllegatoTemplateTask {
  constructor(
    public id?: number,
    public idTemplateTaskRef?: number,
    public idTipoAllegatoRef?: number,
    public formato?: number,
    public idFileRef?: number,
    public pubPriv?: number,
    public idTemplateTaskRefId?: number,
    public tipoAllegatoId?: number
  ) {}
}
