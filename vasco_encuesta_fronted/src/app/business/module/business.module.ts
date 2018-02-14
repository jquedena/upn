import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { PersonaComponent } from '../view/persona/persona.component';
import { EncuestaComponent } from '../view/encuesta/encuesta.component';
import { BusinessRoutingModule } from './business-routing.module';

@NgModule({
  declarations: [
    PersonaComponent,
    EncuestaComponent
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
