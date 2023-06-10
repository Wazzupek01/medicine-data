import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {MdLoginDto} from "../models/md-login-dto";
import {MdRegisterDto} from "../models/md-register-dto";
import {environment} from '../../environments/environment';
import {MdUserInfoDto} from "../models/md-user-info-dto";

@Injectable({
    providedIn: 'root'
})
export class HttpAuthService {

    private baseApiUrl: string = environment.apiUrl + "/auth";

    constructor(private http: HttpClient) {
    }

    public getUser(): Observable<MdUserInfoDto> {
        return this.http.get<MdUserInfoDto>(this.baseApiUrl + "/user", {withCredentials: true});
    }

    public isAdmin(): Observable<boolean> {
        return this.http.get<boolean>(this.baseApiUrl + "/admin", {withCredentials: true});
    }

    public loginUser(loginDto: MdLoginDto): Observable<MdUserInfoDto> {
        return this.http.post<MdUserInfoDto>(this.baseApiUrl + "/login", loginDto, {withCredentials: true});
    }

    public registerUser(registerDto: MdRegisterDto): Observable<MdUserInfoDto> {
        return this.http.post<MdUserInfoDto>(this.baseApiUrl + "/register", registerDto, {withCredentials: true});
    }

    public logoutUser(): Observable<any> {
        return this.http.get(this.baseApiUrl + "/logout", {withCredentials: true});
    }
}
