import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MdLogoutPageComponent } from './md-logout-page.component';

describe('MdLogoutPageComponent', () => {
  let component: MdLogoutPageComponent;
  let fixture: ComponentFixture<MdLogoutPageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MdLogoutPageComponent]
    });
    fixture = TestBed.createComponent(MdLogoutPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
