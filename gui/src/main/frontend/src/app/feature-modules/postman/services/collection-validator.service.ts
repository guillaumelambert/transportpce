import { JsonValidatorService } from './json-validator.service';
import { SchemaRepositoryService } from './schema-repository.service';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CollectionValidatorService {
  constructor(
    private jsonValidator: JsonValidatorService,
    private SchemaRepository: SchemaRepositoryService
  ) { }

  getRequestVersion(collection: object): string {
    return this.validateAndReturnVersion(collection);
  }

  private validateAndReturnVersion(collection): string {
    for (const jsonSchema of this.SchemaRepository.getAllSchemas().reverse()) {
      if (this.jsonValidator.validateJsonAgainstSchema(collection, jsonSchema.default.schema)) {
        return jsonSchema.default.version;
      }
    }
    throw new Error('Not a valid collection');
  }
}
