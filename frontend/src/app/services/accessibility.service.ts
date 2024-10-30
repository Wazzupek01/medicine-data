import {Injectable} from '@angular/core';
import {BehaviorSubject, Subject} from "rxjs";
import {MdAccessibilityContrastOptions} from "../models/md-accessibility-contrast-options";
import WhiteAndBlackContrast from "../const/white-and-black-contrast";
import {MdAccessibilityFontOptions} from "../models/md-accessibility-font-options";
import DefaultFontConfiguration from "../const/default-font-configuration";

@Injectable({
  providedIn: 'root'
})
export class AccessibilityService {

    private readonly fontOptions$: Subject<MdAccessibilityFontOptions>;
    private readonly contrastOptions$: Subject<MdAccessibilityContrastOptions>;

    constructor(){
        this.fontOptions$ = new BehaviorSubject<MdAccessibilityFontOptions>(new DefaultFontConfiguration());
        this.contrastOptions$ = new BehaviorSubject<MdAccessibilityContrastOptions>(new WhiteAndBlackContrast());
    }

    public get fontOptions(): BehaviorSubject<MdAccessibilityFontOptions> {
        return this.fontOptions$ as BehaviorSubject<MdAccessibilityFontOptions>;
    }

    public get contrastOptions(): BehaviorSubject<MdAccessibilityContrastOptions> {
        return this.contrastOptions$ as BehaviorSubject<MdAccessibilityContrastOptions>;
    }

    public setFontOptions(fontOptions: MdAccessibilityFontOptions) {
        this.fontOptions$.next(fontOptions);
    }

    public setContrastOptions(contrastOptions: MdAccessibilityContrastOptions) {
        this.contrastOptions$.next(contrastOptions);
    }
}
