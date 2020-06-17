export interface INotePersona {
  id?: number;
  idPersona?: number;
  idNote?: number;
  testo?: string;
  personaId?: number;
}

export class NotePersona implements INotePersona {
  constructor(public id?: number, public idPersona?: number, public idNote?: number, public testo?: string, public personaId?: number) {}
}
