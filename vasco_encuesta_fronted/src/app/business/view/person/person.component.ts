import { Component, OnInit } from '@angular/core';
import { Persona } from '../../model/Persona';
import { GridOptions } from "ag-grid";

@Component({
  selector: 'app-person',
  templateUrl: './person.component.html',
  styleUrls: ['./person.component.css']
})
export class PersonComponent implements OnInit {

  gridOptions: GridOptions;

  onGridReady(params) {
      params.api.sizeColumnsToFit();
  }

  selectAllRows() {
      this.gridOptions.api.selectAll();
  }

  persona: Persona = {};

  constructor() {
    this.gridOptions = <GridOptions>{};

    this.gridOptions.columnDefs = [
        {headerName: "Make", field: "make"},
        {headerName: "Model", field: "model"}, // , cellRendererFramework: RedComponentComponent
        {headerName: "Price", field: "price"}
    ];

    this.gridOptions.rowData = [
        {make: "Toyota", model: "Celica", price: 35000},
        {make: "Ford", model: "Mondeo", price: 32000},
        {make: "Porsche", model: "Boxter", price: 72000}
    ]
  }

  ngOnInit() {
  }

  grabar(): void {
    
  }
}
