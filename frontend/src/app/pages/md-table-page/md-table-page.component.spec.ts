import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MdTablePageComponent } from './md-table-page.component';

describe('MdTablePageComponent', () => {
  let component: MdTablePageComponent;
  let fixture: ComponentFixture<MdTablePageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MdTablePageComponent]
    });
    fixture = TestBed.createComponent(MdTablePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
