import {Component, ElementRef, ViewChild} from '@angular/core';

@Component({
  selector: 'app-md-navbar',
  templateUrl: './md-navbar.component.html',
  styleUrls: ['./md-navbar.component.css']
})
export class MdNavbarComponent {

    @ViewChild('navBurger', {static:true }) protected navBurger: ElementRef | undefined;
    @ViewChild('navMenu', {static : true }) protected navMenu: ElementRef| undefined;

    protected logged: boolean = true;

    protected toggleNavbar = () => {
        if(this.navBurger !== undefined && this.navMenu !== undefined) {
            this.navBurger.nativeElement.classList.toggle('is-active');
            this.navMenu.nativeElement.classList.toggle('is-active');
        }
    }
}
