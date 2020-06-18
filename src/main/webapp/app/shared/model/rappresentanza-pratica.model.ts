import { Ruoli } from 'app/shared/model/enumerations/ruoli.model';

export interface IRappresentanzaPratica {
  id?: number;
  idRuoloPersona?: number;
  idPersonaRef?: number;
  ruoli?: Ruoli;
  idPersonaId?: number;
}

export class RappresentanzaPratica implements IRappresentanzaPratica {
  constructor(
    public id?: number,
    public idRuoloPersona?: number,
    public idPersonaRef?: number,
    public ruoli?: Ruoli,
    public idPersonaId?: number
  ) {}
}
