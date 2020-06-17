export interface ICondivisionePratica {
  id?: number;
  idUserAmmesso?: number;
  ruolo?: number;
  idUserConcedente?: number;
  statoInvito?: number;
  idPraticaRef?: number;
  ruoloId?: number;
  idUserConcedenteId?: number;
  praticaId?: number;
  userPersonaId?: number;
}

export class CondivisionePratica implements ICondivisionePratica {
  constructor(
    public id?: number,
    public idUserAmmesso?: number,
    public ruolo?: number,
    public idUserConcedente?: number,
    public statoInvito?: number,
    public idPraticaRef?: number,
    public ruoloId?: number,
    public idUserConcedenteId?: number,
    public praticaId?: number,
    public userPersonaId?: number
  ) {}
}
