export interface IPratica {
  id?: number;
  idStudioProfessionaleRef?: number;
  numero?: string;
  nome?: string;
  dataApertura?: string;
  dataChiusura?: string;
  dataScadenza?: string;
  stato?: number;
  motivoChiusura?: string;
  idTitolare?: number;
  prcAvanzato?: number;
  version?: string;
  valuta?: string;
  idTemplatePraticaRef?: number;
  idTemplatePraticaId?: number;
}

export class Pratica implements IPratica {
  constructor(
    public id?: number,
    public idStudioProfessionaleRef?: number,
    public numero?: string,
    public nome?: string,
    public dataApertura?: string,
    public dataChiusura?: string,
    public dataScadenza?: string,
    public stato?: number,
    public motivoChiusura?: string,
    public idTitolare?: number,
    public prcAvanzato?: number,
    public version?: string,
    public valuta?: string,
    public idTemplatePraticaRef?: number,
    public idTemplatePraticaId?: number
  ) {}
}
