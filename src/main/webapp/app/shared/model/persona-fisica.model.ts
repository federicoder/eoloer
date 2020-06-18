export interface IPersonaFisica {
  id?: number;
  idPersonaRef?: number;
  idRuoloPersonaRef?: number;
  titolo?: string;
  cognome?: string;
  nome?: string;
  dataDiNascita?: string;
  luogoDiNascita?: string;
  professione?: string;
  idPersonaId?: number;
}

export class PersonaFisica implements IPersonaFisica {
  constructor(
    public id?: number,
    public idPersonaRef?: number,
    public idRuoloPersonaRef?: number,
    public titolo?: string,
    public cognome?: string,
    public nome?: string,
    public dataDiNascita?: string,
    public luogoDiNascita?: string,
    public professione?: string,
    public idPersonaId?: number
  ) {}
}
