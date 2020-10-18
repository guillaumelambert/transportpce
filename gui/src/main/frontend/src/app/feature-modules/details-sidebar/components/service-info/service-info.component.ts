import {ChangeDetectorRef, Component, Input, OnInit, SimpleChanges} from '@angular/core';
import {ServiceStore} from "../ServiceStore";
import {ServiceModel} from "../Service.model";
import {TopologyNode} from "../../models/topology-node.model";
import {FlatTreeControl} from "@angular/cdk/tree";
import {MatTreeFlatDataSource, MatTreeFlattener} from "@angular/material/tree";
import {JSONUtilService} from "../../services/json-util/jsonutil.service";
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs/operators";
import {ServiceNode} from "../../models/service-node.model";

@Component({
  selector: 'app-service-info',
  templateUrl: './service-info.component.html',
  styleUrls: ['./service-info.component.css']
})
export class ServiceInfoComponent implements OnInit {
  ngOnInit(): void {

  }

  @Input() nodeId !: string;
  service: any;
  recursive = false;

  levels = new Map<ServiceNode, number>();

  treeControl: FlatTreeControl<ServiceNode>;

  treeFlattener: MatTreeFlattener<ServiceNode, ServiceNode>;

  dataSource: MatTreeFlatDataSource<ServiceNode, ServiceNode>;

  serviceModel: ServiceModel = new ServiceModel();

  constructor(private changeDetectorRef: ChangeDetectorRef, private jsonUtil: JSONUtilService, private http: HttpClient
    , private serviceStore: ServiceStore) {
    this.treeFlattener = new MatTreeFlattener(this.transformer, this.getLevel,
      this.isExpandable, this.getChildren);

    this.treeControl = new FlatTreeControl<TopologyNode>(
      this.getLevel, this.isExpandable);

    this.dataSource = new MatTreeFlatDataSource(this.treeControl, this.treeFlattener);
    this.serviceStore.state$.forEach(serviceModel => {
      if (serviceModel) {
        this.serviceModel = serviceModel;
        this.dataSource.data = [];
        console.log('i can hear');
        console.log(this.service);
        if (!this.service) {
          this.http.get('/api/service').pipe(map(response => response['services'] || []))
            .subscribe(
              data => {
                this.service = data;
                this.setTreeData();
              },
              error => console.log(error)
            );
        } else {
          this.setTreeData();
        }
      }
    });
  }

  ngOnChanges(changes: SimpleChanges) {
    /* this.dataSource.data = [];
       console.log('i can hear');
       if (!this.service) {
         this.http.get('/api/service').pipe(map(response => response['services'] || []))
           .subscribe(
             data => {
               this.service = data;
               this.setTreeData();
             },
             error => console.log(error)
           );
       } else {
         this.setTreeData();
       }*/

    /*  if (changes.nodeId.currentValue) {

      }*/

  }


  private setTreeData() {
    const treeData = [];
    let foundJsonObject: ServiceNode = null;
    console.log('serviceMode: ', this.serviceModel.serviceName)
    console.log(this.service);
    foundJsonObject = this.searchForObjectInJson(this.serviceModel.serviceName, this.service);
    console.log(foundJsonObject);
    if (foundJsonObject) {
       let objectKey = '';
       for (const key in foundJsonObject) {
        // if (key.toString().includes('-id')) {
           console.log('key = ', key, 'value = ', foundJsonObject[key]);
           objectKey = foundJsonObject[key]['service-name'] ;//+ ' [ ' + foundJsonObject[key] + ' ]';
           break;
        // }
       }

       treeData.push(this.jsonUtil.parseObject(objectKey, foundJsonObject[0]));
       console.log("this.treeData", treeData);
       this.dataSource.data = treeData;
     }

  }

  getLevel = (node: ServiceNode): number => {
    return this.levels.get(node) || 0;
  }

  isExpandable = (node: ServiceNode): boolean => {
    return node.children.value.length > 0;
  }

  getChildren = (node: ServiceNode) => {
    return node.children;
  }

  transformer = (node: ServiceNode, level: number) => {
    this.levels.set(node, level);
    return node;
  }

  hasChildren = (index: number, node: ServiceNode): boolean => {
    return node.children.value.length > 0;
  }

  isOdd(node: ServiceNode) {
    return this.getLevel(node) % 2 === 1;
  }

  private searchForObjectInJson(serviceName: string, services: any):ServiceNode {
   return services.filter(function (service) {
      return service['service-name'].includes(serviceName);
    });
  }
}
