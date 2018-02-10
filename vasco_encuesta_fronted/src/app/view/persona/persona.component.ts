import { Component, OnInit } from '@angular/core';
import { Persona } from '../../model/Persona';

@Component({
  selector: 'app-persona',
  templateUrl: './persona.component.html',
  styleUrls: ['./persona.component.css']
})
export class PersonaComponent implements OnInit {

  persona: Persona = {nombre: "Joel"};

  constructor() { }

  ngOnInit() {
  }

  grabar():void {
    this.persona.nombre = "";
  }
}
