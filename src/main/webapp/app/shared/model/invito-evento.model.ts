export interface IInvitoEvento {
  id?: number;
  idTaskRef?: number;
  luogoFisico?: string;
  indicazioniLuogo?: string;
  dataInizio?: string;
  oraInizio?: string;
  dataFine?: string;
  oraFine?: string;
  urlStanzaVirtuale?: string;
  idTaskId?: number;
  previsioneEventoId?: number;
}

export class InvitoEvento implements IInvitoEvento {
  constructor(
    public id?: number,
    public idTaskRef?: number,
    public luogoFisico?: string,
    public indicazioniLuogo?: string,
    public dataInizio?: string,
    public oraInizio?: string,
    public dataFine?: string,
    public oraFine?: string,
    public urlStanzaVirtuale?: string,
    public idTaskId?: number,
    public previsioneEventoId?: number
  ) {}
}
