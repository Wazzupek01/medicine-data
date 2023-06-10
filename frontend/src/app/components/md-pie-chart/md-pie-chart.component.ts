import {Component, Input} from '@angular/core';
import {ChartConfiguration, ChartData, ChartType} from "chart.js";
import DatalabelsPlugin from "chartjs-plugin-datalabels";

@Component({
    selector: 'app-md-pie-chart',
    templateUrl: './md-pie-chart.component.html',
    styleUrls: ['./md-pie-chart.component.css']
})
export class MdPieChartComponent {

    @Input() public pieChartOptions!: ChartConfiguration['options'];
    @Input() public pieChartData!: ChartData<'pie', number[], string | string[]>;
    protected pieChartType: ChartType = 'pie';
    protected pieChartPlugins = [DatalabelsPlugin];
}
