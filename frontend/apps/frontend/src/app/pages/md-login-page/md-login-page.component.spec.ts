import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MdLoginPageComponent } from './md-login-page.component';

describe('MdLoginPageComponent', () => {
  let component: MdLoginPageComponent;
  let fixture: ComponentFixture<MdLoginPageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MdLoginPageComponent],
    });
    fixture = TestBed.createComponent(MdLoginPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
