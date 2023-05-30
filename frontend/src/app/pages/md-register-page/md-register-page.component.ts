import {Component, OnDestroy, OnInit} from '@angular/core';
import {
    AbstractControl,
    FormBuilder,
    FormGroup,
    ValidationErrors,
    Validators
} from "@angular/forms";
import {Router} from "@angular/router";
import {Subscription} from "rxjs";

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
    ) {
        this.registerForm = formBuilder.group(
            {
                email: formBuilder.control({
                    value: "",
                    validatorOrOpts: [
                        Validators.required,
                        Validators.email
                    ]
                }),
                password: formBuilder.control({
                    value: "",
                    validatorOrOpts: [
                        Validators.required,
                        Validators.minLength(8)
                    ]
                }),
                repeatPassword: formBuilder.control({
                    value: "",
                    validatorOrOpts: [
                        Validators.required,
                        Validators.minLength(8)
                    ]
                }),
                role: formBuilder.control({
                    value: "USER",
                    validatorsOrOpts: []
                }),
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
        // this.subscriptions.push()
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
