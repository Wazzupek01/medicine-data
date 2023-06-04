import {Injectable} from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class LocalStorageService {

    constructor() {
    }

    public setValue(key: string, value: string) {
        localStorage.setItem(key, value);
    }

    public getValue(key: string): string | null {
        return localStorage.getItem(key);
    }

    public removeValue(key: string) {
        localStorage.removeItem(key);
    }
}
