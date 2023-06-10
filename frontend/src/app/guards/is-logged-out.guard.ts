import {CanActivateFn, Router} from '@angular/router';
import {inject} from "@angular/core";
import {PermissionsService} from "../services/permissions.service";
import {take, tap} from "rxjs";

export const isLoggedOutGuard: CanActivateFn = (route, state) => {
    const router = inject(Router);

    return inject(PermissionsService).canActivateWhenUserLoggedOut().pipe(
        take(1),
        tap(async (loggedOut) => {
            if (!loggedOut) {
                await router.navigateByUrl("/");
            }
            return loggedOut;
        })
    );
};

