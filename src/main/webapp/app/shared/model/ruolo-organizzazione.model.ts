export interface IRuoloOrganizzazione {
  id?: number;
  ruoloInOrg?: number;
  idOrganizzazioneId?: number;
  idPersonaFisicaId?: number;
}

export class RuoloOrganizzazione implements IRuoloOrganizzazione {
  constructor(public id?: number, public ruoloInOrg?: number, public idOrganizzazioneId?: number, public idPersonaFisicaId?: number) {}
}
