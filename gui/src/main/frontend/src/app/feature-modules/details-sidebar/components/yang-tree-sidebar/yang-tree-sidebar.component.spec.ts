import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { YangTreeSideBarComponent } from './yang-tree-sidebar.component';

describe('YangTreeComponent', () => {
  let component: YangTreeSideBarComponent;
  let fixture: ComponentFixture<YangTreeSideBarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ YangTreeSideBarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(YangTreeSideBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
