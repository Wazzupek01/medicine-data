import {CanActivateFn} from '@angular/router';
import {inject} from "@angular/core";
import {PermissionsService} from "../services/permissions.service";

export const isLoggedOutGuard: CanActivateFn = (route, state) => {
    // return inject(PermissionsService).canActivateWhenUserLoggedOut()
    return true;
};

