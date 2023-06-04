import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";

@Component({
    selector: 'app-md-main-page',
    templateUrl: './md-main-page.component.html',
    styleUrls: ['./md-main-page.component.css']
})
export class MdMainPageComponent implements OnInit, OnDestroy {

    protected searchForm: FormGroup;

    constructor(
        private router: Router,
        formBuilder: FormBuilder
    ) {
        this.searchForm = formBuilder.group({
            search: new FormControl("",
                [
                    Validators.required,
                ]
            ),
        });
    }

    ngOnInit() {
    }

    ngOnDestroy() {
    }

    protected async searchHandler() {
        if (this.searchForm.invalid) {
            return;
        }

        await this.router.navigateByUrl("/search", {state: {search: this.searchForm.get("search")?.value}});
    }
}
