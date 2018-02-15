import { Component, OnInit } from '@angular/core';
import { Persona } from '../../model/Persona';

@Component({
  selector: 'app-person',
  templateUrl: './person.component.html',
  styleUrls: ['./person.component.css']
})
export class PersonComponent implements OnInit {

  persona: Persona = {};

  constructor() { }

  ngOnInit() {
  }

  grabar(): void {
    
  }
}
