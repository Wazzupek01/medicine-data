import {CanActivateFn, Router} from '@angular/router';
import {inject} from "@angular/core";
import {PermissionsService} from "../services/permissions.service";
import {take, tap} from "rxjs";

export const isAdminGuard: CanActivateFn = (route, state) => {
    const router = inject(Router);

    return inject(PermissionsService).canActivateWhenUserIsAdmin().pipe(
        take(1),
        tap(async (admin) => {
            if (!admin) {
                await router.navigateByUrl("/");
            }
            return admin;
        })
    );
};

