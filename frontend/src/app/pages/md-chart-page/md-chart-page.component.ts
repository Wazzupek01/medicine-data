import {Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import { ChartConfiguration, ChartData, ChartEvent, ChartType } from 'chart.js';
import { BaseChartDirective } from 'ng2-charts';

import DataLabelsPlugin from 'chartjs-plugin-datalabels';

@Component({
  selector: 'app-md-chart-page',
  templateUrl: './md-chart-page.component.html',
  styleUrls: ['./md-chart-page.component.css']
})
export class MdChartPageComponent implements OnInit, OnDestroy {
    @ViewChild(BaseChartDirective) chart: BaseChartDirective | undefined;

    constructor() {
    }

    ngOnInit() {
    }

    ngOnDestroy() {
    }

    protected barChartOptions: ChartConfiguration['options'] = {
        responsive: true,
        // We use these empty structures as placeholders for dynamic theming.
        scales: {
            x: {},
            y: {
                min: 10,
                max: 100
            }
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

    protected barChartType: ChartType = 'bar';

    protected barChartPlugins = [
        DataLabelsPlugin
    ];

    protected barChartData: ChartData<'bar'> = {
        labels: [ '2006', '2007', '2008', '2009', '2010', '2011', '2012', '2007', '2008', '2009', '2010', '2011', '2012' ],
        datasets: [
            { data: [ 65, 59, 80, 81, 56, 55, 40, 59, 80, 81, 56, 55, 40 ], label: 'Series A', backgroundColor: "red" },
            { data: [ 28, 48, 40, 19, 86, 27, 90, 59, 80, 81, 56, 55, 40 ], label: 'Series B', backgroundColor: "pink" },
            { data: [ 28, 48, 40, 19, 86, 27, 90, 59, 80, 81, 56, 55, 40 ], label: 'Series C' }
        ]
    };

    // events
    protected chartClicked({ event, active }: { event?: ChartEvent, active?: {}[] }): void {
    }

    protected chartHovered({ event, active }: { event?: ChartEvent, active?: {}[] }): void {
    }
}
