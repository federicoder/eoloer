export interface IPrevisioneTask {
  id?: number;
  idTaskRef?: number;
  qntOrdine?: number;
  prcPrevisione?: number;
  checkList?: number;
  idTaskMilestone?: number;
  tipoTask?: string;
  version?: string;
  idTaskRefs?: IPrevisioneTask[];
  idTaskRefId?: number;
  idTaskRefId?: number;
  idTaskRefId?: number;
  previsioneTaskId?: number;
}

export class PrevisioneTask implements IPrevisioneTask {
  constructor(
    public id?: number,
    public idTaskRef?: number,
    public qntOrdine?: number,
    public prcPrevisione?: number,
    public checkList?: number,
    public idTaskMilestone?: number,
    public tipoTask?: string,
    public version?: string,
    public idTaskRefs?: IPrevisioneTask[],
    public idTaskRefId?: number,
    public idTaskRefId?: number,
    public idTaskRefId?: number,
    public previsioneTaskId?: number
  ) {}
}
