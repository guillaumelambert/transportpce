import { TestBed } from '@angular/core/testing';

import { JSONUtilService } from './jsonutil.service';

describe('JSONUtilService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: JSONUtilService = TestBed.get(JSONUtilService);
    expect(service).toBeTruthy();
  });
});
