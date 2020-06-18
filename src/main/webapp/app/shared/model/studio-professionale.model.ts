export interface IStudioProfessionale {
  id?: number;
  idUserAmministratore?: number;
  idPersonaId?: number;
}

export class StudioProfessionale implements IStudioProfessionale {
  constructor(public id?: number, public idUserAmministratore?: number, public idPersonaId?: number) {}
}
