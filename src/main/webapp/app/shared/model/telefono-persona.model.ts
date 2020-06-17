export interface ITelefonoPersona {
  id?: number;
  idPersona?: number;
  etichetta?: number;
  valore?: number;
  personaId?: number;
}

export class TelefonoPersona implements ITelefonoPersona {
  constructor(
    public id?: number,
    public idPersona?: number,
    public etichetta?: number,
    public valore?: number,
    public personaId?: number
  ) {}
}
