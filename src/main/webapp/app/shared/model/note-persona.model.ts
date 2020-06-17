export interface INotePersona {
  id?: number;
  idPersonaRef?: number;
  testo?: string;
  personaId?: number;
}

export class NotePersona implements INotePersona {
  constructor(public id?: number, public idPersonaRef?: number, public testo?: string, public personaId?: number) {}
}
