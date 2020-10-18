import { Request } from './request.model';

export interface TreeNode {
  name: string;
  type: string;
  data: Request;
  children: TreeNode[];
}
