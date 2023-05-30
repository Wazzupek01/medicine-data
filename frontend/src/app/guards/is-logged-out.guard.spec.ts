import {TestBed} from '@angular/core/testing';
import {CanActivateFn} from '@angular/router';

import {isLoggedOutGuard} from './is-logged-out.guard';

describe('isLoggedOutGuard', () => {
    const executeGuard: CanActivateFn = (...guardParameters) =>
        TestBed.runInInjectionContext(() => isLoggedOutGuard(...guardParameters));

    beforeEach(() => {
        TestBed.configureTestingModule({});
    });

    it('should be created', () => {
        expect(executeGuard).toBeTruthy();
    });
});
