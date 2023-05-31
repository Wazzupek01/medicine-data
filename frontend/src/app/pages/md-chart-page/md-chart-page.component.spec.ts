import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MdChartPageComponent } from './md-chart-page.component';

describe('MdChartPageComponent', () => {
  let component: MdChartPageComponent;
  let fixture: ComponentFixture<MdChartPageComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MdChartPageComponent]
    });
    fixture = TestBed.createComponent(MdChartPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
