import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {HttpProduktLeczniczyService} from "../../services/http-produkt-leczniczy.service";
import {MdPageDto} from "../../models/md-page-dto";
import {MdProduktLeczniczyDto} from "../../models/md-produkt-leczniczy-dto";
import {HttpErrorResponse} from "@angular/common/http";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {Location} from "@angular/common";

@Component({
    selector: 'app-md-search-page',
    templateUrl: './md-search-page.component.html',
    styleUrls: ['./md-search-page.component.css']
})
export class MdSearchPageComponent implements OnInit, OnDestroy {

    public name: string = "xanax";

    private subscriptions: Subscription[] = [];

    protected products: MdProduktLeczniczyDto[] = [];
    protected currentPage: number = 0;
    protected allPages: number = 0;
    protected sortTypes: string[] = [];
    protected optionForm: FormGroup;

    protected searchEmpty: boolean = false;

    private sortBy: string = "nazwaProduktu";
    private sortDir: boolean = true;

    constructor(
        private httpProduktLeczniczyService: HttpProduktLeczniczyService,
        private route: ActivatedRoute,
        private router: Router,
        private location: Location,
        formBuilder: FormBuilder
    ) {
        this.optionForm = formBuilder.group({
            sortBy: new FormControl(this.sortBy),
            sortDir: new FormControl(this.sortDir),
        });

        const navigationData = this.router.getCurrentNavigation()?.extras.state
        if (navigationData) {
            this.name = navigationData["search"];
        }
    }

    ngOnInit() {
        this.getProductLeczniczyParams();
        this.performSearch();

        this.subscriptions.push(
            this.optionForm.get("sortBy")!.valueChanges.subscribe({
                next: (value) => {
                    this.sortBy = value;
                    this.currentPage = 0;
                    this.performSearch();
                }
            })
        );

        this.subscriptions.push(
            this.optionForm.get("sortDir")!.valueChanges.subscribe({
                next: (value) => {
                    this.sortDir = value;
                    this.currentPage = 0;
                    this.performSearch();
                }
            })
        );
    }

    ngOnDestroy() {
        this.subscriptions.forEach(s => s.unsubscribe());
    }

    protected pageChanged(page: number) {
        this.currentPage = page;
        this.performSearch();
    }

    protected prepareNameForColumns(name: string): string {
        const result = name.replace(/([A-Z])/g, " $1");
        return result.charAt(0).toUpperCase() + result.slice(1);
    }

    private getProductLeczniczyParams() {
        this.subscriptions.push(
            this.httpProduktLeczniczyService.getParams().subscribe({
                next: (value: string[]) => {
                    this.sortTypes = value.filter(option => {
                        return option != "opakowania" && option != "substancjeCzynne";
                    });
                    this.optionForm.get("sortBy")?.setValue(this.sortTypes[0]);
                },
                error: (error: HttpErrorResponse) => {
                    console.log(error);
                }
            })
        );
    }

    private performSearch() {
        this.subscriptions.push(
            this.httpProduktLeczniczyService.geByNames(this.name, this.sortBy, this.sortDir, this.currentPage).subscribe({
                next: (value: MdPageDto<MdProduktLeczniczyDto>) => {
                    this.searchEmpty = false;
                    this.currentPage = value.number;
                    this.allPages = value.totalPages;
                    this.products = value.content;
                },
                error: (error: HttpErrorResponse) => {
                    this.searchEmpty = true;
                }
            })
        );
    }
}
