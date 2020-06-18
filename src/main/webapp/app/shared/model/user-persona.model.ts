export interface IUserPersona {
  id?: number;
  idPersonaRef?: number;
  nomeUser?: number;
  idPersonaFisicaId?: number;
}

export class UserPersona implements IUserPersona {
  constructor(public id?: number, public idPersonaRef?: number, public nomeUser?: number, public idPersonaFisicaId?: number) {}
}
