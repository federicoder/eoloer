import { IRisorseDisponibili } from 'app/shared/model/risorse-disponibili.model';
import { IOrdine } from 'app/shared/model/ordine.model';

export interface IStudioProfessionale {
  id?: number;
  idStudioProfessionale?: number;
  idUserAmministratore?: number;
  idStudioProfessionales?: IRisorseDisponibili[];
  idStudioProfessionales?: IOrdine[];
  idStudioProfessionaleId?: number;
  personaId?: number;
}

export class StudioProfessionale implements IStudioProfessionale {
  constructor(
    public id?: number,
    public idStudioProfessionale?: number,
    public idUserAmministratore?: number,
    public idStudioProfessionales?: IRisorseDisponibili[],
    public idStudioProfessionales?: IOrdine[],
    public idStudioProfessionaleId?: number,
    public personaId?: number
  ) {}
}
