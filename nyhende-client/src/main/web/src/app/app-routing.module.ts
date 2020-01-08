import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {NyhendeComponent} from "./nyhende/nyhende.component";


const routes: Routes = [
  {path: '**', component: NyhendeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
