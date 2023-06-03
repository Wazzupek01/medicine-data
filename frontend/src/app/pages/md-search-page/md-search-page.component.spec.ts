import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MdSearchPageComponent } from './md-search-page.component';

describe('MdSearchPageComponent', () => {
  let component: MdSearchPageComponent;
  let fixture: ComponentFixture<MdSearchPageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MdSearchPageComponent]
    });
    fixture = TestBed.createComponent(MdSearchPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
