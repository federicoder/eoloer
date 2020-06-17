export interface IRuoloOrganizzazione {
  id?: number;
  idRuoloOrganizzazione?: number;
  ruoloInOrg?: number;
  idRuoloOrganizzazioneId?: number;
  idRuoloOrganizzazioneId?: number;
}

export class RuoloOrganizzazione implements IRuoloOrganizzazione {
  constructor(
    public id?: number,
    public idRuoloOrganizzazione?: number,
    public ruoloInOrg?: number,
    public idRuoloOrganizzazioneId?: number,
    public idRuoloOrganizzazioneId?: number
  ) {}
}
