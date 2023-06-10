import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {MdPageDto} from "../models/md-page-dto";
import {MdOpakowanieDto} from "../models/md-opakowanie-dto";
import {MdCountResult} from "../models/md-count-result";

@Injectable({
    providedIn: 'root'
})
export class HttpOpakowanieService {

    private baseApiUrl: string = environment.apiUrl + "/opakowanie";

    constructor(
        private http: HttpClient
    ) {
    }

    public getParams(): Observable<string[]> {
        return this.http.get<string[]>(`${this.baseApiUrl}/params`, {withCredentials: true});
    }

    public getAll(sortBy: string, isAscending: boolean, page: number): Observable<MdPageDto<MdOpakowanieDto>> {
        return this.http.get<MdPageDto<MdOpakowanieDto>>(`${this.baseApiUrl}/all/${sortBy}/${isAscending}/${page}`, {withCredentials: true});
    }

    public getKategoriaDostepnosci(): Observable<MdCountResult[]> {
        return this.http.get<MdCountResult[]>(`${this.baseApiUrl}/kategoriedostepnosci`, {withCredentials: true});
    }
}
