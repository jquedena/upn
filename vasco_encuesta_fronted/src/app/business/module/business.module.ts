import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { PersonaComponent } from '../view/persona/persona.component';
import { QuizComponent } from '../view/quiz/quiz.component';
import { LayoutComponent } from '../view/layout/layout.component';
import { BusinessRoutingModule } from './business-routing.module';

@NgModule({
  declarations: [
    LayoutComponent,
    PersonaComponent,
    QuizComponent
  ],
  imports: [
    CommonModule,
    NgbModule.forRoot(),
    FormsModule,
    BusinessRoutingModule
  ],
  providers: [ ]
})
export class BusinessModule {}
