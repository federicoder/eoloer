export interface IPersona {
  id?: number;
  idStudioProfessionaleRef?: number;
  codiceFiscale?: string;
  areaDiInteresse?: string;
  titolo?: string;
  cognome?: string;
  nome?: string;
  dataDiNascita?: string;
  luogoDiNascita?: string;
  professione?: string;
  tipo?: number;
  discriminator?: string;
  idRuoloPersonaRef?: number;
  tipoRuoloUtente?: number;
}

export class Persona implements IPersona {
  constructor(
    public id?: number,
    public idStudioProfessionaleRef?: number,
    public codiceFiscale?: string,
    public areaDiInteresse?: string,
    public titolo?: string,
    public cognome?: string,
    public nome?: string,
    public dataDiNascita?: string,
    public luogoDiNascita?: string,
    public professione?: string,
    public tipo?: number,
    public discriminator?: string,
    public idRuoloPersonaRef?: number,
    public tipoRuoloUtente?: number
  ) {}
}
