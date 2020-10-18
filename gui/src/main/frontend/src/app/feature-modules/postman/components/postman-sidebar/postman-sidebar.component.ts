import {
  Component,
  Input,
  OnInit,
  OnChanges,
  SimpleChanges,
} from '@angular/core';
import { Collection } from '../../models/collection.model';
import { CollectionsService } from '../../services/collections.service';
import { CollectionImporterService } from '../../services/collection-importer.service';
import { DiskFileReaderService } from '../../services/disk-file-reader.service';
import { CollectionToMatTreeDataMapper } from '../../services/collection-to-mat-tree-data-mapper.service';
import { TreeNode } from '../../models/tree-node.model';
import { debounceTime, distinctUntilChanged } from 'rxjs/operators';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-postman-sidebar',
  templateUrl: './postman-sidebar.component.html',
  styleUrls: ['./postman-sidebar.component.css']
})
export class PostmanSidebarComponent implements OnInit, OnChanges {
  @Input() postmanCollections: Collection[] = [];
  @Input() active: boolean;

  searchFilter: Subject<string> = new Subject<string>();
  searchText: string;
  treeData: TreeNode[] = [];

  constructor(
    private diskFileReader: DiskFileReaderService,
    private collectionImporter: CollectionImporterService,
    private collectionsService: CollectionsService,
    private collectionToMatTreeDataMapper: CollectionToMatTreeDataMapper
  ) { }

  ngOnInit() {
    this.searchFilter.pipe(debounceTime(500), distinctUntilChanged())
      .subscribe(value => {
        this.searchText = value;
      });
  }

  // Invoked every time the @input changes
  ngOnChanges(changes: SimpleChanges): void {
    this.treeData = this.getTreeData();
  }

  private getTreeData(): TreeNode[] {
    const fileNodes: TreeNode[] = [];
    this.postmanCollections.forEach((collection) => fileNodes.push(this.collectionToMatTreeDataMapper.getTreeData(collection)));
    return fileNodes;
  }

  public readCollectionFiles(files: File[]): void {
    for (const file of files) {
      this.readFile(file);
    }
  }

  private readFile(file: File) {
    this.diskFileReader.readFileAsString(file, this.readFileSuccess.bind(this), this.readFileError.bind(this));
  }

  private readFileSuccess(rawTextCollection) {
    try {
      this.importCollection(rawTextCollection);
    } catch (error) {
      console.log(error);
    }
  }

  private readFileError(error) {
    console.log(error);
  }

  private importCollection(rawTextCollection: string) {
    const postmanCollection = this.getCollection(rawTextCollection);
    this.collectionsService.addCollection(postmanCollection);
  }
  private getCollection(rawTextCollection: string): Collection {
    const rawJsonCollection = this.parseAsJson(rawTextCollection);
    return this.collectionImporter.getCollection(rawJsonCollection);
  }

  private parseAsJson(rawTextCollection: string) {
    return JSON.parse(rawTextCollection);
  }

  filterChanged(filter: string): void {
    this.searchFilter.next(filter);
  }
}
