import { Injectable } from '@angular/core';
import { KeyValuePair } from '../../models/key-value-pair.model';

@Injectable({
  providedIn: 'root'
})
export class KeyValuePairExtractorService {
  collectionHeaders: KeyValuePair[] = [];
  constructor() {}
  extractPairs(keyValuePairs: any): KeyValuePair[] {
    keyValuePairs.forEach(keyValue => {
      this.collectionHeaders.push(new KeyValuePair(keyValue.key, keyValue.value));
    });
    return this.collectionHeaders;
  }
}
