import {ComponentFixture, TestBed} from '@angular/core/testing';

import {MdMainPageComponent} from './md-main-page.component';

describe('MdMainPageComponent', () => {
    let component: MdMainPageComponent;
    let fixture: ComponentFixture<MdMainPageComponent>;

    beforeEach(() => {
        TestBed.configureTestingModule({
            declarations: [MdMainPageComponent]
        });
        fixture = TestBed.createComponent(MdMainPageComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
