export interface IRisorseDisponibili {
  id?: number;
  idStudioProfessionale?: number;
  dataAttivazioneLicenza?: string;
  nrLicenza?: number;
  storageTotale?: number;
  studioProfessionaleId?: number;
}

export class RisorseDisponibili implements IRisorseDisponibili {
  constructor(
    public id?: number,
    public idStudioProfessionale?: number,
    public dataAttivazioneLicenza?: string,
    public nrLicenza?: number,
    public storageTotale?: number,
    public studioProfessionaleId?: number
  ) {}
}
