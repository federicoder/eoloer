export interface IInvitoAttivita {
  id?: number;
  idAttivita?: number;
  idAttivitaId?: number;
  idAttivitaId?: number;
}

export class InvitoAttivita implements IInvitoAttivita {
  constructor(public id?: number, public idAttivita?: number, public idAttivitaId?: number, public idAttivitaId?: number) {}
}
