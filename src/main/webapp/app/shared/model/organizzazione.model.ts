export interface IOrganizzazione {
  id?: number;
  idOrganizzazione?: number;
  idPersona?: number;
  nome?: string;
  tipo?: string;
  idPersonaId?: number;
  idOrganizzazioneId?: number;
}

export class Organizzazione implements IOrganizzazione {
  constructor(
    public id?: number,
    public idOrganizzazione?: number,
    public idPersona?: number,
    public nome?: string,
    public tipo?: string,
    public idPersonaId?: number,
    public idOrganizzazioneId?: number
  ) {}
}
