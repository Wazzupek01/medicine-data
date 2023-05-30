import {Injectable} from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class PermissionsService {

    constructor() {
    }

    public canActivateWhenUserLoggedIn(): boolean {
        return true;
    }

    public canActivateWhenUserLoggedOut(): boolean {
        return true;
    }

}
