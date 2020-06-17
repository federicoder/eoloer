import { Ruoli } from 'app/shared/model/enumerations/ruoli.model';

export interface IRappresentanzaPratica {
  id?: number;
  idRuoloPersona?: number;
  idPersona?: number;
  ruoli?: Ruoli;
  idRuoloPersonaId?: number;
  idRuoloPersonaId?: number;
  personaId?: number;
}

export class RappresentanzaPratica implements IRappresentanzaPratica {
  constructor(
    public id?: number,
    public idRuoloPersona?: number,
    public idPersona?: number,
    public ruoli?: Ruoli,
    public idRuoloPersonaId?: number,
    public idRuoloPersonaId?: number,
    public personaId?: number
  ) {}
}
