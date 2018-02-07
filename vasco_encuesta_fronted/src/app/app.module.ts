import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { PersonaComponent } from './view/persona/persona.component';
import { EncuestaComponent } from './view/encuesta/encuesta.component';
import { InicioComponent } from './view/inicio/inicio.component';


@NgModule({
  declarations: [
    AppComponent,
    PersonaComponent,
    EncuestaComponent,
    InicioComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
