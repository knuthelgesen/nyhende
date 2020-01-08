import {Component, Input, OnInit} from '@angular/core';
import {MenuItem} from "../../../model/menu.model";

@Component({
  selector: 'app-menu-item',
  templateUrl: './menu-item.component.html',
  styleUrls: ['./menu-item.component.sass']
})
export class MenuItemComponent implements OnInit {

  @Input()
  menuItem: MenuItem;

  constructor() { }

  ngOnInit() {
  }

}
