import {CanActivateFn} from '@angular/router';
import {inject} from "@angular/core";
import {PermissionsService} from "../services/permissions.service";

export const isAdminGuard: CanActivateFn = (route, state) => {
    // return inject(PermissionsService).canActivateWhenUserIsAdmin();
    return true;
};

