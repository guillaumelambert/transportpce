import { Injectable } from '@angular/core';
import { TreeNode } from '../models/tree-node.model';

@Injectable({
  providedIn: 'root'
})
export class TreeFilterService {
  constructor() { }

  filter(tree: TreeNode[], filterString: string): TreeNode[] {
    if (!filterString || filterString.length === 0) {
      return tree;
    } else {
      return this.filterTree(tree, filterString);
    }
  }

  filterTree(tree: TreeNode[], filterString: string): TreeNode[] {
    return JSON.parse(JSON.stringify(tree)).filter(function iter(treeNode) {
      let temp;
      if (treeNode.name.includes(filterString)) {
        return true;
      }
      if (!Array.isArray(treeNode.children)) {
        return false;
      }
      temp = treeNode.children.filter(iter);
      if (temp.length) {
        treeNode.children = temp;
        return true;
      }
    });
  }
}
