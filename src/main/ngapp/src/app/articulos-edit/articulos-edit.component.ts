import {Component, EventEmitter, Inject, OnInit, Output} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Articulos} from "../model/articulosModel";
import {tap} from "rxjs/operators";
import {ApiService} from "../model/api.service";

@Component({
  selector: 'app-articulos-edit',
  templateUrl: './articulos-edit.component.html',
  styleUrls: ['./articulos-edit.component.css']
})
export class ArticulosEditComponent implements OnInit {
  @Output() updatedArticulo = new EventEmitter<any>();

  articulosForm: FormGroup;
  articulo: Articulos = null;
  codigoArticulo: number = null;
  descripcionArticulo: string = '';
  precioUnidadArticulo: number = null;

  constructor(private fb: FormBuilder, private api: ApiService,
              private dialogRef: MatDialogRef<ArticulosEditComponent>,
              @Inject(MAT_DIALOG_DATA) data) {
    this.articulo = data;
  }

  ngOnInit() {
    this.articulosForm = this.fb.group({
      'codigoArticulo': [this.articulo.codigoArticulo, Validators.compose([Validators.required, Validators.min(0)])],
      'descripcionArticulo': [this.articulo.descripcionArticulo, Validators.required],
      'precioUnidadArticulo': [this.articulo.precioUnidadArticulo, Validators.compose([Validators.required, Validators.min(0)])]
    });
  }

  save() {
    this.api.updateArticulo(this.articulosForm.value).pipe(
      tap(res => {
        console.log("Updating articulo: ")
      }),
      tap(res => {
        console.log(this.articulosForm.value)
      }),
    ).subscribe(data => {
      this.dialogRef.close(true);
    });
  }

  close() {
    this.dialogRef.close(false);
  }

}
