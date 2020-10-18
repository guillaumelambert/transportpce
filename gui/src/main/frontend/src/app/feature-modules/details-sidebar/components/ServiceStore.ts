import {Store} from "../../../core-module/stores/Store";
import {ServiceModel} from "./Service.model";
import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class ServiceStore extends Store<ServiceModel> {

  constructor() {
    super(new ServiceModel());
  }

  public setServiceMode(serviceModel: ServiceModel) {
    console.log('setting state' + serviceModel);
    this.setState(serviceModel);
  }
}
