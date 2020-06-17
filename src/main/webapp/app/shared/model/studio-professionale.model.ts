export interface IStudioProfessionale {
  id?: number;
  idUserAmministratore?: number;
  idUserAmministratoreId?: number;
  idId?: number;
}

export class StudioProfessionale implements IStudioProfessionale {
  constructor(public id?: number, public idUserAmministratore?: number, public idUserAmministratoreId?: number, public idId?: number) {}
}
