import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {ArticulosComponent} from './articulos/articulos.component';
import {AppRoutingModule} from "./core/router";
import {HttpClientModule} from "@angular/common/http";
import {MaterialModule} from "./core/material.module";
import {ArticulosAddComponent} from './articulos-add/articulos-add.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {ArticulosEditComponent} from './articulos-edit/articulos-edit.component';
import {ProgressBarComponent} from './progress-bar/progress-bar.component';

@NgModule({
  declarations: [
    AppComponent,
    ArticulosComponent,
    ArticulosAddComponent,
    ArticulosEditComponent,
    ProgressBarComponent
  ],
  imports: [
    BrowserModule, HttpClientModule, AppRoutingModule, BrowserAnimationsModule,
    FormsModule, ReactiveFormsModule, MaterialModule
  ],
  providers: [],
  bootstrap: [AppComponent],
  entryComponents: [ArticulosEditComponent]
})
export class AppModule { }
