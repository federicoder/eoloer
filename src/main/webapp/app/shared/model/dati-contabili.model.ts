export interface IDatiContabili {
  id?: number;
  idPersonaRef?: number;
  idPersonaId?: number;
}

export class DatiContabili implements IDatiContabili {
  constructor(public id?: number, public idPersonaRef?: number, public idPersonaId?: number) {}
}
