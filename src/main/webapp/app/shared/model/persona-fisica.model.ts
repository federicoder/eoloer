import { IUserPersona } from 'app/shared/model/user-persona.model';

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
  idPersonaRefId?: number;
  ids?: IUserPersona[];
  idRuoloPersonaId?: number;
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
    public idPersonaRefId?: number,
    public ids?: IUserPersona[],
    public idRuoloPersonaId?: number
  ) {}
}
