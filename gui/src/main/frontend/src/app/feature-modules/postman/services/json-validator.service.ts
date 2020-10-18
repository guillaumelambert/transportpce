import * as djv from 'djv';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class JsonValidatorService {
  djvValidator: djv.djv;
  constructor() {
    this.djvValidator = new djv({version: 'draft-06'});
  }

  validateJsonAgainstSchema(json: object, schema: object): boolean {
    this.djvValidator.addSchema('test', schema);
    if (this.djvValidator.validate('test', json) === undefined) {
      return true;
    }
  }
}
