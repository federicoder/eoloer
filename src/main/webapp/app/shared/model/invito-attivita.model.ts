export interface IInvitoAttivita {
  id?: number;
  idTaskRef?: number;
  idTaskRefId?: number;
  idTaskRefId?: number;
}

export class InvitoAttivita implements IInvitoAttivita {
  constructor(public id?: number, public idTaskRef?: number, public idTaskRefId?: number, public idTaskRefId?: number) {}
}
