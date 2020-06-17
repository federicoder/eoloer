export interface IDatiContabili {
  id?: number;
  idDatiContabili?: number;
  idPersonaRef?: number;
  personaId?: number;
}

export class DatiContabili implements IDatiContabili {
  constructor(public id?: number, public idDatiContabili?: number, public idPersonaRef?: number, public personaId?: number) {}
}
