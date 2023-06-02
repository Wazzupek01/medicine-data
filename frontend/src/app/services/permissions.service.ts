import {Injectable} from '@angular/core';
import {HttpAuthService} from "./http-auth.service";
import {catchError, map, Observable, of, take} from "rxjs";
import {LocalStorageService} from "./local-storage.service";
import {MdConst} from "../md-const";

@Injectable({
    providedIn: 'root'
})
export class PermissionsService {

    constructor(
        private httpAuthService: HttpAuthService,
        private localStorageService: LocalStorageService) {
    }

    public canActivateWhenUserLoggedIn(): Observable<boolean> {
        return this.httpAuthService.getUser().pipe(
            take(1)
        ).pipe(
            map((value: string) => {
                this.localStorageService.setValue(MdConst.USEREMAIL, value);
                return true;
            }),
            catchError((error: any) => {
                this.localStorageService.removeValue(MdConst.USEREMAIL);
                return of(false);
            })
        );
    }

    public canActivateWhenUserLoggedOut(): Observable<boolean> {
        return this.httpAuthService.getUser().pipe(
            take(1)
        ).pipe(
            map((value: string) => {
                this.localStorageService.setValue(MdConst.USEREMAIL, value);
                return false;
            }),
            catchError((error: any) => {
                this.localStorageService.removeValue(MdConst.USEREMAIL);
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
