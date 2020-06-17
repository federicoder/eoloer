export interface IOrganizzazione {
  id?: number;
  idOrganizzazione?: number;
  idPersonaRef?: number;
  nome?: string;
  tipo?: string;
  idPersonaRefId?: number;
  idOrganizzazioneId?: number;
}

export class Organizzazione implements IOrganizzazione {
  constructor(
    public id?: number,
    public idOrganizzazione?: number,
    public idPersonaRef?: number,
    public nome?: string,
    public tipo?: string,
    public idPersonaRefId?: number,
    public idOrganizzazioneId?: number
  ) {}
}
