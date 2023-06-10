import {Component, Input} from '@angular/core';
import {ChartConfiguration, ChartData, ChartType} from "chart.js";
import DataLabelsPlugin from "chartjs-plugin-datalabels";

@Component({
    selector: 'app-md-bar-chart',
    templateUrl: './md-bar-chart.component.html',
    styleUrls: ['./md-bar-chart.component.css']
})
export class MdBarChartComponent {

    @Input() public barChartOptions: ChartConfiguration['options'];
    @Input() public barChartData: ChartData<'bar'> = {
        labels: [],
        datasets: []
    };

    protected barChartType: ChartType = 'bar';
    protected barChartPlugins = [DataLabelsPlugin];
}
