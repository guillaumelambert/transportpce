import {Injectable} from '@angular/core';
import {Collection} from '../models/collection.model';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CollectionsService {
  private postmanCollections: Collection[] = [];
  private subject = new Subject<Collection[]>();
  constructor() {}

  getCollections(): Subject<Collection[]> {
    return this.subject;
  }

  addCollection(postmanCollection: Collection) {
    this.postmanCollections = [...this.postmanCollections, postmanCollection];
    this.subject.next(this.postmanCollections);
  }
}
