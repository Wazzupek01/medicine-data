import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {HttpProduktLeczniczyService} from "../../services/http-produkt-leczniczy.service";
import {HttpErrorResponse} from "@angular/common/http";
import {MdDownloadOptions} from "../../models/md-download-options";
import {DomSanitizer, SafeResourceUrl} from "@angular/platform-browser";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";

@Component({
    selector: 'app-md-download-page',
    templateUrl: './md-download-page.component.html',
    styleUrls: ['./md-download-page.component.css']
})
export class MdDownloadPageComponent implements OnInit, OnDestroy {

    private subscriptions: Subscription[] = [];
    private selectedOptions: string[] = [];

    protected avalibleSortOptions: string[] = [];
    protected optionsForm: FormGroup;
    protected saveJSON: boolean = false;
    protected saveXML: boolean = false;
    protected sortOptions: string[] = [];
    protected fileUrl!: SafeResourceUrl;

    constructor(
        private httpProduktLeczniczyService: HttpProduktLeczniczyService,
        private sanitizer: DomSanitizer,
        formBuilder: FormBuilder
    ) {
        this.optionsForm = formBuilder.group({
            sortBy: new FormControl(this.avalibleSortOptions[0]),
            sortDir: new FormControl(true),
            length: new FormControl(20)
        });
    }

    ngOnInit() {
        this.subscriptions.push(
            this.httpProduktLeczniczyService.getParams().subscribe({
                next: (value: string[]) => {
                    this.avalibleSortOptions = value;
                    this.sortOptions.push("null");
                    this.sortOptions = this.sortOptions.concat(value.filter(option => {
                        return option != "opakowania" && option != "substancjeCzynne";
                    }));
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
            nullFields: this.prepareInvisibleFields(),
            ascending: this.optionsForm.get("sortDir")?.value,
            sortBy: this.optionsForm.get("sortBy")?.value,
            elementsNum: this.optionsForm.get("length")?.value
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
            nullFields: this.prepareInvisibleFields(),
            ascending: this.optionsForm.get("sortDir")?.value,
            sortBy: this.optionsForm.get("sortBy")?.value,
            elementsNum: this.optionsForm.get("length")?.value
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

    protected updateVisibleFields(field: string) {
        if (this.selectedOptions.includes(field)) {
            this.selectedOptions = this.selectedOptions.filter(s => s !== field);
        } else {
            this.selectedOptions.push(field);
        }
    }

    protected prepareNameForColumns(name: string): string {
        const result = name.replace(/([A-Z])/g, " $1");
        return result.charAt(0).toUpperCase() + result.slice(1);
    }

    private prepareInvisibleFields(): string[] {
        const invisibleFields: string[] = [];
        if (this.selectedOptions.length === 0) {
            return [];
        }

        this.avalibleSortOptions.map(s => {
            if (!this.selectedOptions.includes(s)) {
                invisibleFields.push(s);
            }
        });
        return invisibleFields;
    }
}
