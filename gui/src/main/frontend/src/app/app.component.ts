import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { CollectionsService } from './feature-modules/postman/services/collections.service';
import { Collection } from './feature-modules/postman/models/collection.model';

declare var webvowl;
declare var $;

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  topology: any = null;
  postmanCollections: Collection[] = [];
  active: boolean;
  nodeId: string;

  constructor(private http: HttpClient, private collectionService: CollectionsService, public dialog: MatDialog) {
    // this.nodeId = "";
  }


  ngOnInit() {
    // console.log(webvowl);

    // webvowl.app().initialize();
    this.collectionService.getCollections().subscribe((collections) => {
      this.postmanCollections = collections;
    });
    this.http.get('api/topology').subscribe((res) => {
      this.topology = res;
      webvowl.app().initialize();
      // Jquery action after webvowl loaded
      webvowl.app().getOptions().loadingModule().parseOntologyContent(this.topology);
    });
    // postmanCollections: PostmanCollection[] = [];
    // active: boolean;
    // constructor(private http: HttpClient, private collectionService: CollectionsService) {
    //   /*this.http.get('api/topology').subscribe((res) => {
    //     this.topology = res;
    //   });*/
    this.collectionService.getCollections().subscribe((collections) => this.postmanCollections = collections);
  }

  toggleSidebar() {
    this.active = !this.active;
  }

  // ngAfterViewInit() {
  //   // let nodes = this.graph.nativeElement.querySelector('.node');
  //   console.log("list of nodes: " +  JSON.stringify(this.graph));
  //   //this.node.nativeElement.focus();
  // }

  // @HostListener('document:click', ['$event.path'])
  // public onGlobalClick(targetElementPath: Array<any>) {
  //   targetElementPath.forEach(element => {
  //     console.log(element);
  //   });
  // }

  handleGraphClick({ target }: Event) {
    const { tagName, parentElement, classList } = target as HTMLElement;
    // console.log(classList);
    if (tagName === 'rect' && classList.contains('focused')) {
      // const dialogRef = this.dialog.open(YangTreeSideBarComponent, {width: '1000px'});
      // dialogRef.componentInstance.nodeId = parentElement.attributes.getNamedItem('id').nodeValue;
      this.nodeId = parentElement.attributes.getNamedItem('id').nodeValue;
    } else {
      this.nodeId = null;
      webvowl.app().getOptions().focuserModule().handle();
    }
  }

}
