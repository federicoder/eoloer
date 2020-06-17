import { IInvito } from 'app/shared/model/invito.model';

export interface IAssegnazioneTask {
  id?: number;
  idAttivita?: number;
  idUserAmmesso?: number;
  ruolo?: number;
  idUserConcedente?: number;
  statoAssegnazione?: number;
  ruoloId?: number;
  idUserConcedentes?: IInvito[];
  idAttivitaId?: number;
  userPersonaId?: number;
}

export class AssegnazioneTask implements IAssegnazioneTask {
  constructor(
    public id?: number,
    public idAttivita?: number,
    public idUserAmmesso?: number,
    public ruolo?: number,
    public idUserConcedente?: number,
    public statoAssegnazione?: number,
    public ruoloId?: number,
    public idUserConcedentes?: IInvito[],
    public idAttivitaId?: number,
    public userPersonaId?: number
  ) {}
}
