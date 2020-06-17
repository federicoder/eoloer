import { IInvito } from 'app/shared/model/invito.model';

export interface IAssegnazioneTask {
  id?: number;
  idTaskRef?: number;
  idUserAmmesso?: number;
  ruolo?: number;
  idUserConcedente?: number;
  statoAssegnazione?: number;
  ruoloId?: number;
  idUserConcedentes?: IInvito[];
  idTaskRefId?: number;
  userPersonaId?: number;
}

export class AssegnazioneTask implements IAssegnazioneTask {
  constructor(
    public id?: number,
    public idTaskRef?: number,
    public idUserAmmesso?: number,
    public ruolo?: number,
    public idUserConcedente?: number,
    public statoAssegnazione?: number,
    public ruoloId?: number,
    public idUserConcedentes?: IInvito[],
    public idTaskRefId?: number,
    public userPersonaId?: number
  ) {}
}
