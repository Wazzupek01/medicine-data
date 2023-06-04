import {CanActivateFn, Router} from '@angular/router';
import {inject} from "@angular/core";
import {PermissionsService} from "../services/permissions.service";
import {take, tap} from "rxjs";

export const isLoginGuard: CanActivateFn = (route, state) => {
    const router = inject(Router);

    return inject(PermissionsService).canActivateWhenUserLoggedIn().pipe(
        take(1),
        tap(async (loggedIn) => {
            if (!loggedIn) {
                await router.navigateByUrl("/");
            }
            return loggedIn;
        })
    );
};
