import { Component, OnInit } from '@angular/core';
import { MatFormFieldModule, MatInputModule } from '@angular/material';

@Component({
  selector: 'app-inicio-sesion',
  templateUrl: './inicio-sesion.component.html',
  styleUrls: ['./inicio-sesion.component.css']
})
export class InicioSesionComponent implements OnInit {

  hide = true;

  constructor() { }

  ngOnInit() {
  }

}
