import {Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {ChartConfiguration, ChartData} from 'chart.js';
import {BaseChartDirective} from 'ng2-charts';
import {Subscription} from "rxjs";
import {HttpOpakowanieService} from "../../services/http-opakowanie.service";
import {HttpProduktLeczniczyService} from "../../services/http-produkt-leczniczy.service";
import {HttpErrorResponse} from "@angular/common/http";
import {MdCountResult} from "../../models/md-count-result";

@Component({
    selector: 'app-md-chart-page',
    templateUrl: './md-chart-page.component.html',
    styleUrls: ['./md-chart-page.component.css']
})
export class MdChartPageComponent implements OnInit, OnDestroy {
    @ViewChild(BaseChartDirective) chart: BaseChartDirective | undefined;

    private subscriptions: Subscription[] = [];

    constructor(
        private httpOpakowanieService: HttpOpakowanieService,
        private httpProduktLeczniczyService: HttpProduktLeczniczyService
    ) {
    }

    ngOnInit() {
        this.getTopSubstancjeHandler();
    }

    ngOnDestroy() {
        this.subscriptions.forEach(s => s.unsubscribe());
    }

    protected barChartOptions: ChartConfiguration['options'] = {
        responsive: true,
        scales: {
            x: {},
            y: {}
        },
        plugins: {
            legend: {
                display: true,
            },
            datalabels: {
                anchor: 'end',
                align: 'end'
            }
        }
    };
    protected barChartDataOne: ChartData<'bar'> = {
        labels: [],
        datasets: []
    };
    protected barChartDataTwo: ChartData<'bar'> = {
        labels: [],
        datasets: []
    };
    protected pieChartOptions: ChartConfiguration['options'] = {
        responsive: true,
        plugins: {
            legend: {
                display: true,
                position: 'top',
            },
            datalabels: {
                formatter: (value, ctx) => {
                    if (ctx.chart.data.labels) {
                        return ctx.chart.data.labels[ctx.dataIndex];
                    }
                },
            },
        }
    };
    protected pieChartData: ChartData<'pie', number[], string | string[]> = {
        labels: [],
        datasets: []
    };
    protected isPieChart: boolean = false;
    protected isOne: boolean = true;

    protected getTopPostaciHandler() {
        this.subscriptions.push(
            this.httpProduktLeczniczyService.getTopPostaci().subscribe({
                next: (value: MdCountResult[]) => {
                    this.barChartDataOne.labels = value.map(u => {
                        if(u.name === null) {
                            return "null"
                        }
                        return u.name;
                    });
                    this.barChartDataOne.datasets = [{
                        data: value.map(u => u.value),
                        label: "Top 10 postaci leków"
                    }, {
                        data: value.map(u => u.value2),
                        label: "W tym refundowane"
                    }];
                    this.isPieChart = false;
                    this.isOne = true;
                },
                error: (error: HttpErrorResponse) => {
                    console.log(error);
                }
            })
        );
    }

    protected getTopSubstancjeHandler() {
        this.subscriptions.push(
            this.httpProduktLeczniczyService.getTopSubstancji().subscribe({
                next: (value: MdCountResult[]) => {
                    this.barChartDataTwo.labels = value.map(u => {
                        if(u.name === null) {
                            return "null"
                        }
                        return u.name;
                    });
                    this.barChartDataTwo.datasets = [{
                        data: value.map(u => u.value),
                        label: "Top 10 substancje lecznicze"
                    }, {
                        data: value.map(u => u.value2),
                        label: "W tym refundowane"
                    }];
                    this.isPieChart = false;
                    this.isOne = false;
                },
                error: (error: HttpErrorResponse) => {
                    console.log(error);
                }
            })
        );
    }

    protected getKategorieDostepnosciHandler() {
        this.subscriptions.push(
            this.httpOpakowanieService.getKategoriaDostepnosci().subscribe({
                next: (value: MdCountResult[]) => {
                    this.pieChartData.labels = value.map(u => {
                        if(u.name === null) {
                            return "null"
                        }
                        return u.name;
                    });
                    this.pieChartData.datasets = [{
                        data: value.map(u => u.value)
                    }];
                    this.isPieChart = true;
                },
                error: (error: HttpErrorResponse) => {
                    console.log(error);
                }
            })
        );
    }
}
