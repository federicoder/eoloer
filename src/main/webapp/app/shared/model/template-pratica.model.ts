export interface ITemplatePratica {
  id?: number;
  nomeTemplate?: number;
  elencoTagAmbito?: number;
}

export class TemplatePratica implements ITemplatePratica {
  constructor(public id?: number, public nomeTemplate?: number, public elencoTagAmbito?: number) {}
}
