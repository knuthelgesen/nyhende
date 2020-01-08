import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NyhendeComponent } from './nyhende/nyhende.component';
import {HttpClientModule} from "@angular/common/http";
import { MenuComponent } from './nyhende/menu/menu.component';
import { MenuItemComponent } from './nyhende/menu/menu-item/menu-item.component';

@NgModule({
  declarations: [
    AppComponent,
    NyhendeComponent,
    MenuComponent,
    MenuItemComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
