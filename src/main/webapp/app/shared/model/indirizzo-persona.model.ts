export interface IIndirizzoPersona {
  id?: number;
  idPersonaRef?: number;
  indirizzo?: string;
  comune?: string;
  cap?: number;
  provincia?: string;
  regione?: string;
  nazione?: string;
  idPersonaRefId?: number;
}

export class IndirizzoPersona implements IIndirizzoPersona {
  constructor(
    public id?: number,
    public idPersonaRef?: number,
    public indirizzo?: string,
    public comune?: string,
    public cap?: number,
    public provincia?: string,
    public regione?: string,
    public nazione?: string,
    public idPersonaRefId?: number
  ) {}
}
