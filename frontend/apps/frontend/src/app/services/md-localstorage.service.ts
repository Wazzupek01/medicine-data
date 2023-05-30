import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class MdLocalstorageService {
  constructor() {}

  public getValue(key: string): string | null {
    return localStorage.getItem(key);
  }

  public setValue(key: string, value: string) {
    localStorage.setItem(key, value);
  }

  public removeValue(key: string) {
    localStorage.removeItem(key);
  }
}
