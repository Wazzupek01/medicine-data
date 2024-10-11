import {MdAccessibilityFontOptions} from "../models/md-accessibility-font-options";

export default class DefaultFontConfiguration implements MdAccessibilityFontOptions {
    public titleFontSize: number = 14;
    public subtitleFontSize: number = 12;
    public descriptionFontSize: number = 10;
    public paragraphFontSize: number = 10;
}
