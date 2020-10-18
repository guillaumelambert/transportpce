import { Component, OnInit, Input, OnChanges, SimpleChanges } from '@angular/core';
import { FlatTreeControl } from '@angular/cdk/tree';
import { MatTreeFlattener, MatTreeFlatDataSource } from '@angular/material';
import { TreeNode } from '../../models/tree-node.model';
import { of as observableOf } from 'rxjs';
import { Request } from '../../models/request.model';
import { TreeFilterService } from '../../services/tree-filter.service';


export interface FlattenedTreeNode {
  name: string;
  type: string;
  data: Request;
  level: number;
  expandable: boolean;
}


@Component({
  selector: 'app-collections-tree',
  templateUrl: './collections-tree.component.html',
  styleUrls: ['./collections-tree.component.css']
})
export class CollectionsTreeComponent implements OnInit, OnChanges {
  @Input() collectionsTree: TreeNode[] = [];
  @Input() searchText: string;

  /** The TreeControl controls the expand/collapse state of tree nodes.  */
  treeControl: FlatTreeControl<FlattenedTreeNode>;

  /** The TreeFlattener is used to generate the flat list of items from hierarchical data. */
  treeFlattener: MatTreeFlattener<TreeNode, FlattenedTreeNode>;

  /** The MatTreeFlatDataSource connects the control and flattener to provide data. */
  dataSource: MatTreeFlatDataSource<TreeNode, FlattenedTreeNode>;

  constructor(private treeFilterService: TreeFilterService) {
    this.initializeMatTree();
  }

  private initializeMatTree() {
    this.treeFlattener = new MatTreeFlattener(this.transformer, this.getLevel, this.isExpandable, this.getChildren);
    this.treeControl = new FlatTreeControl<FlattenedTreeNode>(this.getLevel, this.isExpandable);
    this.dataSource = new MatTreeFlatDataSource(this.treeControl, this.treeFlattener);
  }

  ngOnInit() {
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes.searchText) {
      this.dataSource.data = this.treeFilterService.filter(this.collectionsTree, this.searchText);
      this.treeControl.expandAll();
    } else {
      this.dataSource.data = this.collectionsTree;
    }
  }

  /** Transform the data to something the tree can read. */
  transformer(node: TreeNode, level: number) {
    return {
      name: node.name,
      type: node.type,
      level: level,
      data: node.data,
      expandable: !!node.children
    };
  }

  getLevel(node: FlattenedTreeNode) {
    return node.level;
  }

  isExpandable(node: FlattenedTreeNode) {
    return node.expandable;
  }

  getChildren(node: TreeNode) {
    return observableOf(node.children);
  }

  hasChild(index: number, node: FlattenedTreeNode) {
    return node.expandable;
  }

}
