import {BehaviorSubject} from 'rxjs';

export class ServiceNode {
  children: BehaviorSubject<ServiceNode[]>;
  constructor(public item: string, children?: ServiceNode[]) {
    this.children = new BehaviorSubject(children === undefined ? [] : children);
  }
}
