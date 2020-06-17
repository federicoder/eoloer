export interface IEmailPersona {
  id?: number;
  idPersona?: number;
  etichetta?: number;
  numero?: number;
  personaId?: number;
}

export class EmailPersona implements IEmailPersona {
  constructor(
    public id?: number,
    public idPersona?: number,
    public etichetta?: number,
    public numero?: number,
    public personaId?: number
  ) {}
}
