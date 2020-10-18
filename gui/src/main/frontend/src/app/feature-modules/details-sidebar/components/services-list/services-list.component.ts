import { HttpClient } from '@angular/common/http';
import { Component, OnInit, SimpleChanges, ViewChild } from '@angular/core';
import { map } from 'rxjs/operators';
import * as servicesFile from '../../../../../data/serviceList_example.json';
import { MatSelectionList, MatListOption } from '@angular/material';

declare var webvowl;

@Component({
  selector: 'app-services-list',
  templateUrl: './services-list.component.html',
  styleUrls: ['./services-list.component.css']
})
export class ServicesListComponent implements OnInit {
  services: any[];
  selectedOptions: any[];
  serviceLayerShown: boolean = false;
  serviceLayerShownButtonText: string = 'Show Service Layer';
  before = false;

  @ViewChild('servicesListRef', { static: false }) servicesListViewChild: MatSelectionList;
  constructor(private http: HttpClient) {
  }

  ngOnInit() {
    if (!this.services) {
      this.http.get('/api/service').pipe(map(response => response['services'] || []))
        .subscribe(
          data => {
            this.services = data;
          },
          error => console.log(error)
        );
    }
  }

  onSelection(clickedOption: any, event?) {

    // this.selectedOptions = value;
    // console.log('src -> dst', event.option._value.source['node-id'], event.option._value.target['node-id']);
    const selectedValue = clickedOption.value;
    const serviceName = selectedValue['service-name']
    console.log(selectedValue);
    if (clickedOption.selected) {
      const linkLabel = serviceName + ' - '
        + selectedValue.sourcePoint['service-format'] + ' - '
        + selectedValue.targetPoint['service-rate'];
      console.log(selectedValue.sourcePoint['node-id']);
      console.log(selectedValue.targetPoint['node-id']);
      console.log(linkLabel);
      webvowl.app().getGraph().addExtraLink(selectedValue.sourcePoint['node-id'], selectedValue.targetPoint['node-id'], linkLabel, serviceName);
    } else {
      webvowl.app().getGraph().removeExtraLink(selectedValue.sourcePoint['node-id'], selectedValue.targetPoint['node-id'], serviceName);
    }
    // console.log("selectedOptions: ", value);
    // console.log("event.option: ", clickedOption);
    // console.log("event.source: ", event.source);
    // for (let i = 0; i < this.selectedOptions.length; i++) {
    //   this.checkedValues.push(this.selectedOptions[i]._text.nativeElement.innerText);
    // }
  }

  addLink(source: string, destination: string, label: string) {

  }

  selectOrUnsellectAll() {

    this.servicesListViewChild.options.forEach(option => {
      if (option.selected === this.serviceLayerShown) {
        option.selected = !this.serviceLayerShown;
        this.onSelection(option);
      }
    });
    this.serviceLayerShown = !this.serviceLayerShown;

    if (this.serviceLayerShown) {
      this.serviceLayerShownButtonText = 'Hide Service Layer';
    } else {
      this.serviceLayerShownButtonText = 'Show Service Layer';
    }
  }
}
