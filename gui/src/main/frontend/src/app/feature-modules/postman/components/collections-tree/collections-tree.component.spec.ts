import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CollectionsTreeComponent } from './collections-tree.component';

describe('CollectionsTreeComponent', () => {
  let component: CollectionsTreeComponent;
  let fixture: ComponentFixture<CollectionsTreeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CollectionsTreeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CollectionsTreeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
