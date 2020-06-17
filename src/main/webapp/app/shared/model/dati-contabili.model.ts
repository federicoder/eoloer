export interface IDatiContabili {
  id?: number;
  idPersonaRef?: number;
  idPersonaRefId?: number;
}

export class DatiContabili implements IDatiContabili {
  constructor(public id?: number, public idPersonaRef?: number, public idPersonaRefId?: number) {}
}
