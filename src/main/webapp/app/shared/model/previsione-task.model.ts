export interface IPrevisioneTask {
  id?: number;
  idTaskRef?: number;
  qntOrdine?: number;
  prcPrevisione?: number;
  checkList?: number;
  idTaskMilestone?: number;
  tipoTask?: string;
  version?: string;
  idPrevisioneTasks?: IPrevisioneTask[];
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
    public idPrevisioneTasks?: IPrevisioneTask[],
    public previsioneTaskId?: number
  ) {}
}
