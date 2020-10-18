import { CollectionV1_0_0ExtractorService } from './impl/collection-v1.0.0-extractor.service';
import { Injectable } from '@angular/core';
import { CollectionV2_0_0ExtractorService } from './impl/collection-v2.0.0-extractor.service';
import { CollectionV2_1_0ExtractorService } from './impl/collection-v2.1.0-extractor.service';
import { CollectionExtractor } from './CollectionExtractor';

@Injectable({
  providedIn: 'root'
})
export class CollectionExtractorFactoryService {
  constructor(
    private v1_0_0Extractor: CollectionV1_0_0ExtractorService,
    private v2_0_0Extractor: CollectionV2_0_0ExtractorService,
    private V2_1_0Extractor: CollectionV2_1_0ExtractorService
  ) { }

  getExtractor(collectionVersion: string): CollectionExtractor {
    switch (collectionVersion) {
      case '1.0.0':
        return this.v1_0_0Extractor;
      case '2.0.0':
        return this.v2_0_0Extractor;
      case '2.1.0':
        return this.V2_1_0Extractor;
      default:
        throw new Error('Collection version not supported');
    }
  }
}
