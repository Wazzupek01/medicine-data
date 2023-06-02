import {Component, ElementRef, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {Subscription} from "rxjs";
import {HttpAuthService} from "../../services/http-auth.service";
import {LocalStorageService} from "../../services/local-storage.service";
import {MdConst} from "../../md-const";

@Component({
    selector: 'app-md-navbar',
    templateUrl: './md-navbar.component.html',
    styleUrls: ['./md-navbar.component.css']
})
export class MdNavbarComponent implements OnInit, OnDestroy {

    @ViewChild('navBurger', {static: true}) protected navBurger: ElementRef | undefined;
    @ViewChild('navMenu', {static: true}) protected navMenu: ElementRef | undefined;

    private subscriptions: Subscription[] = [];
    protected logged: boolean = true;
    protected email: string | undefined | null;

    constructor(
        private authHttpService: HttpAuthService,
        private localStorageService: LocalStorageService
    ) {

    }

    ngOnInit() {
        this.getUserEmail();
    }

    ngOnDestroy() {
        this.subscriptions.forEach(s => s.unsubscribe())
    }

    protected toggleNavbar = () => {
        if (this.navBurger !== undefined && this.navMenu !== undefined) {
            this.navBurger.nativeElement.classList.toggle('is-active');
            this.navMenu.nativeElement.classList.toggle('is-active');
        }
    }

    private getUserEmail() {
        this.subscriptions.push(
            this.authHttpService.getUser().subscribe({
                next: (value: string) => {
                    this.email = value;
                    this.logged = true;
                    this.localStorageService.setValue(MdConst.USEREMAIL, value);
                },
                error: (error: any) => {
                    this.email = undefined;
                    this.logged = false;
                    this.localStorageService.removeValue(MdConst.USEREMAIL);
                    console.log(error);
                }
            })
        );
    }
}
