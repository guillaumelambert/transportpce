import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RequestComponent} from './components/request/request.component';
import {CollectionComponent} from './components/collection/collection.component';
import {PostmanSidebarComponent} from './components/postman-sidebar/postman-sidebar.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { NgxJsonViewerModule } from 'ngx-json-viewer';
import { CollectionsTreeComponent } from './components/collections-tree/collections-tree.component';
import { MatTreeModule, MatIconModule, MatButtonModule } from '@angular/material';
@NgModule({
  declarations: [PostmanSidebarComponent, CollectionComponent, RequestComponent, CollectionsTreeComponent],
  imports: [
    CommonModule,
    NgbModule,
    NgxJsonViewerModule,
    MatTreeModule,
    MatIconModule,
    MatButtonModule
  ],
  exports: [PostmanSidebarComponent, CollectionComponent, RequestComponent]
})
export class PostmanModule {
}
