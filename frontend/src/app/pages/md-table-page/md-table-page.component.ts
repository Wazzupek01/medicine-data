import {Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {ColDef} from "ag-grid-community";
import {AgGridAngular} from "ag-grid-angular";
import {Subscription} from "rxjs";
import {HttpProduktLeczniczyService} from "../../services/http-produkt-leczniczy.service";
import {HttpErrorResponse} from "@angular/common/http";
import {MdPageDto} from "../../models/md-page-dto";
import {MdProduktLeczniczyDto} from "../../models/md-produkt-leczniczy-dto";
import {HttpOpakowanieService} from "../../services/http-opakowanie.service";
import {MdOpakowanieDto} from "../../models/md-opakowanie-dto";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";

@Component({
    selector: 'app-md-table-page',
    templateUrl: './md-table-page.component.html',
    styleUrls: ['./md-table-page.component.css']
})
export class MdTablePageComponent implements OnInit, OnDestroy {

    @ViewChild(AgGridAngular) protected agGrid!: AgGridAngular;
    protected optionForm: FormGroup;
    protected currentPage: number = 0;
    protected allPages: number = 0;
    protected sortTypes: string[] = [];
    protected columnDefs: ColDef[] = [];
    protected rowData: any = [];

    private subscriptions: Subscription[] = [];

    private type: string = "produkt";
    private sortBy: string = "";
    private sortDir: boolean = true;

    constructor(
        private httpProduktLeczniczyService: HttpProduktLeczniczyService,
        private httpOpakowaniaService: HttpOpakowanieService,
        formBuilder: FormBuilder
    ) {
        this.optionForm = formBuilder.group({
            type: new FormControl(this.type),
            sortBy: new FormControl(this.sortBy),
            sortDir: new FormControl(this.sortDir),
        });
    }

    ngOnInit() {
        this.getProductLeczniczyParams();
        this.subscriptions.push(
            this.optionForm.get("type")!.valueChanges.subscribe({
                next: (value) => {
                    this.type = value;
                    this.getSortByList();
                },
                error: (error: HttpErrorResponse) => {},
            })
        );

        this.subscriptions.push(
            this.optionForm.get("sortBy")!.valueChanges.subscribe({
                next: (value) => {
                    this.sortBy = value;
                    this.currentPage = 0;
                    this.getValues();
                }
            })
        );

        this.subscriptions.push(
            this.optionForm.get("sortDir")!.valueChanges.subscribe({
                next: (value) => {
                    this.sortDir = value;
                    this.currentPage = 0;
                    this.getValues();
                }
            })
        );
    }

    ngOnDestroy() {
        this.subscriptions.forEach(s => s.unsubscribe());
    }

    protected pageChanged(page: number) {
        this.currentPage = page;
        this.getValues();
    }

    protected prepareNameForColumns(name: string): string {
        const result = name.replace(/([A-Z])/g, " $1");
        return result.charAt(0).toUpperCase() + result.slice(1);
    }

    private getSortByList() {
        if (this.type == "produkt") {
            this.getProductLeczniczyParams();
        } else {
            this.getOpakaowaniaParams();
        }
        this.currentPage = 0;
    }

    private getValues() {
        if (this.type == "produkt") {
            this.getProduktyLecznicze(this.currentPage, this.sortBy, this.sortDir);
        } else {
            this.getOpakaowania(this.currentPage, this.sortBy, this.sortDir);
        }
    }

    private getProductLeczniczyParams() {
        this.subscriptions.push(
            this.httpProduktLeczniczyService.getParams().subscribe({
                next: (value: string[]) => {
                    const filtered = value.filter(option => {
                        return option != "opakowania" && option != "substancjeCzynne";
                    });

                    this.sortTypes = filtered;
                    this.optionForm.get("sortBy")?.setValue(this.sortTypes[0]);
                    this.columnDefs = filtered.map((value) => {
                        return {field: value};
                    });

                },
                error: (error: HttpErrorResponse) => {
                    console.log(error);
                }
            })
        );
    }

    private getProduktyLecznicze(page: number, sortBy: string, asc: boolean) {
        this.subscriptions.push(
            this.httpProduktLeczniczyService.getAll(sortBy, asc, page).subscribe({
                next: (value: MdPageDto<MdProduktLeczniczyDto>) => {
                    this.currentPage = value.number;
                    this.allPages = value.totalPages;
                    this.prepareProduktyLecznicze(value.content);
                },
                error: (error: HttpErrorResponse) => {
                    console.log(error)
                }
            })
        );
    }

    private prepareProduktyLecznicze(produktyLecznicze: MdProduktLeczniczyDto[]) {
        this.rowData = produktyLecznicze;
    }

    private getOpakaowaniaParams() {
        this.subscriptions.push(
            this.httpOpakowaniaService.getParams().subscribe({
                next: (value: string[]) => {
                    this.sortTypes = value;
                    this.optionForm.get("sortBy")?.setValue(this.sortTypes[0]);
                    this.columnDefs = value.map((value) => {
                        return {field: value};
                    });
                },
                error: (error: HttpErrorResponse) => {
                    console.log(error);
                }
            })
        );
    }

    private getOpakaowania(page: number, sortBy: string, asc: boolean) {
        this.subscriptions.push(
            this.httpOpakowaniaService.getAll(sortBy, asc, page).subscribe({
                next: (value: MdPageDto<MdOpakowanieDto>) => {
                    this.currentPage = value.number;
                    this.allPages = value.totalPages;
                    this.prepareOpakowanie(value.content);
                },
                error: (error: HttpErrorResponse) => {
                    console.log(error)
                }
            })
        );
    }

    private prepareOpakowanie(opakowania: MdOpakowanieDto[]) {
        this.rowData = opakowania;
    }
}
