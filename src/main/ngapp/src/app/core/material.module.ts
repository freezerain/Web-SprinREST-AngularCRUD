import {NgModule} from '@angular/core';
import {
  MatButtonModule,
  MatCardModule,
  MatDialogModule,
  MatDividerModule,
  MatFormFieldModule,
  MatIconModule,
  MatInputModule,
  MatPaginatorModule,
  MatProgressBarModule,
  MatProgressSpinnerModule,
  MatSortModule,
  MatTableModule,
  MatToolbarModule
} from "@angular/material";

/* Class container for @angular/Material library */
@NgModule({
  imports: [MatTableModule, MatIconModule, MatButtonModule, MatProgressSpinnerModule, MatToolbarModule,
    MatCardModule, MatFormFieldModule, MatInputModule, MatDividerModule, MatPaginatorModule,
    MatSortModule, MatProgressBarModule, MatDialogModule
  ],
  exports: [MatTableModule, MatIconModule, MatButtonModule, MatProgressSpinnerModule, MatToolbarModule,
    MatCardModule, MatFormFieldModule, MatInputModule, MatDividerModule, MatPaginatorModule,
    MatSortModule, MatProgressBarModule, MatDialogModule
  ]
})
export class MaterialModule {
}
