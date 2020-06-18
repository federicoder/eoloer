export interface ICondivisionePratica {
  id?: number;
  idUserAmmesso?: number;
  ruolo?: number;
  idUserConcedente?: number;
  statoInvito?: number;
  idPraticaRef?: number;
  idRuoloPersonaId?: number;
  idPersonaId?: number;
  idUserPersonaId?: number;
  idPraticaId?: number;
}

export class CondivisionePratica implements ICondivisionePratica {
  constructor(
    public id?: number,
    public idUserAmmesso?: number,
    public ruolo?: number,
    public idUserConcedente?: number,
    public statoInvito?: number,
    public idPraticaRef?: number,
    public idRuoloPersonaId?: number,
    public idPersonaId?: number,
    public idUserPersonaId?: number,
    public idPraticaId?: number
  ) {}
}
