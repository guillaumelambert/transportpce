import { BehaviorSubject } from 'rxjs';

export class TopologyNode {
  children: BehaviorSubject<TopologyNode[]>;
  constructor(public item: string, children?: TopologyNode[]) {
    this.children = new BehaviorSubject(children === undefined ? [] : children);
  }
}
