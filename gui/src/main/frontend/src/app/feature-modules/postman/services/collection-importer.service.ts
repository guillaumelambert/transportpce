import { CollectionExtractorFactoryService } from './extractors/collection-extractor-factory.service';
import { Injectable } from '@angular/core';
import { Collection } from '../models/collection.model';
import { CollectionValidatorService } from './collection-validator.service';

@Injectable({
  providedIn: 'root'
})
export class CollectionImporterService {
  constructor(
    private collectionValidator: CollectionValidatorService,
    private collectionExtractorFactory: CollectionExtractorFactoryService
  ) {}

  getCollection(collection: any): Collection {
    const collectionVersion = this.collectionValidator.getRequestVersion(collection);
    const requestsExtractor = this.collectionExtractorFactory.getExtractor(collectionVersion);
    return requestsExtractor.extractCollection(collection);
  }
}
