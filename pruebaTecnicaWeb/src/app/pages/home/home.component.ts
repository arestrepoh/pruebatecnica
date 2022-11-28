import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/_services/auth.service';
import { MovieserviceService } from 'src/app/_services/movieservice.service';
import { StorageService } from 'src/app/_services/storage.service';
import { Movies } from '../interfaces/movies';
import { Filter } from '../interfaces/filter';
import { Gender } from '../interfaces/gender';
import { Type } from '../interfaces/type';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})

export class HomeComponent implements OnInit {
  isLoggedIn = false; //Indica si ya el usuario esta loggueado.

  //Variables para cargar los datos
  data!: Movies[]; //Variable para cargar los datos.
  genders!: Gender[];
  types!: Type[];
  user!: any;
  editData!: Movies;

  //Variables para el ordenamiento de los datos
  selectedField!: string; // campo seleccionado para el ordenamiento
  ordenValue!: boolean; //Direccion del ordenamiento

  //variables edicion 
  score!: number;

  //Variables para el filtrado
  genderCode!: string;
  typeCode!: string;
  nameFilter!: string;

  errorMessage!: string;
  /** 
   * Campos por los cuales es posible ordenar
   */
  ordenar: any[] = [{ 'text': 'Genero', 'value': 'gender.name' }, { 'text': 'Nombre', 'value': 'name' }, { 'text': 'Puntaje', 'value': 'score' }, { 'text': 'Tipo', 'value': 'type.name' }];

  constructor(private authService: AuthService, private storageService: StorageService, private router: Router, private movies: MovieserviceService) { }

  ngOnInit(): void {
    if (this.storageService.isLoggedIn()) {
      this.isLoggedIn = true;
      this.errorMessage = '';
      this.allData();
      this.loadSelect();
      this.cleanFilter();
    } else {
      this.router.navigate(['login']);
    }
  }

  /**
   * Carga todos los datos 
   */
  allData(): void {
    this.errorMessage = '';
    this.movies.all().subscribe({
      next: ms => {
        this.data = ms
      },
      error: err => {
        console.log('Error ' + err.message)
      }
    });
  }

  /**
   * Carga los datos para los Select
   */
  loadSelect(): void {
    this.movies.allGender().subscribe({
      next: g => {
        this.genders = g
      },
      error: err => {
        console.log('Error ' + err.message)
      }
    });
    this.movies.allType().subscribe({
      next: t => {
        this.types = t
      },
      error: err => {
        console.log('Error ' + err.message)
      }
    });
  }

  /**
   * Realiza el ordenamiento de la tabla.
   * @returns 
   */
  orderBy(): void {
    this.errorMessage = '';
    if (!this.selectedField) {
      this.selectedField = 'id';
    }
    if (this.ordenValue == undefined) {
      this.ordenValue = false;
    }

    this.movies.allBySort(this.selectedField, this.ordenValue).subscribe({
      next: ms => {
        this.data = ms
      },
      error: err => {
        console.log('Error ' + err.message)
      }
    });
  }

  /**
   * Metodo que se encarga de filtrar los datos de la tabla.
   */
  filter(): void {
    this.errorMessage = '';
    let filterGender: Filter = {
      id: '',
      field: '',
      value: ''
    };

    let filterName: Filter = {
      id: '',
      field: '',
      value: ''
    };
    let filterType: Filter = {
      id: '',
      field: '',
      value: ''
    };

    let filters: Filter[] = [];

    if (this.nameFilter) {
      filterName.id = '1';
      filterName.field = 'name';
      filterName.value = this.nameFilter;
      filters.push(filterName);
    }

    if (this.genderCode) {
      filterGender.id = '2';
      filterGender.field = 'gender';
      filterGender.value = this.genderCode;
      filters.push(filterGender);
    }

    if (this.typeCode) {
      filterType.id = '3';
      filterType.field = 'type';
      filterType.value = this.typeCode;
      filters.push(filterType);
    }

    this.movies.allByFilter(filters).subscribe({
      next: ms => {
        this.data = ms
      },
      error: err => {
        console.log('Error ' + err.message)
      }
    });
  }

  /**
   * Limpia los componentes del formulario de filtro.
   */
  cleanFilter(): void {
    this.nameFilter = '';
    this.typeCode = '';
    this.genderCode = '';
  }

  /**
   * Carga un dato de manera aleatoria.
   */
  loadRandom(): void {
    this.errorMessage = '';
    this.movies.random().subscribe({
      next: ms => {
        this.data = ms
      },
      error: err => {
        console.log('Error ' + err.message)
      }
    });
  }

  /**
   * Metodo para seleccionar la pelicula o serie a modificar
   * @param editData 
   */
  edit(editData: Movies): void {
    this.errorMessage = '';
    this.editData = editData;
  }

  /**
   * Actualiza el score de la pelicula o serie
   */
  saveScore(): void {
    this.errorMessage = '';
    let scoreOld = this.editData.score;
    this.editData.score = this.score;
    this.score = 0;
    this.movies.saveScore(this.editData).subscribe({
      next: ms => {
        if (ms && ms.tipo == 1) {
          this.errorMessage = ms.message;
          this.editData.score = scoreOld;
        } else {
          this.allData();
        }

      },
      error: err => {
        console.log('Error ' + err.message)
      }
    });
  }

  /**   
   * Actualiza el numero de visualizaciones de la pelicula o serie
   */
  saveView(editData: Movies): void {
    this.errorMessage = '';
    editData.view = true;
    this.movies.saveView(editData).subscribe({
      next: ms => {
        if (ms && ms.tipo == 1) {
          this.errorMessage = ms.message;
        } else {
          this.allData();
        }
      },
      error: err => {
        console.log('Error ' + err.message)
      }
    });
  }
}
