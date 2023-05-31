import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MdNavbarComponent } from './md-navbar.component';

describe('MdNavbarComponent', () => {
  let component: MdNavbarComponent;
  let fixture: ComponentFixture<MdNavbarComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MdNavbarComponent]
    });
    fixture = TestBed.createComponent(MdNavbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
