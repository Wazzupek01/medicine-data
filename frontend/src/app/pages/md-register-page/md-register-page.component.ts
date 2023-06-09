import {Component, OnDestroy, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, FormControl, FormGroup, ValidationErrors, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {Subscription} from "rxjs";
import {MdRegisterDto} from "../../models/md-register-dto";
import {LocalStorageService} from "../../services/local-storage.service";
import {HttpAuthService} from "../../services/http-auth.service";
import {MdConst} from "../../md-const";
import {MdUserInfoDto} from "../../models/md-user-info-dto";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
    selector: 'app-md-register-page',
    templateUrl: './md-register-page.component.html',
    styleUrls: ['./md-register-page.component.css']
})
export class MdRegisterPageComponent implements OnInit, OnDestroy {

    protected registerForm: FormGroup;

    private subscriptions: Subscription[] = [];

    constructor(
        formBuilder: FormBuilder,
        private router: Router,
        private httpAuthService: HttpAuthService,
        private localstorageService: LocalStorageService
    ) {
        this.registerForm = formBuilder.group(
            {
                email: new FormControl(
                    "",
                    [
                        Validators.required,
                        Validators.email
                    ]
                ),
                password: new FormControl(
                    "",
                    [
                        Validators.required,
                        Validators.minLength(8)
                    ]
                ),
                repeatPassword: new FormControl(
                    "",
                    [
                        Validators.required,
                        Validators.minLength(8)
                    ]
                ),
                role: new FormControl(
                    "ROLE_USER",
                    []
                ),
            },
            {
                validators: [this.repeatPasswordValidator],
                asyncValidators: [],
                updateOn: "change"
            }
        );
    }

    ngOnInit() {

    }

    ngOnDestroy() {
        this.subscriptions.forEach(s => s.unsubscribe());
    }

    protected registerUser() {
        if (this.registerForm.invalid) {
            this.registerForm.markAllAsTouched();
            return;
        }

        const registerDto: MdRegisterDto = {
            email: this.registerForm.get("email")?.value,
            password: this.registerForm.get("password")?.value,
            repeatPassword: this.registerForm.get("repeatPassword")?.value,
            role: this.registerForm.get("role")?.value
        };

        this.subscriptions.push(
            this.httpAuthService.registerUser(registerDto).subscribe({
                next: async (value: MdUserInfoDto) => {
                    this.localstorageService.setValue(MdConst.USEREMAIL, value.email);
                    this.localstorageService.setValue(MdConst.USERROLE, value.role.name);
                    await this.router.navigateByUrl("/")
                },
                error: (error: HttpErrorResponse) => {
                    if (error.status === 400) {
                        if (error.error.message.includes("Invalid Password")) {
                            this.registerForm.get("password")?.setErrors({toShortPassword: true});
                        }
                        if (error.error.message.includes("Email has to be unique!")) {
                            this.registerForm.get("email")?.setErrors({emailTaken: true});
                        }
                    }
                }
            })
        );
    }

    private repeatPasswordValidator = (control: AbstractControl): ValidationErrors | null => {
        const password = control.get("password");
        const repeatPassword = control.get("repeatPassword");
        if (password != null && repeatPassword != null) {
            if (password.value != repeatPassword.value) {
                repeatPassword.setErrors({incorrect: true});
                return {incorrect: {value: control.value}};
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
