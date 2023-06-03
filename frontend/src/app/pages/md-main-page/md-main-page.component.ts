import {Component, OnDestroy, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-md-main-page',
  templateUrl: './md-main-page.component.html',
  styleUrls: ['./md-main-page.component.css']
})
export class MdMainPageComponent implements OnInit, OnDestroy{

    protected searchForm: FormGroup;

    constructor(private formBuilder: FormBuilder) {
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

    protected searchHandler() {
        if(this.searchForm.invalid) {
            return;
        }
        // TODO: Implement search page
    }
}
