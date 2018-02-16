import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {AgGridModule} from "ag-grid-angular/main";

import { FormsModule } from '@angular/forms';
import { PersonComponent } from '../view/person/person.component';
import { QuizComponent } from '../view/quiz/quiz.component';
import { LayoutComponent } from '../view/layout/layout.component';
import { BusinessRoutingModule } from './business-routing.module';

@NgModule({
  declarations: [
    LayoutComponent,
    PersonComponent,
    QuizComponent
  ],
  imports: [
    CommonModule,
    NgbModule.forRoot(),
    AgGridModule.withComponents([]),
    FormsModule,
    BusinessRoutingModule
  ],
  providers: [ ]
})
export class BusinessModule {}
