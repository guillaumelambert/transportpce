import { Collection } from '../../models/collection.model';

export abstract class CollectionExtractor {
  abstract extractCollection(rawJsonCollection: any): Collection;
}
