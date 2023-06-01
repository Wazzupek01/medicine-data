import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {Subscription} from "rxjs";

@Component({
    selector: 'app-md-login-page',
    templateUrl: './md-login-page.component.html',
    styleUrls: ['./md-login-page.component.css']
})
export class MdLoginPageComponent implements OnInit, OnDestroy {

    protected loginError: boolean = false;
    protected loginForm: FormGroup;

    private subscriptions: Subscription[] = [];

    constructor(
        formBuilder: FormBuilder,
        private router: Router,
    ) {
        this.loginForm = formBuilder.group({
            email: {
                value: "",
                validatorOrOpts: [
                    Validators.required,
                    Validators.email
                ]
            },
            password: {
                value: "",
                validatorOrOpts: [
                    Validators.required,
                    Validators.minLength(8)
                ]
            },
        });
    }

    ngOnInit() {
    }

    ngOnDestroy() {
        this.subscriptions.forEach(s => s.unsubscribe());
    }

    protected loginHandler() {
        // this.subscriptions.push()
    }
}
