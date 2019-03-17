import {Component, OnInit, ViewChild} from '@angular/core';
import {Articulos} from "../model/articulosModel";
import {ApiService} from "../model/api.service";
import {MatDialog, MatDialogConfig, MatPaginator, MatSort, MatTableDataSource} from "@angular/material";
import {tap} from "rxjs/operators";
import {ArticulosEditComponent} from "../articulos-edit/articulos-edit.component";

@Component({
  selector: 'app-articulos',
  templateUrl: './articulos.component.html',
  styleUrls: ['./articulos.component.css']
})
export class ArticulosComponent implements OnInit {

  displayedColumns: string[] = ['codigoArticulo', 'descripcionArticulo', 'precioUnidadArticulo', 'actions'];
  dataSource: MatTableDataSource<Articulos>;
  myData: Articulos[] = [];

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private apiService: ApiService, private dialog: MatDialog) {
  }

  ngOnInit() {
    this.dataSource = new MatTableDataSource(this.myData);
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
    this.dataSource.sort.direction = "asc";
    this.dataSource.sort.active = "codigoArticulo";
    this.refreshTable()
  }

  showUpdate(Articulos) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.data = Articulos;
    const dialogRef = this.dialog.open(ArticulosEditComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(
      bool => {
        if (bool == true) {
          this.refreshTable()
        }
      }
    );
  }

  deleteArticulo(id) {
    this.apiService.deleteArticulo(id).pipe(
      tap(articulos => console.log("Calling to delete id: " + id))
    ).subscribe(res => {
      this.refreshTable();
    });
  }

  refreshTable() {
    this.getAllArticulos();
  }

  private getAllArticulos() {
    this.apiService.getArticulos()
      .subscribe(res => {
        console.log("Requesting data");
        this.dataSource.data = res;
        console.log("Recieving: " + res.length);
      }, err => {
        console.error("Error getting data" + err);
      });
  }
}
