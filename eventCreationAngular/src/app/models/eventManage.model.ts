import { Activity } from './activity.model';

export class EventManage {
  public id: number;
  public name: string;
  public description: string;
  public startEvent: Date;
  public endEvent: Date;
  public place: string;
  public activities: Activity[];
}
