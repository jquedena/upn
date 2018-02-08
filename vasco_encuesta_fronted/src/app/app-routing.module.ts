import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { InicioSesionComponent } from './view/inicio-sesion/inicio-sesion.component';
import { EncuestaComponent } from './view/encuesta/encuesta.component';
import { PersonaComponent } from './view/persona/persona.component';

const routes: Routes = [
  { path: '', redirectTo: '/inicio-sesion', pathMatch: 'full' },
  { path: 'inicio-sesion', component: InicioSesionComponent },
  { path: 'encuesta', component: EncuestaComponent },
  { path: 'persona', component: PersonaComponent }
]

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
