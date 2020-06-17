export interface IEmailPersona {
  id?: number;
  idPersonaRef?: number;
  etichetta?: number;
  numero?: number;
  personaId?: number;
}

export class EmailPersona implements IEmailPersona {
  constructor(
    public id?: number,
    public idPersonaRef?: number,
    public etichetta?: number,
    public numero?: number,
    public personaId?: number
  ) {}
}
