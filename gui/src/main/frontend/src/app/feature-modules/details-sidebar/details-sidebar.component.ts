import {Component, Input, OnChanges, OnInit, SimpleChanges, ViewEncapsulation} from '@angular/core';
import {ResizeEvent} from 'angular-resizable-element';
import {ServiceStore} from "./components/ServiceStore";

@Component({
  selector: 'app-details-sidebar',
  templateUrl: './details-sidebar.component.html',
  styleUrls: ['./details-sidebar.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class DetailsSideBarComponent implements OnInit, OnChanges {
  @Input() nodeId !: string;
  public style: object = {};
  public selectedTabIndex: number = 0;
  public active = false;

  constructor(private serviceStore: ServiceStore) {
    this.selectedTabIndex = 1;
    this.serviceStore.state$.forEach(serviceModel => {
      if (serviceModel && serviceModel.serviceName ) {
        this.selectedTabIndex = 2;
      }
    });
  }

  ngOnInit() {
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes.nodeId.currentValue) {
      this.selectedTabIndex = 0;
    }
  }

  validate(event: ResizeEvent): boolean {
    const MIN_DIMENSIONS_HEIGHT_PX: number = 50;
    const MIN_DIMENSIONS_WIDTH_PX: number = 250;
    if (
      event.rectangle.width &&
      event.rectangle.height &&
      (event.rectangle.width < MIN_DIMENSIONS_WIDTH_PX ||
        event.rectangle.height < MIN_DIMENSIONS_HEIGHT_PX)
    ) {
      return false;
    }
    return true;
  }

  onResizeEnd(event: ResizeEvent): void {
    this.style = {
      position: 'fixed',
      left: `${event.rectangle.left}px`,
      top: `${event.rectangle.top}px`,
      width: `${event.rectangle.width}px`,
      height: `${event.rectangle.height}px`
    };
  }
}
