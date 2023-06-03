import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {HttpAuthService} from "../../services/http-auth.service";
import {LocalStorageService} from "../../services/local-storage.service";
import {MdConst} from "../../md-const";
import {Router} from "@angular/router";

@Component({
    selector: 'app-md-logout-page',
    templateUrl: './md-logout-page.component.html',
    styleUrls: ['./md-logout-page.component.css']
})
export class MdLogoutPageComponent implements OnInit, OnDestroy {

    private subscriptions: Subscription[] = [];
    protected email!: string;

    constructor(
        private httpAuthService: HttpAuthService,
        private localStorageService: LocalStorageService,
        private router: Router
    ) {
    }

    ngOnInit() {
        this.email = this.localStorageService.getValue(MdConst.USEREMAIL)!;
    }

    ngOnDestroy() {
        this.subscriptions.forEach(s => s.unsubscribe());
    }

    protected logout = () => {
        this.subscriptions.push(
            this.httpAuthService.logoutUser().subscribe({
                next: async () => {
                    this.localStorageService.removeValue(MdConst.USEREMAIL);
                    this.localStorageService.removeValue(MdConst.USERROLE);
                    await this.router.navigateByUrl("");
                },
                error: (error: any) => {
                    console.log(error);
                }
            })
        )
    }
}
