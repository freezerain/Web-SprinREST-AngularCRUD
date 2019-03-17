import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, NgForm, Validators} from "@angular/forms";
import {ApiService} from "../model/api.service";
import {tap} from "rxjs/operators";

@Component({
  selector: 'app-articulos-add',
  templateUrl: './articulos-add.component.html',
  styleUrls: ['./articulos-add.component.css']
})
export class ArticulosAddComponent implements OnInit {
  @Output() addedArticulo = new EventEmitter<any>();

  articulosForm: FormGroup;
  codigoArticulo: number = null;
  descripcionArticulo: string = '';
  precioUnidadArticulo: number = null;

  constructor(private api: ApiService, private formBuilder: FormBuilder) {
  }

  ngOnInit() {
    this.articulosForm = this.formBuilder.group({
      'codigoArticulo': [null, Validators.compose([Validators.required, Validators.min(0)])],
      'descripcionArticulo': ['', Validators.required],
      'precioUnidadArticulo': [null, Validators.compose([Validators.required, Validators.min(0)])]
    });
  }

  onFormSubmit(form: NgForm) {
    this.api.addArticulo(form).pipe(
      tap(res => {
        console.log(form)
      })
    )
      .subscribe(data => {
        this.addedArticulo.emit(null);
      });
  }

}
