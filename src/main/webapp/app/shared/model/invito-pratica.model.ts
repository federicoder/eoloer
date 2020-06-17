export interface IInvitoPratica {
  id?: number;
  idPraticaRef?: number;
  idPraticaRefId?: number;
  praticaId?: number;
}

export class InvitoPratica implements IInvitoPratica {
  constructor(public id?: number, public idPraticaRef?: number, public idPraticaRefId?: number, public praticaId?: number) {}
}
