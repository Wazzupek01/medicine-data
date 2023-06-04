import {Component, EventEmitter, Input, OnDestroy, OnInit, Output} from '@angular/core';

@Component({
    selector: 'app-md-pagination',
    templateUrl: './md-pagination.component.html',
    styleUrls: ['./md-pagination.component.css']
})
export class MdPaginationComponent implements OnInit, OnDestroy {

    @Input("currentPage") public currentPage!: number;
    @Input("allPages") public allPages!: number;

    @Output("pageChanged") public pageChanged = new EventEmitter<number>();

    protected lowpageNumbers: number[] = []

    constructor() {
    }

    ngOnInit() {
        if (this.allPages <= 5) {
            this.lowpageNumbers = Array.from({length: this.allPages}, (_, i) => i);
        }
    }

    ngOnDestroy() {
    }

    protected pageChangedHandler(page: number) {
        this.currentPage = page;
        this.pageChanged.emit(page);
    }
}
