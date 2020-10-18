import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PostmanSidebarComponent } from './postman-sidebar.component';

describe('PostmanSidebarComponent', () => {
  let component: PostmanSidebarComponent;
  let fixture: ComponentFixture<PostmanSidebarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PostmanSidebarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PostmanSidebarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
