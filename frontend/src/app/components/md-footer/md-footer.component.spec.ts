import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MdFooterComponent } from './md-footer.component';

describe('MdFooterComponent', () => {
  let component: MdFooterComponent;
  let fixture: ComponentFixture<MdFooterComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MdFooterComponent]
    });
    fixture = TestBed.createComponent(MdFooterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
