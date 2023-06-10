import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {MdLoginPageComponent} from './pages/md-login-page/md-login-page.component';
import {ReactiveFormsModule} from "@angular/forms";
import {MdRegisterPageComponent} from './pages/md-register-page/md-register-page.component';
import {AgGridModule} from "ag-grid-angular";
import {NgChartsModule} from "ng2-charts";
import {MdMainPageComponent} from './pages/md-main-page/md-main-page.component';
import {MdNavbarComponent} from './components/md-navbar/md-navbar.component';
import {MdFooterComponent} from './components/md-footer/md-footer.component';
import {MdChartPageComponent} from './pages/md-chart-page/md-chart-page.component';
import {MdTablePageComponent} from './pages/md-table-page/md-table-page.component';
import {MdLogoutPageComponent} from './pages/md-logout-page/md-logout-page.component';
import {HttpClientModule} from "@angular/common/http";
import {MdSearchPageComponent} from './pages/md-search-page/md-search-page.component';
import {MdPaginationComponent} from './components/md-pagination/md-pagination.component';
import {MdBarChartComponent} from './components/md-bar-chart/md-bar-chart.component';
import {MdPieChartComponent} from './components/md-pie-chart/md-pie-chart.component';
import {MdDownloadPageComponent} from './pages/md-download-page/md-download-page.component';
import {NgOptimizedImage} from "@angular/common";

@NgModule({
    declarations: [
        AppComponent,
        MdLoginPageComponent,
        MdRegisterPageComponent,
        MdMainPageComponent,
        MdNavbarComponent,
        MdFooterComponent,
        MdChartPageComponent,
        MdTablePageComponent,
        MdLogoutPageComponent,
        MdSearchPageComponent,
        MdPaginationComponent,
        MdBarChartComponent,
        MdPieChartComponent,
        MdDownloadPageComponent,
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        ReactiveFormsModule,
        AgGridModule,
        NgChartsModule,
        HttpClientModule,
        NgOptimizedImage
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {
}
