import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {MdLoginDto} from "../models/md-login-dto";
import {MdRegisterDto} from "../models/md-register-dto";
import {environment} from '../../environments/environment';

@Injectable({
    providedIn: 'root'
})
export class HttpAuthService {

    private baseApiUrl: string = environment.apiUrl + "/auth";

    constructor(private http: HttpClient) {
    }

    public getUser(): Observable<string> {
        return this.http.get<string>(this.baseApiUrl + "/user", {withCredentials: true});
    }

    public isAdmin(): Observable<boolean> {
        return this.http.get<boolean>(this.baseApiUrl + "/admin", {withCredentials: true});
    }

    public loginUser(loginDto: MdLoginDto): Observable<string> {
        return this.http.post<string>(this.baseApiUrl + "/login", loginDto, {withCredentials: true});
    }

    public registerUser(registerDto: MdRegisterDto): Observable<string> {
        return this.http.post<string>(this.baseApiUrl + "/register", registerDto, {withCredentials: true});
    }

    public logoutUser(): Observable<any> {
        return this.http.get(this.baseApiUrl + "/logout", {withCredentials: true});
    }
}
