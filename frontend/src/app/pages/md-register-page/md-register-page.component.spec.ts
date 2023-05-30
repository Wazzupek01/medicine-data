import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MdRegisterPageComponent } from './md-register-page.component';

describe('MdRegisterPageComponent', () => {
  let component: MdRegisterPageComponent;
  let fixture: ComponentFixture<MdRegisterPageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MdRegisterPageComponent]
    });
    fixture = TestBed.createComponent(MdRegisterPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
