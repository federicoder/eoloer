import { IInvitato } from 'app/shared/model/invitato.model';

export interface IInvito {
  id?: number;
  idStudioProfessionale?: number;
  dataInvito?: string;
  idUserInvitante?: number;
  nomeUserInvitante?: string;
  dataScadenzaInvito?: string;
  testoInvito?: string;
  idPratica?: number;
  idAttivita?: number;
  luogoFisico?: string;
  indicazioniLuogo?: string;
  dataInizio?: string;
  oraInizio?: string;
  dataFine?: string;
  oraFine?: string;
  urlStanzaVirtuale?: string;
  discriminator?: string;
  idStudioProfessionaleId?: number;
  ids?: IInvitato[];
  idId?: number;
  idId?: number;
  idId?: number;
  assegnazioneTaskId?: number;
}

export class Invito implements IInvito {
  constructor(
    public id?: number,
    public idStudioProfessionale?: number,
    public dataInvito?: string,
    public idUserInvitante?: number,
    public nomeUserInvitante?: string,
    public dataScadenzaInvito?: string,
    public testoInvito?: string,
    public idPratica?: number,
    public idAttivita?: number,
    public luogoFisico?: string,
    public indicazioniLuogo?: string,
    public dataInizio?: string,
    public oraInizio?: string,
    public dataFine?: string,
    public oraFine?: string,
    public urlStanzaVirtuale?: string,
    public discriminator?: string,
    public idStudioProfessionaleId?: number,
    public ids?: IInvitato[],
    public idId?: number,
    public idId?: number,
    public idId?: number,
    public assegnazioneTaskId?: number
  ) {}
}
