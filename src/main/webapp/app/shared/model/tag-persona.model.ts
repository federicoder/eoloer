export interface ITagPersona {
  id?: number;
  idPersona?: number;
  tag?: number;
  personaId?: number;
}

export class TagPersona implements ITagPersona {
  constructor(public id?: number, public idPersona?: number, public tag?: number, public personaId?: number) {}
}
