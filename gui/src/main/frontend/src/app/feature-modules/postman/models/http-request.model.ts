import { HttpMethod } from './http-method.enum';
import { KeyValuePair } from './key-value-pair.model';

export class HttpRequest {
  constructor(
    public url: string,
    public method: HttpMethod,
    public headers: KeyValuePair[] = [],
    public params: KeyValuePair[] = [],
    public body: string = '',
  ) { }

  getUrl() {
    return this.url;
  }

  setUrl(url: string) {
    this.url = url;
  }

  getMethod() {
    return this.method;
  }

  setMethod(method: HttpMethod) {
    this.method = method;
  }

  getHeaders() {
    return this.headers;
  }

  setHeaders(headers: KeyValuePair[]) {
    this.headers = headers;
  }

  getBody() {
    return this.body;
  }

  setBody(body: string) {
    this.body = body;
  }

  getParams() {
    return this.params;
  }

  setParams(params: KeyValuePair[]) {
    this.params = params;
  }
}
