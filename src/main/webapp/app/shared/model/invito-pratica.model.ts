export interface IInvitoPratica {
  id?: number;
  idPraticaRef?: number;
  idPraticaId?: number;
  idPraticaId?: number;
}

export class InvitoPratica implements IInvitoPratica {
  constructor(public id?: number, public idPraticaRef?: number, public idPraticaId?: number, public idPraticaId?: number) {}
}
