import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Movies } from '../pages/interfaces/movies';
import { Gender } from '../pages/interfaces/gender';
import { Type } from '../pages/interfaces/type';
import { Filter } from '../pages/interfaces/filter';

/**
 * Servicio que consume los endpoints realacionados con las peliculas y series.
 * 
 */

/**
 * Constante de la url de la api
 */
const API = '/api/movie-series';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Access-Control-Allow-Origin': '*' })
};



@Injectable({
  providedIn: 'root'
})
export class MovieserviceService {

  constructor(private http: HttpClient) { }

  /**
   * 
   * @returns Metodo que retorna un listado aleatorio.
   */
  random(): Observable<Movies[]> {
    return this.http.get<Movies[]>(API + '/random');
  }

  /**
   * Metodo que retorna el listado de las peliculas y series.
   * @returns 
   */
  all(): Observable<Movies[]> {
    return this.http.get<Movies[]>(API);
  }

  allBySort(sortby: string, desc: boolean): Observable<Movies[]> {
    return this.http.get<Movies[]>(API + '/orderby?sortby=' + sortby + "&desc=" + desc);
  }

  allByFilter(filters: Filter[]): Observable<Movies[]> {
    return this.http.post<Movies[]>(API + '/filterby', filters);
  }

  allGender(): Observable<Gender[]> {
    return this.http.get<Gender[]>(API + '/gender');
  }

  allType(): Observable<Type[]> {
    return this.http.get<Type[]>(API + '/type');
  }

  saveScore(editData: Movies) {
    return this.http.post<any>(API + '/uscore', editData);
  }

  saveView(editData: Movies) {
    return this.http.post<any>(API + '/uview', editData);
  }
}
