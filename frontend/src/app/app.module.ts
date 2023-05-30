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

@NgModule({
    declarations: [
        AppComponent,
        MdLoginPageComponent,
        MdRegisterPageComponent,
        MdMainPageComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        ReactiveFormsModule,
        AgGridModule,
        NgChartsModule
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {
}
