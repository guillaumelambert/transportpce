import { HttpMethod } from './http-method.enum';
import { HttpRequest } from './http-request.model';
import { KeyValuePair } from './key-value-pair.model';

export class Request extends HttpRequest {
  public name: string;

  constructor(
    name: string,
    url: string,
    method: HttpMethod,
    headers: KeyValuePair[] = [],
    params: KeyValuePair[] = [],
    body: string = '',
  ) {
    super(url, method, headers, params, body);
    this.name = name;
  }

  getName() {
    return this.name;
  }

  setName(name: string) {
    this.name = name;
  }
}
