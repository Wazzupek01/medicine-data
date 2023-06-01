import {Injectable} from '@angular/core';
import {HttpAuthService} from "./http-auth.service";
import {catchError, map, Observable, of, take} from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class PermissionsService {

    constructor(private httpAuthService: HttpAuthService) {
    }

    public canActivateWhenUserLoggedIn(): Observable<boolean> {
        return this.httpAuthService.getUser().pipe(
            take(1)
        ).pipe(
            map((value: string) => {
                return true;
            }),
            catchError((error: any) => {
                return of(false);
            })
        );
    }

    public canActivateWhenUserLoggedOut(): Observable<boolean> {
        return this.httpAuthService.getUser().pipe(
            take(1)
        ).pipe(
            map((value: string) => {
                return false;
            }),
            catchError((error: any) => {
                return of(true);
            })
        );
    }

    public canActivateWhenUserIsAdmin(): Observable<boolean> {
        return this.httpAuthService.isAdmin().pipe(
            take(1)
        ).pipe(
            map((value: boolean) => {
                return value;
            }),
            catchError((error: any) => {
                return of(false);
            })
        );
    }
}
