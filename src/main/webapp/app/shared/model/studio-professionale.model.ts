import { IRisorseDisponibili } from 'app/shared/model/risorse-disponibili.model';
import { IOrdine } from 'app/shared/model/ordine.model';

export interface IStudioProfessionale {
  id?: number;
  idUserAmministratore?: number;
  ids?: IRisorseDisponibili[];
  ids?: IOrdine[];
  idId?: number;
  personaId?: number;
}

export class StudioProfessionale implements IStudioProfessionale {
  constructor(
    public id?: number,
    public idUserAmministratore?: number,
    public ids?: IRisorseDisponibili[],
    public ids?: IOrdine[],
    public idId?: number,
    public personaId?: number
  ) {}
}
