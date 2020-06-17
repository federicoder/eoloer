export interface IInvitoPratica {
  id?: number;
  idPratica?: number;
  idPraticaId?: number;
  praticaId?: number;
}

export class InvitoPratica implements IInvitoPratica {
  constructor(public id?: number, public idPratica?: number, public idPraticaId?: number, public praticaId?: number) {}
}
