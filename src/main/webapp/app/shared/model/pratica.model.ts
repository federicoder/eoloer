import { ITask } from 'app/shared/model/task.model';
import { INotaPratica } from 'app/shared/model/nota-pratica.model';
import { ICondivisionePratica } from 'app/shared/model/condivisione-pratica.model';
import { IInvitoPratica } from 'app/shared/model/invito-pratica.model';

export interface IPratica {
  id?: number;
  idPratica?: number;
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
  idPraticas?: ITask[];
  idPraticas?: INotaPratica[];
  idPraticas?: ICondivisionePratica[];
  idPraticas?: IInvitoPratica[];
  idTemplatePraticaRefId?: number;
}

export class Pratica implements IPratica {
  constructor(
    public id?: number,
    public idPratica?: number,
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
    public idPraticas?: ITask[],
    public idPraticas?: INotaPratica[],
    public idPraticas?: ICondivisionePratica[],
    public idPraticas?: IInvitoPratica[],
    public idTemplatePraticaRefId?: number
  ) {}
}
