export interface INotePersona {
  id?: number;
  idPersonaRef?: number;
  idNotePersona?: number;
  testo?: string;
  personaId?: number;
}

export class NotePersona implements INotePersona {
  constructor(
    public id?: number,
    public idPersonaRef?: number,
    public idNotePersona?: number,
    public testo?: string,
    public personaId?: number
  ) {}
}
