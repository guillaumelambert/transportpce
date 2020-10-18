import {FlatTreeControl} from '@angular/cdk/tree';
import {HttpClient} from '@angular/common/http';
import {ChangeDetectorRef, Component, Input, OnChanges, SimpleChanges, ViewEncapsulation} from '@angular/core';
import {MatTreeFlatDataSource, MatTreeFlattener} from '@angular/material/tree';
// import * as topology from '../../../../../data/topology.json';
// import * as topology from '../../../../../data/topology-example.json';
import {TopologyNode} from '../../models/topology-node.model.js';
import {JSONUtilService} from '../../services/json-util/jsonutil.service';
import {map} from 'rxjs/operators';
import {ServiceStore} from "../ServiceStore";
import {ServiceModel} from "../Service.model";


@Component({
  selector: 'app-yang-tree',
  templateUrl: './yang-tree-sidebar.component.html',
  styleUrls: ['./yang-tree-sidebar.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class YangTreeSideBarComponent implements OnChanges {
  @Input() nodeId !: string;
  topology: any;
  recursive = false;

  levels = new Map<TopologyNode, number>();

  treeControl: FlatTreeControl<TopologyNode>;

  treeFlattener: MatTreeFlattener<TopologyNode, TopologyNode>;

  dataSource: MatTreeFlatDataSource<TopologyNode, TopologyNode>;

  // treeData: TopologyNode[] = [];
  // @ViewChild('')
  services: any[];

  constructor(private changeDetectorRef: ChangeDetectorRef, private jsonUtil: JSONUtilService, private http: HttpClient
    , private serviceStore: ServiceStore) {
    this.treeFlattener = new MatTreeFlattener(this.transformer, this.getLevel,
      this.isExpandable, this.getChildren);

    this.treeControl = new FlatTreeControl<TopologyNode>(
      this.getLevel, this.isExpandable);

    this.dataSource = new MatTreeFlatDataSource(this.treeControl, this.treeFlattener);
  }

  ngOnChanges(changes: SimpleChanges) {
    this.dataSource.data = [];
    if (changes.nodeId.currentValue) {
      if (!this.topology) {
        this.http.get('/api/networks').pipe(map(response => response['network'] || []))
          .subscribe(
            data => {
              this.topology = data;
              this.setTreeData();
            },
            error => console.log(error)
          );
      } else {
        this.setTreeData();
      }
    }


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

  private setTreeData() {
    const treeData = [];
    let foundJsonObject: TopologyNode = null;
    console.log('nodeId: ', this.nodeId);
    foundJsonObject = this.jsonUtil.searchForObjectInJson(this.nodeId, this.topology);
    if (foundJsonObject) {
      let objectKey = '';
      for (const key in foundJsonObject) {
        if (key.toString().includes('-id')) {
          console.log('key = ', key, 'value = ', foundJsonObject[key]);
          objectKey = key.split('-')[0] + ' [ ' + foundJsonObject[key] + ' ]';
          break;
        }
      }

      treeData.push(this.jsonUtil.parseObject(objectKey, foundJsonObject));
      console.log("this.treeData", treeData);
      this.dataSource.data = treeData;
    } else {
      const serviceModel = new ServiceModel();
      serviceModel.serviceName = this.nodeId.split(" - ")[0];
      serviceModel.serviceFormat = this.nodeId.split(" - ")[1];
      serviceModel.ServiceRate = this.nodeId.split(" - ")[2];
      this.serviceStore.setServiceMode(serviceModel);
      }

  }

  getLevel = (node: TopologyNode): number => {
    return this.levels.get(node) || 0;
  }

  isExpandable = (node: TopologyNode): boolean => {
    return node.children.value.length > 0;
  }

  getChildren = (node: TopologyNode) => {
    return node.children;
  }

  transformer = (node: TopologyNode, level: number) => {
    this.levels.set(node, level);
    return node;
  }

  hasChildren = (index: number, node: TopologyNode): boolean => {
    return node.children.value.length > 0;
  }

  isOdd(node: TopologyNode) {
    return this.getLevel(node) % 2 === 1;
  }
}

