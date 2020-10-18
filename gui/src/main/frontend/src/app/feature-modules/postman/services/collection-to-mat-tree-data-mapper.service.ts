import { Injectable } from '@angular/core';
import { Collection } from '../models/collection.model';
import { Folder } from '../models/folder.model';
import { Request } from '../models/request.model';
import { Item } from '../models/item.type';
import { TreeNode } from '../models/tree-node.model';

@Injectable({
  providedIn: 'root'
})
export class CollectionToMatTreeDataMapper {
  constructor() {}

  getTreeData(collection: Collection): TreeNode {
    const treeNode: TreeNode = {} as TreeNode;
    treeNode.name = collection.getName();
    treeNode.type = 'collection';
    treeNode.children = this.getChildren(collection.getItems());
    return treeNode;
  }

  private getChildren(items: Item[]): TreeNode[] {
    const treeNodes: TreeNode[] = [];
    items.forEach(item => {
      if (this.isFolder(item)) {
        // here we have the recursion getChildren ---> buildFolder ---> getchildren
        treeNodes.push(this.buildFolder(item as Folder));
      } else {
        treeNodes.push(this.buildRequest(item as Request));
      }
    });
    return treeNodes;
  }

  private isFolder(item: Item) {
    return item.constructor.name === Folder.name;
  }

  private buildRequest(request: Request): TreeNode {
    const requestTreeNode: TreeNode = {} as TreeNode;
    requestTreeNode.name = request.getName();
    requestTreeNode.data = request;
    requestTreeNode.type = 'request';
    return requestTreeNode;
  }

  private buildFolder(folder: Folder): TreeNode {
    const folderTreeNode: TreeNode = {} as TreeNode;
    folderTreeNode.name = folder.getName();
    folderTreeNode.type = 'folder';
    folderTreeNode.children = this.getChildren(folder.getItems());
    return folderTreeNode;
  }
}
