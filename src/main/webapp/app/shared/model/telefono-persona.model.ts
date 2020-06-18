export interface ITelefonoPersona {
  id?: number;
  idPersonaRef?: number;
  etichetta?: number;
  valore?: number;
  idPersonaId?: number;
}

export class TelefonoPersona implements ITelefonoPersona {
  constructor(
    public id?: number,
    public idPersonaRef?: number,
    public etichetta?: number,
    public valore?: number,
    public idPersonaId?: number
  ) {}
}
