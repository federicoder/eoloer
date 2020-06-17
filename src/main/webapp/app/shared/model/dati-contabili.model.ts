export interface IDatiContabili {
  id?: number;
  idDatiContabili?: number;
  idPersona?: number;
  personaId?: number;
}

export class DatiContabili implements IDatiContabili {
  constructor(public id?: number, public idDatiContabili?: number, public idPersona?: number, public personaId?: number) {}
}
