import { TestBed } from '@angular/core/testing';

import { MdLocalstorageService } from './md-localstorage.service';

describe('MdLocalstorageService', () => {
  let service: MdLocalstorageService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MdLocalstorageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
