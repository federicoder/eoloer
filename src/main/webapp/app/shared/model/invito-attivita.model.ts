export interface IInvitoAttivita {
  id?: number;
  idTaskRef?: number;
  idTaskId?: number;
  idTaskId?: number;
}

export class InvitoAttivita implements IInvitoAttivita {
  constructor(public id?: number, public idTaskRef?: number, public idTaskId?: number, public idTaskId?: number) {}
}
