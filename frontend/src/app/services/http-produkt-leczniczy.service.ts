import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {MdPageDto} from "../models/md-page-dto";
import {MdProduktLeczniczyDto} from "../models/md-produkt-leczniczy-dto";
import {Observable} from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class HttpProduktLeczniczyService {

    private baseApiUrl: string = environment.apiUrl + "/produkt";

    constructor(private http: HttpClient) {
    }

    public getParams(): Observable<string[]> {
        return this.http.get<string[]>(`${this.baseApiUrl}/params`, {withCredentials: true});
    }

    public geByNames(name: string, sortBy: string, isAscending: boolean, page: number): Observable<MdPageDto<MdProduktLeczniczyDto>> {
        return this.http.get<MdPageDto<MdProduktLeczniczyDto>>(`${this.baseApiUrl}/name/${name}/${sortBy}/${isAscending}/${page}`, {withCredentials: true});
    }

    public getAll(sortBy: string, isAscending: boolean, page: number): Observable<MdPageDto<MdProduktLeczniczyDto>> {
        return this.http.get<MdPageDto<MdProduktLeczniczyDto>>(`${this.baseApiUrl}/all/${sortBy}/${isAscending}/${page}`, {withCredentials: true});
    }
}
