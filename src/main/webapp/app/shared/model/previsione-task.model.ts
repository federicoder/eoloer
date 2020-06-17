export interface IPrevisioneTask {
  id?: number;
  idTask?: number;
  qntOrdine?: number;
  prcPrevisione?: number;
  checkList?: number;
  idTaskMilestone?: number;
  tipoTask?: string;
  version?: string;
  idTasks?: IPrevisioneTask[];
  idTaskId?: number;
  idTaskId?: number;
  idTaskId?: number;
  previsioneTaskId?: number;
}

export class PrevisioneTask implements IPrevisioneTask {
  constructor(
    public id?: number,
    public idTask?: number,
    public qntOrdine?: number,
    public prcPrevisione?: number,
    public checkList?: number,
    public idTaskMilestone?: number,
    public tipoTask?: string,
    public version?: string,
    public idTasks?: IPrevisioneTask[],
    public idTaskId?: number,
    public idTaskId?: number,
    public idTaskId?: number,
    public previsioneTaskId?: number
  ) {}
}
