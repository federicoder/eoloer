import { IInvito } from 'app/shared/model/invito.model';

export interface IAssegnazioneTask {
  id?: number;
  idTaskRef?: number;
  idUserAmmesso?: number;
  ruolo?: number;
  idUserConcedente?: number;
  statoAssegnazione?: number;
  idTaskId?: number;
  idRuoloPersonaId?: number;
  idInvitos?: IInvito[];
  idPersonaId?: number;
}

export class AssegnazioneTask implements IAssegnazioneTask {
  constructor(
    public id?: number,
    public idTaskRef?: number,
    public idUserAmmesso?: number,
    public ruolo?: number,
    public idUserConcedente?: number,
    public statoAssegnazione?: number,
    public idTaskId?: number,
    public idRuoloPersonaId?: number,
    public idInvitos?: IInvito[],
    public idPersonaId?: number
  ) {}
}
