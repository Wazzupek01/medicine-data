import { Component } from '@angular/core';
import {AccessibilityService} from "../../services/accessibility.service";
import WhiteAndBlackContrast from "../../const/white-and-black-contrast";
import BlackAndWhiteContrast from "../../const/black-and-white-contrast";
import YellowAndBlackContrast from "../../const/yellow-and-black-contrast";
import BlackAndYellowContrast from "../../const/black-and-yellow-contrast";
import DefaultFontConfiguration from "../../const/default-font-configuration";
import LargerFontConfiguration from "../../const/larger-font-configuration";
import LargesFontConfiguration from "../../const/larges-font-configuration";

@Component({
  selector: 'app-md-accessibility-select',
  templateUrl: './md-accessibility-select.component.html',
  styleUrls: ['./md-accessibility-select.component.css']
})
export class MdAccessibilitySelectComponent {
    constructor(private accessibilityService: AccessibilityService) {
    }

    protected changeContrastToWhiteAndBlack() {
        this.accessibilityService.setContrastOptions(new WhiteAndBlackContrast());
    }

    protected changeContrastToBlackAndWhite() {
        this.accessibilityService.setContrastOptions(new BlackAndWhiteContrast());
    }

    protected changeContrastToYellowAndBlack() {
        this.accessibilityService.setContrastOptions(new YellowAndBlackContrast());
    }

    protected changeContrastToBlackAndYellow() {
        this.accessibilityService.setContrastOptions(new BlackAndYellowContrast());
    }

    protected changeFontToDefault() {
        this.accessibilityService.setFontOptions(new DefaultFontConfiguration());
    }

    protected changeFontToLargerFont() {
        this.accessibilityService.setFontOptions(new LargerFontConfiguration());
    }

    protected changeFontToLargestFont() {
        this.accessibilityService.setFontOptions(new LargesFontConfiguration());
    }
}
