import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router/src/router';

import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { PersonaComponent } from './view/persona/persona.component';
import { EncuestaComponent } from './view/encuesta/encuesta.component';
import { InicioSesionComponent } from './view/inicio-sesion/inicio-sesion.component';
import { AuthGuard } from './service/auth-guard.service';


@NgModule({
  declarations: [
    AppComponent,
    PersonaComponent,
    EncuestaComponent,
    InicioSesionComponent
  ],
  imports: [
    BrowserModule,
    NgbModule.forRoot(),
    FormsModule,
    AppRoutingModule
  ],
  providers: [AuthGuard],
  bootstrap: [AppComponent]
})
export class AppModule {
  // Diagnostic only: inspect router configuration
  /*
  constructor(router: Router) {
    console.log('Routes: ', JSON.stringify(router.config, undefined, 2));
  }
  */
}
