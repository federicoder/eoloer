import { IInvitato } from 'app/shared/model/invitato.model';

export interface IInvito {
  id?: number;
  idInvito?: number;
  idStudioProfessionaleRef?: number;
  dataInvito?: string;
  idUserInvitante?: number;
  nomeUserInvitante?: string;
  dataScadenzaInvito?: string;
  testoInvito?: string;
  idPraticaRef?: number;
  idTaskRef?: number;
  luogoFisico?: string;
  indicazioniLuogo?: string;
  dataInizio?: string;
  oraInizio?: string;
  dataFine?: string;
  oraFine?: string;
  urlStanzaVirtuale?: string;
  discriminator?: string;
  idStudioProfessionaleRefId?: number;
  idInvitos?: IInvitato[];
  idInvitoId?: number;
  idInvitoId?: number;
  idInvitoId?: number;
  assegnazioneTaskId?: number;
}

export class Invito implements IInvito {
  constructor(
    public id?: number,
    public idInvito?: number,
    public idStudioProfessionaleRef?: number,
    public dataInvito?: string,
    public idUserInvitante?: number,
    public nomeUserInvitante?: string,
    public dataScadenzaInvito?: string,
    public testoInvito?: string,
    public idPraticaRef?: number,
    public idTaskRef?: number,
    public luogoFisico?: string,
    public indicazioniLuogo?: string,
    public dataInizio?: string,
    public oraInizio?: string,
    public dataFine?: string,
    public oraFine?: string,
    public urlStanzaVirtuale?: string,
    public discriminator?: string,
    public idStudioProfessionaleRefId?: number,
    public idInvitos?: IInvitato[],
    public idInvitoId?: number,
    public idInvitoId?: number,
    public idInvitoId?: number,
    public assegnazioneTaskId?: number
  ) {}
}
