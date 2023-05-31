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

const routes: Routes = [
    {path: "", component: MdMainPageComponent, canActivate: [isLoginGuard]},
    {path: "chart", component: MdChartPageComponent, canActivate: [isLoginGuard]},
    {path: "table", component: MdTablePageComponent, canActivate: [isLoginGuard]},
    {path: "login", component: MdLoginPageComponent, canActivate: [isLoggedOutGuard]},
    {path: "register", component: MdRegisterPageComponent, canActivate: [isLoggedOutGuard]},
    {path: "logout", component: MdLogoutPageComponent, canActivate: [isLoginGuard]}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
