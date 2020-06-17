export interface IRuoloOrganizzazione {
  id?: number;
  ruoloInOrg?: number;
  idId?: number;
  idId?: number;
}

export class RuoloOrganizzazione implements IRuoloOrganizzazione {
  constructor(public id?: number, public ruoloInOrg?: number, public idId?: number, public idId?: number) {}
}
