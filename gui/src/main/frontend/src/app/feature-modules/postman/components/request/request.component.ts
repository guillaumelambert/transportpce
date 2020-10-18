import { Component, OnInit, Input, ViewEncapsulation, OnChanges, SimpleChanges } from '@angular/core';
import { Request } from 'src/app/feature-modules/postman/models/request.model';
import { HttpMethodCssFactoryService } from '../../services/http-method-css-factory.service';
import { HttpMethod } from '../../models/http-method.enum';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { HttpRequester } from '../../services/http-requester.service';

@Component({
  selector: 'app-request',
  templateUrl: './request.component.html',
  styleUrls: ['./request.component.scss'],
})
export class RequestComponent implements OnInit {
  @Input() request: Request;
  methodCssClass: string;
  requestResponse: {};
  constructor(
    private httpRequester: HttpRequester,
    private methodCssFactory: HttpMethodCssFactoryService,
    private modalService: NgbModal
  ) {}

  ngOnInit() {
    this.methodCssClass = this.methodCssFactory.getCssClass(this.request.method);
  }

  executeAndShowRequest(modalContent) {
    this.httpRequester.invokeRequest(this.request)
    .subscribe(
      data => {
        this.requestResponse = data;
        this.modalService.open(modalContent, { scrollable: true });
    }
    );
  }

  getMethodCssClass(httpMethod: HttpMethod): string {
    return this.methodCssFactory.getCssClass(httpMethod);
  }
}
