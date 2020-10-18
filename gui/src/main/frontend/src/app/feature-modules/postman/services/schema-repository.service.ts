import * as v1_0_0Schema from 'src/assets/json/postman-collection-schemas/V1.0.0.Schema.json';
import * as v2_0_0Schema from 'src/assets/json/postman-collection-schemas/V2.0.0.Schema.json';
import * as v2_1_0Schema from 'src/assets/json/postman-collection-schemas/V2.1.0.Schema.json';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SchemaRepositoryService {
  private schemas = [];
  constructor() {
    this.pushSchemas();
  }
  private pushSchemas() {
    this.schemas.push(v1_0_0Schema);
    this.schemas.push(v2_0_0Schema);
    this.schemas.push(v2_1_0Schema);
  }

  getAllSchemas(): any[] {
    return [...this.schemas];
  }
}
