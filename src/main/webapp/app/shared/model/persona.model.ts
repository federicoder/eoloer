import { IDatiContabili } from 'app/shared/model/dati-contabili.model';
import { IEmailPersona } from 'app/shared/model/email-persona.model';
import { ITagPersona } from 'app/shared/model/tag-persona.model';
import { ITelefonoPersona } from 'app/shared/model/telefono-persona.model';
import { INotePersona } from 'app/shared/model/note-persona.model';
import { IStudioProfessionale } from 'app/shared/model/studio-professionale.model';
import { IRappresentanzaPratica } from 'app/shared/model/rappresentanza-pratica.model';

export interface IPersona {
  id?: number;
  idPersona?: number;
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
  idPersonaId?: number;
  idPersonas?: IDatiContabili[];
  idPersonas?: IEmailPersona[];
  idPersonas?: ITagPersona[];
  idPersonas?: ITelefonoPersona[];
  idPersonas?: INotePersona[];
  idPersonas?: IStudioProfessionale[];
  idPersonas?: IRappresentanzaPratica[];
  idPersonaId?: number;
  idPersonaId?: number;
  idPersonaId?: number;
}

export class Persona implements IPersona {
  constructor(
    public id?: number,
    public idPersona?: number,
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
    public tipoRuoloUtente?: number,
    public idPersonaId?: number,
    public idPersonas?: IDatiContabili[],
    public idPersonas?: IEmailPersona[],
    public idPersonas?: ITagPersona[],
    public idPersonas?: ITelefonoPersona[],
    public idPersonas?: INotePersona[],
    public idPersonas?: IStudioProfessionale[],
    public idPersonas?: IRappresentanzaPratica[],
    public idPersonaId?: number,
    public idPersonaId?: number,
    public idPersonaId?: number
  ) {}
}
