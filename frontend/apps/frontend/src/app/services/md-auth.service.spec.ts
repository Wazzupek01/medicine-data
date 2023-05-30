import { TestBed } from '@angular/core/testing';

import { MdAuthService } from './md-auth.service';

describe('MdAuthService', () => {
  let service: MdAuthService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MdAuthService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
