import {RouterModule, Routes} from '@angular/router';
import {NgModule} from "@angular/core";
import {ArticulosComponent} from "../articulos/articulos.component";

const routes: Routes = [
  { path: 'articulosList', component: ArticulosComponent },
];
/* This class will be used if multiple tables will be implemented*/
@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
