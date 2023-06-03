import {Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {CellClickedEvent, ColDef, GridReadyEvent} from "ag-grid-community";
import {AgGridAngular} from "ag-grid-angular";
import {Subscription} from "rxjs";
import {HttpProduktLeczniczyService} from "../../services/http-produkt-leczniczy.service";
import {HttpErrorResponse} from "@angular/common/http";
import {MdPageDto} from "../../models/md-page-dto";
import {MdProduktLeczniczyDto, ProduktLeczniczyKeys} from "../../models/md-produkt-leczniczy-dto";
import {MdOpakowanieDto} from "../../models/md-opakowanie-dto";
import {keys} from 'ts-transformer-keys';

@Component({
    selector: 'app-md-table-page',
    templateUrl: './md-table-page.component.html',
    styleUrls: ['./md-table-page.component.css']
})
export class MdTablePageComponent implements OnInit, OnDestroy {

    @ViewChild(AgGridAngular) agGrid!: AgGridAngular;

    private subscriptions: Subscription[] = [];
    private valuesFromWeb!: MdPageDto<MdProduktLeczniczyDto> | MdPageDto<MdOpakowanieDto>;

    protected currentPage: number = 0;
    protected allPages: number = 0;

    constructor(private httpProduktLeczniczyService: HttpProduktLeczniczyService) {

    }

    ngOnInit() {
        this.getProduktyLecznicze(this.currentPage);
    }

    ngOnDestroy() {
        this.subscriptions.forEach(s => s.unsubscribe());
    }

    protected columnDefs: ColDef[] = [
        {field: 'make'},
        {field: 'model'},
        {field: 'price'}
    ];

    protected rowData: any = [
        {make: 'Toyota', model: 'Celica', price: 35000},
        {make: 'Ford', model: 'Mondeo', price: 32000},
        {make: 'Porsche', model: 'Boxster', price: 72000}
    ];

    protected defaultColDef: ColDef = {
        sortable: true,
        filter: true,
    };

    onGridReady(params: GridReadyEvent) {
        // this.rowData$ = this.http
        //     .get<any[]>('https://www.ag-grid.com/example-assets/row-data.json');
    }

    // Example of consuming Grid Event
    onCellClicked(e: CellClickedEvent): void {
        console.log('cellClicked', e);
    }

    // Example using Grid's API
    clearSelection(): void {
        this.agGrid.api.deselectAll();
    }

    private getProduktyLecznicze(page: number) {
        this.subscriptions.push(
            this.httpProduktLeczniczyService.getAll("nazwaProduktu", true, page).subscribe({
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

    protected pageChanged(page: number) {
        this.currentPage = page;
        this.getProduktyLecznicze(page);
    }

    private prepareProduktyLecznicze(produktyLecznicze: MdProduktLeczniczyDto[]) {
        this.columnDefs = [];
        this.columnDefs = ProduktLeczniczyKeys.map((value) => { return {field: value}});

        this.rowData = produktyLecznicze;
    }
}
