export interface ITagPersona {
  id?: number;
  idPersonaRef?: number;
  tag?: number;
  idPersonaRefId?: number;
}

export class TagPersona implements ITagPersona {
  constructor(public id?: number, public idPersonaRef?: number, public tag?: number, public idPersonaRefId?: number) {}
}
