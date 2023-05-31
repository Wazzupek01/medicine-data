import {Component, ViewChild} from '@angular/core';
import {CellClickedEvent, ColDef, GridReadyEvent} from "ag-grid-community";
import {AgGridAngular} from "ag-grid-angular";

@Component({
  selector: 'app-md-table-page',
  templateUrl: './md-table-page.component.html',
  styleUrls: ['./md-table-page.component.css']
})
export class MdTablePageComponent {

    @ViewChild(AgGridAngular) agGrid!: AgGridAngular;


    protected columnDefs: ColDef[] = [
        { field: 'make' },
        { field: 'model' },
        { field: 'price' }
    ];

    protected rowData = [
        { make: 'Toyota', model: 'Celica', price: 35000 },
        { make: 'Ford', model: 'Mondeo', price: 32000 },
        { make: 'Porsche', model: 'Boxster', price: 72000 }
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
    onCellClicked( e: CellClickedEvent): void {
        console.log('cellClicked', e);
    }

    // Example using Grid's API
    clearSelection(): void {
        this.agGrid.api.deselectAll();
    }
}
