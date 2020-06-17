export interface IInvitato {
  id?: number;
  idInvitoRef?: number;
  tokenInvito?: string;
  canalePrimarioInvito?: number;
  canaleBackupInvito?: number;
  statoInvito?: number;
  idUserInvitato?: number;
  idPersonaInvitata?: number;
  nomeUserInvitato?: string;
  dataRispostaInvito?: string;
  ruoloInvitato?: number;
  indInvitati?: number;
  idUserInvitatoId?: number;
  idInvitoRefId?: number;
}

export class Invitato implements IInvitato {
  constructor(
    public id?: number,
    public idInvitoRef?: number,
    public tokenInvito?: string,
    public canalePrimarioInvito?: number,
    public canaleBackupInvito?: number,
    public statoInvito?: number,
    public idUserInvitato?: number,
    public idPersonaInvitata?: number,
    public nomeUserInvitato?: string,
    public dataRispostaInvito?: string,
    public ruoloInvitato?: number,
    public indInvitati?: number,
    public idUserInvitatoId?: number,
    public idInvitoRefId?: number
  ) {}
}
