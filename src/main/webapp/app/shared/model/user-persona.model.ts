import { ICondivisionePratica } from 'app/shared/model/condivisione-pratica.model';
import { IAssegnazioneTask } from 'app/shared/model/assegnazione-task.model';
import { IInvitato } from 'app/shared/model/invitato.model';

export interface IUserPersona {
  id?: number;
  idUserPersona?: number;
  idPersonaRef?: number;
  nomeUser?: number;
  idUserPersonas?: ICondivisionePratica[];
  idUserPersonas?: IAssegnazioneTask[];
  idUserPersonas?: IInvitato[];
  personaFisicaId?: number;
}

export class UserPersona implements IUserPersona {
  constructor(
    public id?: number,
    public idUserPersona?: number,
    public idPersonaRef?: number,
    public nomeUser?: number,
    public idUserPersonas?: ICondivisionePratica[],
    public idUserPersonas?: IAssegnazioneTask[],
    public idUserPersonas?: IInvitato[],
    public personaFisicaId?: number
  ) {}
}
