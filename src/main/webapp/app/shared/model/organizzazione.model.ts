export interface IOrganizzazione {
  id?: number;
  idPersonaRef?: number;
  nome?: string;
  tipo?: string;
  idPersonaId?: number;
}

export class Organizzazione implements IOrganizzazione {
  constructor(public id?: number, public idPersonaRef?: number, public nome?: string, public tipo?: string, public idPersonaId?: number) {}
}
