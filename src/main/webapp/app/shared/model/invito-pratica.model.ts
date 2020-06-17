export interface IInvitoPratica {
  id?: number;
  idPraticaRef?: number;
  idPraticaRefId?: number;
  idPraticaId?: number;
}

export class InvitoPratica implements IInvitoPratica {
  constructor(public id?: number, public idPraticaRef?: number, public idPraticaRefId?: number, public idPraticaId?: number) {}
}
