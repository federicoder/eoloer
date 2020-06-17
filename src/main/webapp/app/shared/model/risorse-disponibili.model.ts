export interface IRisorseDisponibili {
  id?: number;
  idStudioProfessionaleRef?: number;
  dataAttivazioneLicenza?: string;
  nrLicenza?: number;
  storageTotale?: number;
  idStudioProfessionaleRefId?: number;
}

export class RisorseDisponibili implements IRisorseDisponibili {
  constructor(
    public id?: number,
    public idStudioProfessionaleRef?: number,
    public dataAttivazioneLicenza?: string,
    public nrLicenza?: number,
    public storageTotale?: number,
    public idStudioProfessionaleRefId?: number
  ) {}
}
