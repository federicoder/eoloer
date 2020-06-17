export interface ITagPersona {
  id?: number;
  idPersonaRef?: number;
  tag?: number;
  personaId?: number;
}

export class TagPersona implements ITagPersona {
  constructor(public id?: number, public idPersonaRef?: number, public tag?: number, public personaId?: number) {}
}
