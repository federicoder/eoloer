import { IInvitoEvento } from 'app/shared/model/invito-evento.model';

export interface IPrevisioneEvento {
  id?: number;
  idTask?: number;
  dataInizio?: string;
  dataFine?: string;
  luogo?: string;
  indicazioniLuogo?: string;
  version?: string;
  idTaskId?: number;
  idTasks?: IInvitoEvento[];
}

export class PrevisioneEvento implements IPrevisioneEvento {
  constructor(
    public id?: number,
    public idTask?: number,
    public dataInizio?: string,
    public dataFine?: string,
    public luogo?: string,
    public indicazioniLuogo?: string,
    public version?: string,
    public idTaskId?: number,
    public idTasks?: IInvitoEvento[]
  ) {}
}
