import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {HttpProduktLeczniczyService} from "../../services/http-produkt-leczniczy.service";
import {HttpErrorResponse} from "@angular/common/http";
import {MdDownloadOptions} from "../../models/md-download-options";
import {DomSanitizer, SafeResourceUrl} from "@angular/platform-browser";

@Component({
    selector: 'app-md-download-page',
    templateUrl: './md-download-page.component.html',
    styleUrls: ['./md-download-page.component.css']
})
export class MdDownloadPageComponent implements OnInit, OnDestroy {

    private subscriptions: Subscription[] = [];

    private params: string[] = [];

    protected avalibleSortOptions: string[] = [];

    protected saveJSON: boolean = false;
    protected saveXML: boolean = false;

    protected fileUrl!: SafeResourceUrl;

    constructor(
        private httpProduktLeczniczyService: HttpProduktLeczniczyService,
        private sanitizer: DomSanitizer
    ) {
    }

    ngOnInit() {
        this.subscriptions.push(
            this.httpProduktLeczniczyService.getParams().subscribe({
                next: (value: string[]) => {
                    this.avalibleSortOptions = value;
                },
                error: (error: HttpErrorResponse) => {
                    console.log(error);
                }
            })
        );
    }

    ngOnDestroy() {
        this.subscriptions.forEach(s => s.unsubscribe());
    }

    protected downloadJson() {
        const downloadParams: MdDownloadOptions = {
            nullFields: [],
            ascending: true,
            sortBy: null,
            elementsNum: 20
        };

        this.subscriptions.push(
            this.httpProduktLeczniczyService.downloadJson(downloadParams).subscribe({
                next: (value: Blob) => {
                    const blob = new Blob([JSON.stringify(value, null, 4)], {type: 'application/json'});
                    this.fileUrl = this.sanitizer.bypassSecurityTrustResourceUrl(window.URL.createObjectURL(blob));
                    this.saveJSON = true;
                },
                error: (error: HttpErrorResponse) => {
                    console.log(error);
                }
            })
        );
    }

    protected downloadXml() {
        const downloadParams: MdDownloadOptions = {
            nullFields: [],
            ascending: true,
            sortBy: null,
            elementsNum: 20
        };

        this.subscriptions.push(
            this.httpProduktLeczniczyService.downloadXml(downloadParams).subscribe({
                next: (value: string) => {
                    const blob = new Blob([value], {type: 'application/xml'});
                    this.fileUrl = this.sanitizer.bypassSecurityTrustResourceUrl(window.URL.createObjectURL(blob));
                    this.saveXML = true;
                },
                error: (error: HttpErrorResponse) => {
                    console.log(error);
                }
            })
        );
    }

    protected hideButtons() {
        this.saveJSON = false;
        this.saveXML = false;
    }
}
