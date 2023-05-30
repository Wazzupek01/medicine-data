import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MdMainPageComponent} from "./pages/md-main-page/md-main-page.component";
import {isLoginGuard} from "./guards/is-login.guard";
import {isLoggedOutGuard} from "./guards/is-logged-out.guard";
import {MdRegisterPageComponent} from "./pages/md-register-page/md-register-page.component";
import {MdLoginPageComponent} from "./pages/md-login-page/md-login-page.component";

const routes: Routes = [
    {path: "", component: MdMainPageComponent, canActivate: [isLoginGuard]},
    {path: "login", component: MdLoginPageComponent, canActivate: [isLoggedOutGuard]},
    {path: "register", component: MdRegisterPageComponent, canActivate: [isLoggedOutGuard]}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
