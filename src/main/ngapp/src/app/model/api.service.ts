import {Injectable} from '@angular/core';
import {Observable, of, Subject} from 'rxjs';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {catchError, tap} from 'rxjs/operators';
import {Articulos} from "./articulosModel";
import {LoadingState} from "../core/loading-state";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};
const apiUrl = "http://localhost:8080/articulos";

@Injectable({
  providedIn: 'root'
})

export class ApiService {

  //Observable for loadgin bar
  private loadingSubject = new Subject<LoadingState>();
  loadingState = this.loadingSubject.asObservable();

  constructor(private http: HttpClient) {
  }

  //Error Response logging
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      this.hideLoading()
      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  //Loading Bar Controls
  showLoading() {
    this.loadingSubject.next(<LoadingState>{isLoading: true});
  }

  hideLoading() {
    this.loadingSubject.next(<LoadingState>{isLoading: false});
  }

  //Crud Requests
  getArticulos(): Observable<Articulos[]> {
    this.showLoading();
    return this.http.get<Articulos[]>(apiUrl).pipe(
      tap(articulos => console.log('from spring articulos: ' + articulos.length)),
      tap(articulos => this.hideLoading()),
      catchError(this.handleError('getArticulos', []))
    );
  }

  addArticulo(Articulos): Observable<Articulos[]> {
    this.showLoading();
    return this.http.post<Articulos[]>(apiUrl, Articulos, httpOptions).pipe(
      tap(articulos => console.log('adding to spring articulo: ' + Articulos)),
      tap(articulos => this.hideLoading()),
      catchError(this.handleError('addArticulo', []))
    );
  }

  deleteArticulo(id): Observable<Articulos[]> {
    this.showLoading();
    const url = apiUrl + "/" + id;
    return this.http.delete<Articulos[]>(url, httpOptions).pipe(
      tap(articulos => console.log('removing to spring id: ' + id)),
      tap(articulos => this.hideLoading()),
      catchError(this.handleError('deleteArticulo', []))
    );
  }

  updateArticulo(Articulos): Observable<Articulos[]> {
    this.showLoading();
    return this.http.put<Articulos[]>(apiUrl, Articulos, httpOptions).pipe(
      tap(articulos => console.log('updating to spring articulo: ')),
      tap(articulos => console.log(Articulos)),
      tap(articulos => this.hideLoading()),
      catchError(this.handleError('updateArticulo', []))
    );
  }
}
