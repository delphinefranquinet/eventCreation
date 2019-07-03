import { ConvertActionBindingResult } from '@angular/compiler/src/compiler_util/expression_converter';
import { Activity } from './activity.modele';

export class Event {

  public nameOfEvent: string;
  public descriptionEvent: string;
  public begins: Date;
  public ends: Date;
  public activities: Activity[];
}
