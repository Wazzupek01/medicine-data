import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {MdPageDto} from "../models/md-page-dto";
import {MdProduktLeczniczyDto} from "../models/md-produkt-leczniczy-dto";
import {Observable} from "rxjs";
import {MdCountResult} from "../models/md-count-result";
import {MdDownloadOptions} from "../models/md-download-options";

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

    public getTopPostaci(): Observable<MdCountResult[]> {
        return this.http.get<MdCountResult[]>(`${this.baseApiUrl}/toppostaci`, {withCredentials: true});
    }

    public getTopSubstancji(): Observable<MdCountResult[]> {
        return this.http.get<MdCountResult[]>(`${this.baseApiUrl}/topsubstancje`, {withCredentials: true});
    }

    public downloadJson(body: MdDownloadOptions) {
        return this.http.post<Blob>(`${this.baseApiUrl}/json`, body, {withCredentials: true, responseType: "json"});
    }

    public downloadXml(body: MdDownloadOptions) {
        return this.http.post(`${this.baseApiUrl}/xml`, body, {
            withCredentials: true,
            observe: 'body',
            responseType: "text"
        });
    }
}
