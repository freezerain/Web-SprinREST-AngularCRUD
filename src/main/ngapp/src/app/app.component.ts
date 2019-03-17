import {Component, EventEmitter, Output, ViewChild} from '@angular/core';
import {ArticulosComponent} from "./articulos/articulos.component";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  @Output() showModalEvent = new EventEmitter();
  @ViewChild(ArticulosComponent) articulosComponent: ArticulosComponent;

  onAdded(){
    this.articulosComponent.refreshTable();
  }
}
