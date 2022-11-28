import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { StorageService } from 'src/app/_services/storage.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  isLoggedIn = false; //Indica si ya el usuario esta loggueado.

  //Nobre del usuario
  username?: string;

  constructor(private storageService: StorageService, private router: Router) { }

  ngOnInit(): void {
    if (this.storageService.isLoggedIn()) {
      this.isLoggedIn = true;
      this.username = this.storageService.getUser().firstName + ' ' + this.storageService.getUser().lastName;
    }
  }

  /**
* Cierra la sesion del usuario
*/
  logout(): void {
    this.storageService.clean();
    this.router.navigate(['login']);
    this.username = '';
    this.isLoggedIn = false;
  }
}
