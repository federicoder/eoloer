import { IUserPersona } from 'app/shared/model/user-persona.model';

export interface IPersonaFisica {
  id?: number;
  idPersonaFisica?: number;
  idPersona?: number;
  idRuoloPersona?: number;
  titolo?: string;
  cognome?: string;
  nome?: string;
  dataDiNascita?: string;
  luogoDiNascita?: string;
  professione?: string;
  idPersonaId?: number;
  idPersonaFisicas?: IUserPersona[];
  idRuoloPersonaId?: number;
}

export class PersonaFisica implements IPersonaFisica {
  constructor(
    public id?: number,
    public idPersonaFisica?: number,
    public idPersona?: number,
    public idRuoloPersona?: number,
    public titolo?: string,
    public cognome?: string,
    public nome?: string,
    public dataDiNascita?: string,
    public luogoDiNascita?: string,
    public professione?: string,
    public idPersonaId?: number,
    public idPersonaFisicas?: IUserPersona[],
    public idRuoloPersonaId?: number
  ) {}
}
