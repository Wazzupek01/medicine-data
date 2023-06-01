import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class HttpAuthService {

    constructor(private http: HttpClient) {
    }

    public getUser(): Observable<string> {
        return this.http.get<string>("https://localhost:8080/get/user", {withCredentials: true});
    }

    public isAdmin(): Observable<boolean> {
        return this.http.get<boolean>("https://localhost:8080/user/admin", {withCredentials: true});
    }
}
