import {Component, Input, OnInit, ViewEncapsulation, OnChanges, SimpleChanges, ChangeDetectionStrategy} from '@angular/core';
import {Collection} from '../../models/collection.model';

@Component({
  selector: 'app-collection',
  templateUrl: './collection.component.html',
  styleUrls: ['./collection.component.css']
})
export class CollectionComponent implements OnInit, OnChanges {
  @Input() postmanCollection: Collection = new Collection('');
  @Input() index: number;

  checked = false;
  constructor() { }

  ngOnInit() {
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.checked = true;
  }

}
