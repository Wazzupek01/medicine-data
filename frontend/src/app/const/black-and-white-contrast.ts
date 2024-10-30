import {MdAccessibilityContrastOptions} from "../models/md-accessibility-contrast-options";

export default class BlackAndWhiteContrast implements MdAccessibilityContrastOptions {
    public backgroundColor: string = "#000";
    public fontColor: string = "#fff";
}
