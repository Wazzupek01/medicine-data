import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {Subscription} from "rxjs";
import {HttpAuthService} from "../../services/http-auth.service";
import {MdLoginDto} from "../../models/md-login-dto";
import {LocalStorageService} from "../../services/local-storage.service";
import {MdConst} from "../../md-const";
import {MdUserInfoDto} from "../../models/md-user-info-dto";
import {HttpErrorResponse} from "@angular/common/http";

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
        private httpAuthService: HttpAuthService,
        private localstorageService: LocalStorageService
    ) {
        this.loginForm = formBuilder.group({
            email: new FormControl("",
                [
                    Validators.required,
                    Validators.email
                ]
            ),
            password: new FormControl("",
                [
                    Validators.required,
                    Validators.minLength(8)
                ]
            ),
        });
    }

    ngOnInit() {
    }

    ngOnDestroy() {
        this.subscriptions.forEach(s => s.unsubscribe());
    }

    protected loginHandler() {
        if (!this.loginForm.valid) {
            this.loginForm.markAllAsTouched();
            return;
        }

        const loginDto: MdLoginDto = {
            email: this.loginForm.get("email")?.value,
            password: this.loginForm.get("password")?.value
        };

        this.loginForm.get("email")?.setErrors({passwordMismatch: true});

        this.subscriptions.push(
            this.httpAuthService.loginUser(loginDto).subscribe({
                next: async (value: MdUserInfoDto) => {
                    this.localstorageService.setValue(MdConst.USEREMAIL, value.email);
                    this.localstorageService.setValue(MdConst.USERROLE, value.role.name);
                    this.loginError = false;
                    await this.router.navigateByUrl("/");
                },
                error: (value: HttpErrorResponse) => {
                    if (value.status === 403 || value.status === 400) {
                        this.loginError = true;
                    }
                }
            })
        );
    }
}
