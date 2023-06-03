import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {HttpProduktLeczniczyService} from "../../services/http-produkt-leczniczy.service";

@Component({
  selector: 'app-md-search-page',
  templateUrl: './md-search-page.component.html',
  styleUrls: ['./md-search-page.component.css']
})
export class MdSearchPageComponent implements OnInit, OnDestroy{

    private subscriptions: Subscription[] = [];

    constructor(
        private httpProduktLeczniczyService: HttpProduktLeczniczyService
    ) {
    }

    ngOnInit() {
    }

    ngOnDestroy() {
        this.subscriptions.forEach(s => s.unsubscribe());
    }

    private performSearch() {
        this.subscriptions.push(
        )
    }
}
