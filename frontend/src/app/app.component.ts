import {Component, OnDestroy, OnInit, ViewEncapsulation} from '@angular/core';
import {AccessibilityService} from "./services/accessibility.service";
import {Subscription} from "rxjs";
import {MdAccessibilityFontOptions} from "./models/md-accessibility-font-options";
import {MdAccessibilityContrastOptions} from "./models/md-accessibility-contrast-options";
import DefaultFontConfiguration from "./const/default-font-configuration";
import BlackAndWhiteContrast from "./const/black-and-white-contrast";

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css'],
    encapsulation: ViewEncapsulation.None
})
export class AppComponent implements OnInit, OnDestroy {
    private subscriptions: Subscription[] = [];

    constructor(private accessibilityService: AccessibilityService) {
        console.log(document.documentElement.style.getPropertyValue('--bulma-body-font-size'));
    }

    ngOnInit(): void {
        this.subscriptions.push(this.subscribeContrast());
        this.subscriptions.push(this.subscribeFonts());
    }

    ngOnDestroy(): void {
        this.subscriptions.forEach(subscription => subscription.unsubscribe());
    }

    private subscribeFonts() : Subscription {
        return this.accessibilityService
            .fontOptions
            .subscribe({
                next: (value: MdAccessibilityFontOptions) => {
                    this.changeFontStyle(value);
                },
                error: (err: any) => {
                    this.changeFontStyle(new DefaultFontConfiguration());
                }
            });
    }

    private subscribeContrast() : Subscription {
        return this.accessibilityService
            .contrastOptions
            .subscribe({
                next: (value: MdAccessibilityContrastOptions) => {
                    this.changeContrast(value);
                },
                error: (err: any) => {
                    this.changeContrast(new BlackAndWhiteContrast());
                }
            })
    }

    private changeFontStyle(fontSettings: MdAccessibilityFontOptions): void {
        // TODO: Implement changing fonts
        this.setStyle("--global-font-size", fontSettings.titleFontSize + 'px');
    }

    private changeContrast(contrastSettings: MdAccessibilityContrastOptions): void {
        // TODO: Implement changing fonts
        this.setStyle("--global-font-color", contrastSettings.fontColor);
        this.setStyle("--global-background-color", contrastSettings.backgroundColor);
    }

    private setStyle(variableName: string, value: any) {
        document.documentElement.style.setProperty(variableName, value);
    }
}
