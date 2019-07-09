
import { Activity } from './activity.modele';

export class EventManage {
  public id: number;
  public name: string;
  public description: string;
  public startEvent: Date;
  public endEvent: Date;
  public activities: Activity[];
}
