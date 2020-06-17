export interface IInvitoEvento {
  id?: number;
  idAttivita?: number;
  luogoFisico?: string;
  indicazioniLuogo?: string;
  dataInizio?: string;
  oraInizio?: string;
  dataFine?: string;
  oraFine?: string;
  urlStanzaVirtuale?: string;
  idAttivitaId?: number;
  previsioneEventoId?: number;
}

export class InvitoEvento implements IInvitoEvento {
  constructor(
    public id?: number,
    public idAttivita?: number,
    public luogoFisico?: string,
    public indicazioniLuogo?: string,
    public dataInizio?: string,
    public oraInizio?: string,
    public dataFine?: string,
    public oraFine?: string,
    public urlStanzaVirtuale?: string,
    public idAttivitaId?: number,
    public previsioneEventoId?: number
  ) {}
}
