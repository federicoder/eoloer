export interface IInvitato {
  id?: number;
  idInvito?: number;
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
  userPersonaId?: number;
  invitoId?: number;
}

export class Invitato implements IInvitato {
  constructor(
    public id?: number,
    public idInvito?: number,
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
    public userPersonaId?: number,
    public invitoId?: number
  ) {}
}
