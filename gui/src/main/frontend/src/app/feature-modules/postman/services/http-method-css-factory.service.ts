import {HttpMethod} from '../models/http-method.enum';
import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class HttpMethodCssFactoryService {
  constructor() {}
  getCssClass(httpMethod: HttpMethod) {
    switch (httpMethod) {
      case HttpMethod.GET:
        return 'get-label label';
      case HttpMethod.POST:
        return 'post-label label';
      case HttpMethod.PUT:
        return 'put';
      case HttpMethod.PATCH:
        return 'patch';
      case HttpMethod.DELETE:
        return 'delete';
      case HttpMethod.HEAD:
        return 'head';
      default:
        throw new Error('Method Not Supported');

    }
  }
}
