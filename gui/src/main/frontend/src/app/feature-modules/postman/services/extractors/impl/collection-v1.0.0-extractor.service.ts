import { CollectionExtractor } from '../CollectionExtractor';
import { Request } from '../../../models/request.model';
import { Injectable } from '@angular/core';
import { KeyValuePairExtractorService } from '../key-value-pair-extractor.service';
import { Collection } from 'src/app/feature-modules/postman/models/collection.model';
import { Folder } from '../../../models/folder.model';

@Injectable({
  providedIn: 'root'
})
// tslint:disable-next-line: class-name
export class CollectionV1_0_0ExtractorService implements CollectionExtractor {
  constructor(private keyValuePairExtractor: KeyValuePairExtractorService) {}
  rawJsonCollection: any;

  extractCollection(rawJsonCollection): Collection {
    this.rawJsonCollection = rawJsonCollection;
    return this.traverseDepthFirstAndGetCollection();
  }

  private traverseDepthFirstAndGetCollection(): Collection {
    // here we treat collection as top level folder with undefined id
    const collection: Folder = this.traverseFolder();
    return new Collection(collection.getName(), collection.getItems());
  }

  private traverseFolder(id?: any): Folder {
    const rawJsonFolder: any = this.getRawJsonFolder(id);
    const folder: Folder = new Folder(rawJsonFolder.name);

    // here we have the recursion traverseFolder ---> getSubFolders ---> traverseFolder
    folder.addItems(this.getSubFolders(rawJsonFolder));

    folder.addItems(this.getSubRequests(rawJsonFolder));

    return folder;
  }

  private getSubFolders(rawJsonFolder: any): Folder[] {
    const folders: Folder[] = [];
    if (this.hasRawJsonFolders(rawJsonFolder)) {
      this.getRawFoldersIds(rawJsonFolder).forEach(folderId => {
        folders.push(this.traverseFolder(folderId) as Folder);
      });
    }
    return folders;
  }

  private getSubRequests(rawJsonFolder: any): Request[] {
    const requests: Request[] = [];
    if (this.hasRawJsonRequests(rawJsonFolder)) {
      this.getRawRequestIds(rawJsonFolder).forEach(requestId => {
        const request = this.getrawJsonRequest(requestId);
        requests.push(this.buildRequest(request));
      });
    }
    return requests;
  }


  private buildRequest(rawJsonRequest: any): Request {
    return new Request(
      rawJsonRequest.name,
      rawJsonRequest.url,
      rawJsonRequest.method,
      this.keyValuePairExtractor.extractPairs(rawJsonRequest.headerData ? rawJsonRequest.headerData : []),
      this.keyValuePairExtractor.extractPairs(rawJsonRequest.queryParams ? rawJsonRequest.queryParams : []),
      rawJsonRequest.rawModeData ? rawJsonRequest.rawModeData : ''
    );
  }

  private getrawJsonRequest(requestId: any) {
    return this.rawJsonCollection.requests.filter(request => request.id === requestId)[0];
  }

  private getRawJsonFolder(folderId: any) {
    return folderId ? this.rawJsonCollection.folders.filter(folder => folder.id === folderId)[0] : this.rawJsonCollection;
  }

  private getRawRequestIds(rawJsonFolder: any): [] {
    return rawJsonFolder.order;
  }

  private getRawFoldersIds(rawJsonFolder: any): [] {
    return rawJsonFolder.folders_order;
  }
  private hasRawJsonRequests(rawJsonFolder: any) {
    return rawJsonFolder.order ? true : false;
  }

  private hasRawJsonFolders(rawJsonFolder: any) {
    return rawJsonFolder.folders_order ? true : false;
  }
}
