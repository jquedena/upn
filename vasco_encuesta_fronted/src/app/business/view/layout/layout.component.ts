import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.css']
})
export class LayoutComponent implements OnInit {

  isAppMenuHorizontalCollapsed: boolean = false;
  isToogle: boolean = false;
  hideText: string = "nav-show";
  classButtonToggle: string = "fa fa-fw fa-angle-left";

  constructor() { }

  ngOnInit() {
  }

  clickToggleMenu():void {
    this.isToogle = !this.isToogle;
    this.hideText = this.isToogle ? "nav-hidden" : "nav-show";
    this.classButtonToggle = this.isToogle ? "fa fa-fw fa-angle-right" : "fa fa-fw fa-angle-left";
  }
}
