import { IInvitoEvento } from 'app/shared/model/invito-evento.model';

export interface IPrevisioneEvento {
  id?: number;
  idTaskRef?: number;
  dataInizio?: string;
  dataFine?: string;
  luogo?: string;
  indicazioniLuogo?: string;
  version?: string;
  idPrevisioneEventoId?: number;
  idInvitoEventos?: IInvitoEvento[];
}

export class PrevisioneEvento implements IPrevisioneEvento {
  constructor(
    public id?: number,
    public idTaskRef?: number,
    public dataInizio?: string,
    public dataFine?: string,
    public luogo?: string,
    public indicazioniLuogo?: string,
    public version?: string,
    public idPrevisioneEventoId?: number,
    public idInvitoEventos?: IInvitoEvento[]
  ) {}
}
