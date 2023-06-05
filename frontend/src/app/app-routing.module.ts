import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MdMainPageComponent} from "./pages/md-main-page/md-main-page.component";
import {isLoginGuard} from "./guards/is-login.guard";
import {isLoggedOutGuard} from "./guards/is-logged-out.guard";
import {MdRegisterPageComponent} from "./pages/md-register-page/md-register-page.component";
import {MdLoginPageComponent} from "./pages/md-login-page/md-login-page.component";
import {MdChartPageComponent} from "./pages/md-chart-page/md-chart-page.component";
import {MdTablePageComponent} from "./pages/md-table-page/md-table-page.component";
import {MdLogoutPageComponent} from "./pages/md-logout-page/md-logout-page.component";
import {MdSearchPageComponent} from "./pages/md-search-page/md-search-page.component";
import {MdDownloadPageComponent} from "./pages/md-download-page/md-download-page.component";
import {isAdminGuard} from "./guards/is-admin.guard";

const routes: Routes = [
    {path: "", component: MdMainPageComponent},
    {path: "search", component: MdSearchPageComponent},
    {path: "chart", component: MdChartPageComponent, canActivate: [isLoginGuard]},
    {path: "table", component: MdTablePageComponent, canActivate: [isLoginGuard]},
    {path: "login", component: MdLoginPageComponent, canActivate: [isLoggedOutGuard]},
    {path: "register", component: MdRegisterPageComponent, canActivate: [isLoggedOutGuard]},
    {path: "logout", component: MdLogoutPageComponent, canActivate: [isLoginGuard]},
    {path: "download", component: MdDownloadPageComponent, canActivate: [isAdminGuard]}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
