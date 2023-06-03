import {Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {CellClickedEvent, ColDef, GridReadyEvent} from "ag-grid-community";
import {AgGridAngular} from "ag-grid-angular";
import {Subscription} from "rxjs";
import {HttpProduktLeczniczyService} from "../../services/http-produkt-leczniczy.service";
import {HttpErrorResponse} from "@angular/common/http";
import {MdPageDto} from "../../models/md-page-dto";
import {MdProduktLeczniczyDto} from "../../models/md-produkt-leczniczy-dto";
import {MdOpakowanieDto} from "../../models/md-opakowanie-dto";

@Component({
    selector: 'app-md-table-page',
    templateUrl: './md-table-page.component.html',
    styleUrls: ['./md-table-page.component.css']
})
export class MdTablePageComponent implements OnInit, OnDestroy {

    @ViewChild(AgGridAngular) agGrid!: AgGridAngular;

    private subscriptions: Subscription[] = [];

    private valuesFromWeb!: MdPageDto<MdProduktLeczniczyDto> | MdPageDto<MdOpakowanieDto>;

    constructor(private httpProduktLeczniczyService: HttpProduktLeczniczyService) {

    }

    ngOnInit() {
    }

    ngOnDestroy() {
        this.subscriptions.forEach(s => s.unsubscribe());
    }

    protected columnDefs: ColDef[] = [
        {field: 'make'},
        {field: 'model'},
        {field: 'price'}
    ];

    protected rowData = [
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

    protected getProduktyLecznicze() {
        this.subscriptions.push(
            this.httpProduktLeczniczyService.getAll("nazwaProduktu", true, 0).subscribe({
                next: (value: MdPageDto<MdProduktLeczniczyDto>) => {
                    this.valuesFromWeb = value;
                },
                error: (error: HttpErrorResponse) => {
                    console.log(error)
                }
            })
        );
    }
}
