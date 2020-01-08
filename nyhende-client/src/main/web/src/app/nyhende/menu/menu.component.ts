import {Component, Input, OnInit} from '@angular/core';
import {ContentType} from "../../model/content.model";
import {MenuService} from "../../services/menu.service";
import {MenuItem} from "../../model/menu.model";

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.sass']
})
export class MenuComponent implements OnInit {

  @Input()
  contentType: ContentType;

  private menuItems: MenuItem[];

  constructor(private menuService: MenuService) { }

  ngOnInit() {
    this.menuService.getMenu().subscribe(menuItems => {
      this.menuItems = menuItems;
      console.log(this.menuItems);
    }, error => {
      console.log(error);
    });
  }

}
