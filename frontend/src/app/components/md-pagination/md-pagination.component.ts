import {Component, EventEmitter, Input, OnDestroy, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-md-pagination',
  templateUrl: './md-pagination.component.html',
  styleUrls: ['./md-pagination.component.css']
})
export class MdPaginationComponent implements OnInit, OnDestroy{

    @Input("currentPage") public currentPage!: number;
    @Input("allPages") public allPages!: number;

    @Output("pageChanged") public pageChanged = new EventEmitter<number>();


    constructor() {
    }

    ngOnInit() {
    }

    ngOnDestroy() {
    }

    protected pageChangedHandler(page: number) {
        this.currentPage = page;
        this.pageChanged.emit(page);
    }
}
