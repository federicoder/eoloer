export interface IDatiContabili {
  id?: number;
  idPersonaRef?: number;
  personaId?: number;
}

export class DatiContabili implements IDatiContabili {
  constructor(public id?: number, public idPersonaRef?: number, public personaId?: number) {}
}
