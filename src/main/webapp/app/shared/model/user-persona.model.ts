import { ICondivisionePratica } from 'app/shared/model/condivisione-pratica.model';
import { IAssegnazioneTask } from 'app/shared/model/assegnazione-task.model';
import { IInvitato } from 'app/shared/model/invitato.model';

export interface IUserPersona {
  id?: number;
  idPersonaRef?: number;
  nomeUser?: number;
  ids?: ICondivisionePratica[];
  ids?: IAssegnazioneTask[];
  ids?: IInvitato[];
  personaFisicaId?: number;
}

export class UserPersona implements IUserPersona {
  constructor(
    public id?: number,
    public idPersonaRef?: number,
    public nomeUser?: number,
    public ids?: ICondivisionePratica[],
    public ids?: IAssegnazioneTask[],
    public ids?: IInvitato[],
    public personaFisicaId?: number
  ) {}
}
