export interface IIndirizzoPersona {
  id?: number;
  idPersona?: number;
  indirizzo?: string;
  comune?: string;
  cap?: number;
  provincia?: string;
  regione?: string;
  nazione?: string;
  idPersonaId?: number;
}

export class IndirizzoPersona implements IIndirizzoPersona {
  constructor(
    public id?: number,
    public idPersona?: number,
    public indirizzo?: string,
    public comune?: string,
    public cap?: number,
    public provincia?: string,
    public regione?: string,
    public nazione?: string,
    public idPersonaId?: number
  ) {}
}
