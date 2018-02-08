import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';

import { MaterialModule } from './module/material.module';
import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { PersonaComponent } from './view/persona/persona.component';
import { EncuestaComponent } from './view/encuesta/encuesta.component';
import { InicioSesionComponent } from './view/inicio-sesion/inicio-sesion.component';

@NgModule({
  declarations: [
    AppComponent,
    PersonaComponent,
    EncuestaComponent,
    InicioSesionComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MaterialModule.forRoot(),
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
