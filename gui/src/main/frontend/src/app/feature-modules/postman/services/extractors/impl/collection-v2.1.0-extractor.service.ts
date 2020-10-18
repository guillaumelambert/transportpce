import { CollectionExtractor } from '../CollectionExtractor';
import { Request } from '../../../models/request.model';
import { Injectable } from '@angular/core';
import { Collection } from 'src/app/feature-modules/postman/models/collection.model';
import { KeyValuePairExtractorService } from '../key-value-pair-extractor.service';
import { Folder } from '../../../models/folder.model';
import { Item } from '../../../models/item.type';

@Injectable({
  providedIn: 'root'
})
// tslint:disable-next-line: class-name
export class CollectionV2_1_0ExtractorService implements CollectionExtractor {
  constructor(private keyValuePairExtractor: KeyValuePairExtractorService) {}
  rawJsonCollection: any;

  extractCollection(rawJsonCollection): Collection {
    this.rawJsonCollection = rawJsonCollection;
    return this.traverseDepthFirstAndGetCollection();
  }

  private traverseDepthFirstAndGetCollection(): Collection {
    // here we treat collection as top level folder
    const collection: Folder = this.traverseFolder(this.rawJsonCollection);
    return new Collection(collection.getName(), collection.getItems());
  }

  private traverseFolder(rawJsonFolder: any): Folder {
    const folder: Folder = new Folder(this.getFolderName(rawJsonFolder));
    folder.addItems(this.getSubItems(rawJsonFolder));
    return folder;
  }

  private getFolderName(rawJsonFolder: any): string {
    return rawJsonFolder.name ? rawJsonFolder.name : rawJsonFolder.info.name;
  }

  private getSubItems(rawJsonFolder: any): Item[] {
    const items: Item[] = [];
    this.getRawJsonSubItems(rawJsonFolder).forEach(rawJsonItem => {
      if (this.hasFolder(rawJsonItem)) {
        // here we have the recursion traverseFolder ---> getSubItems ---> traverseFolder
        items.push(this.traverseFolder(rawJsonItem));
      } else {
        items.push(this.buildRequest(rawJsonItem));
      }
    });
    return items;
  }

  private getRawJsonSubItems(rawJsonFolder: any) {
    return rawJsonFolder.item;
  }

  private hasFolder(item: any): boolean {
    return item.item ? true : false;
  }

  private buildRequest(RawJsonRequest: any): Request {
    return new Request(
      RawJsonRequest.name,
      RawJsonRequest.request.url.raw ? RawJsonRequest.request.url.raw : RawJsonRequest.request.url,
      RawJsonRequest.request.method,
      this.keyValuePairExtractor.extractPairs(RawJsonRequest.request.header),
      this.keyValuePairExtractor.extractPairs(RawJsonRequest.request.url.query ? RawJsonRequest.request.url.query : []),
      RawJsonRequest.request.body ? RawJsonRequest.request.body.raw : ''
    );
  }
}
