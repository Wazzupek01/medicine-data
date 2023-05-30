import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";

@Component({
  selector: 'app-md-login-page',
  templateUrl: './md-login-page.component.html',
  styleUrls: ['./md-login-page.component.css']
})
export class MdLoginPageComponent implements OnInit{

  protected loginError: boolean = false;
  protected loginForm: FormGroup;

  constructor(
    formBuilder: FormBuilder,
    private router: Router,
  ) {
    this.loginForm = formBuilder.group({
      email: new FormControl("", [
        Validators.email,
        Validators.required
      ]),
      password: new FormControl("", [
        Validators.required,
        Validators.minLength(8)
      ])
    });
  }

  ngOnInit() {
  }

  protected loginHandler() {

  }
}
